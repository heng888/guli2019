package com.neusoft.list.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.neusoft.interfaces.AttrService;
import com.neusoft.interfaces.ListService;
import com.neusoft.javabean.po.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Id;
import java.util.*;

@Controller
public class ListController {

    @Reference
    ListService listService;

    @Reference
    AttrService attrService;

    /**
     * 根据首页传过来的条件，显示符合条件的商品
     * @param skuLsParam 前端接收参数
     * @param map
     * @return
     */
    @RequestMapping("list.html")
    public String list(SkuLsParam skuLsParam, ModelMap map){
        List<SkuLsInfo> skuLsInfos = listService.search(skuLsParam);
        List<BaseAttrInfo> baseAttrInfos =null;
        List<Crumb> crumbs =new ArrayList<>();
        if(skuLsInfos!=null&&skuLsInfos.size()>0){
            //封装平台属性的列表，排除一级选中的属性
            baseAttrInfos = getAttrValueIds(skuLsInfos);

            String[] valueId = skuLsParam.getValueId();
            //面包屑
            if(valueId!=null && valueId.length>0){
                for (String s : valueId) {
                    Iterator<BaseAttrInfo> iterator = baseAttrInfos.iterator();
                    Crumb crumb = new Crumb();
                    //制作面包屑
                    String urlParamForCrumb = getUrlParamForCrumb(skuLsParam, s);
                    //制作面包屑的name
                    String valueName = "";

                    while(iterator.hasNext()){
                        BaseAttrInfo baseAttrInfo = iterator.next();
                        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
                        for (BaseAttrValue baseAttrValue : attrValueList) {
                            if(baseAttrValue.getId().toString().equals(s)){
                                //面包屑的valueName
                                valueName = baseAttrValue.getValueName();
                                //封装好面包屑
                                crumb.setValueName(valueName);
                                crumb.setUrlParam(urlParamForCrumb);
                                crumbs.add(crumb);
                                //当前游标所在位置的元素删除
                                iterator.remove();
                            }
                        }
                    }
                }
            }

        }

        //将每次的筛选条件保存起来，得到一个拼接字符串
        String urlParam = getUrlParam(skuLsParam);
        map.put("skuLsInfoList",skuLsInfos);
        map.put("urlParam",urlParam);
        map.put("attrList",baseAttrInfos);
        map.put("attrValueSelectedList",crumbs);
        return "list";
    }

    /**
     * 面包屑的url
     * @param skuLsParam
     * @return
     */
    private String getUrlParamForCrumb(SkuLsParam skuLsParam,String id) {
        String[] valueId = skuLsParam.getValueId();
        String catalog3Id = skuLsParam.getCatalog3Id();
        String keyword = skuLsParam.getKeyword();

        String urlParam = "";
        //获取三级分类id
        if(StringUtils.isNotBlank(catalog3Id)){
            //如果urlParam为空第一个拼接的就不加 &
            if(StringUtils.isNotBlank(urlParam)){
                urlParam = urlParam + "&catalog3Id=" + catalog3Id;
            }else {
                urlParam =  "catalog3Id=" + catalog3Id;
            }
        }
        //获取输入内容
        if(StringUtils.isNotBlank(keyword)){
            if(StringUtils.isNotBlank(urlParam)){
                urlParam = urlParam + "&keyword=" + keyword;
            }else {
                urlParam = "keyword=" + keyword;
            }
        }
        //获取筛选条件valueId
        if (valueId != null && valueId.length > 0) {
            for (String s : valueId) {
                if(!id.equals(s)){
                    urlParam = urlParam + "&valueId=" + s;
                }
            }
        }
        return urlParam;
    }


    /**
     * 将每次请求的参数拼接起来得到一个普通url
     * @param skuLsParam
     * @return
     */
    private String getUrlParam(SkuLsParam skuLsParam) {
        String[] valueId = skuLsParam.getValueId();
        String catalog3Id = skuLsParam.getCatalog3Id();
        String keyword = skuLsParam.getKeyword();

        String urlParam = "";
        //获取三级分类id
        if(StringUtils.isNotBlank(catalog3Id)){
            //如果urlParam为空第一个拼接的就不加 &
            if(StringUtils.isNotBlank(urlParam)){
                urlParam = urlParam + "&catalog3Id=" + catalog3Id;
            }else {
                urlParam =  "catalog3Id=" + catalog3Id;
            }
        }
        //获取输入内容
        if(StringUtils.isNotBlank(keyword)){
            if(StringUtils.isNotBlank(urlParam)){
                urlParam = urlParam + "&keyword=" + keyword;
            }else {
                urlParam = "keyword=" + keyword;
            }
        }
        //获取筛选条件valueId
        if (valueId != null && valueId.length > 0) {
            for (String s : valueId) {
                urlParam = urlParam + "&valueId=" + s;
            }
        }
        return urlParam;
    }


    /**
     * 查询平台属性
     * @param skuLsInfos
     * @return
     */
    private List<BaseAttrInfo> getAttrValueIds(List<SkuLsInfo> skuLsInfos) {
        //Set集合特性是;数据无序且不重复
        Set<String> valueIds = new  HashSet<>();

        for (SkuLsInfo skuLsInfo : skuLsInfos) {
            List<SkuLsAttrValue> skuAttrValueList = skuLsInfo.getSkuAttrValueList();
            for (SkuLsAttrValue skuLsAttrValue : skuAttrValueList) {
                String valueId = skuLsAttrValue.getValueId();
                valueIds.add(valueId);
            }
        }

        //根据去重后的id集合检索，关联到的平台属性列表
        List<BaseAttrInfo> baseAttrInfos = attrService.getAttrListByValueId(valueIds);

        return baseAttrInfos;
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

}
