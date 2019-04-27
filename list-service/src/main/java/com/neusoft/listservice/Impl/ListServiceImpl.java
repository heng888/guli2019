package com.neusoft.listservice.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.neusoft.interfaces.ListService;
import com.neusoft.interfaces.SkuService;
import com.neusoft.javabean.po.SkuInfo;
import com.neusoft.javabean.po.SkuLsInfo;
import com.neusoft.javabean.po.SkuLsParam;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ListServiceImpl implements ListService {

    @Autowired
    JestClient jestClient;

    @Reference
    SkuService skuService;

    @Override
    public List<SkuLsInfo> search(SkuLsParam skuLsParam) {

        //如果三级分类id不为空，查询数据存放到elasticSearch
        if(skuLsParam!=null){
            toElastic(skuLsParam);
        }
        List<SkuLsInfo> skuLsInfos = new ArrayList<>();
        //如何查询es中的数据
        Search search = new Search.Builder(getMyDsl(skuLsParam)).addIndex("guli2019").addType("SkuLsInfo").build();

        try {
            SearchResult execute = jestClient.execute(search);
            List<SearchResult.Hit<SkuLsInfo, Void>> hits = execute.getHits(SkuLsInfo.class);
            for (SearchResult.Hit<SkuLsInfo, Void> hit : hits) {
                SkuLsInfo source = hit.source;
                Map<String, List<String>> highlight = hit.highlight;
                if(hit.highlight!=null){
                    List<String> skuName = highlight.get("skuName");
                    String s = skuName.get(0);
                    source.setSkuName(s);
                }
                skuLsInfos.add(source);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return skuLsInfos;
    }


    //拼接dsl语句
    public String getMyDsl(SkuLsParam skuLsParam){

        String keyword = skuLsParam.getKeyword();
        String[] valueId = skuLsParam.getValueId();
        String catalog3Id = skuLsParam.getCatalog3Id();


        //创建dsl工具对象
        SearchSourceBuilder dsl = new SearchSourceBuilder();

        //创建一个先过滤后搜索的query
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        //query对象过滤语句
        if(StringUtils.isNotBlank(catalog3Id)){
            TermQueryBuilder t = new TermQueryBuilder("catalog3Id",catalog3Id);
            boolQueryBuilder.filter(t);
        }

        if(null!=valueId && valueId.length>0){
            for (int i = 0; i <valueId.length ; i++) {
                TermQueryBuilder t = new TermQueryBuilder("skuAttrValueList.valueId",valueId[i]);
                boolQueryBuilder.filter(t);
            }
        }

        //query对象搜索语句
        if(StringUtils.isNotBlank(keyword)){
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("skuName",keyword);
            boolQueryBuilder.must(matchQueryBuilder);
        }

        dsl.query(boolQueryBuilder);
        dsl.from(0);
        dsl.size(20);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("skuName");
        highlightBuilder.preTags("<span style='color:red;font-weight:bolder'>");
        highlightBuilder.postTags("</span>");
        dsl.highlight(highlightBuilder);
        return dsl.toString();
    }


    /**
     *     将sql中的数据存放到elastic中
     */
    public void toElastic(SkuLsParam skuLsParam) {
        List<SkuInfo> skuInfoList = null;
        String catalog3Id = skuLsParam.getCatalog3Id();
        // 查询mysql中的sku信息
        if(StringUtils.isNotBlank(catalog3Id)){
            skuInfoList = skuService.getSkuListByCatalog3Id(Long.parseLong(catalog3Id));
        }
        String keyword = skuLsParam.getKeyword();
        if(StringUtils.isNotBlank(keyword)){
            skuInfoList = skuService.getSkuListBykeyWord(keyword);
        }
        // 转化es中的sku信息
        List<SkuLsInfo> skuLsInfos = new ArrayList<>();

        for (SkuInfo skuInfo : skuInfoList) {
            SkuLsInfo skuLsInfo = new SkuLsInfo();

            try {
                BeanUtils.copyProperties(skuLsInfo,skuInfo);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            skuLsInfos.add(skuLsInfo);
        }

        // 导入到es中
        for (SkuLsInfo skuLsInfo : skuLsInfos) {
            String id = skuLsInfo.getId();

            Index build = new Index.Builder(skuLsInfo).index("guli2019").type("SkuLsInfo").id(id).build();

            try {
                jestClient.execute(build);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
