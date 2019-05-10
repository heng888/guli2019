package com.neusoft.managerservice;



import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.neusoft.interfaces.AttrService;

import com.neusoft.javabean.po.*;
import com.neusoft.managerservice.dao.SkuAttrValueMapper;
import com.neusoft.managerservice.dao.SkuImageMapper;
import com.neusoft.managerservice.dao.SkuInfoMapper;
import com.neusoft.managerservice.dao.SkuSaleAttrValueMapper;
import com.neusoft.managerservice.serviceimpl.AttrServiceImpl;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerServiceApplicationTests {

    @Reference
    AttrService attrService;

    @Autowired
    JestClient jestClient;


    @Autowired
    SkuInfoMapper skuInfoMapper;
    @Autowired
    SkuImageMapper skuImageMapper;
    @Autowired
    SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    SkuSaleAttrValueMapper skuSaleAttrValueMapper;


    @Test
    public void getCatalogJSON() throws IOException {
        List<BaseCatalog1> baseCatalog1s = attrService.selectAllCatalog();
        JSONObject jsonObject = new JSONObject();
        HashMap<String, List<BaseCatalog2>> map = new HashMap<>();
        for (BaseCatalog1 baseCatalog1 : baseCatalog1s) {
            Long catalog1Id = baseCatalog1.getId();
            List<BaseCatalog2> catalog2List = baseCatalog1.getCatalog2List();
            jsonObject.put(catalog1Id.toString(),catalog2List);
        }
        String jsonString = jsonObject.toJSONString();
        File file = new File("D:\\IDEA_Project\\guli201904\\manager-service\\src\\main\\resources\\static\\catalog.json");

        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream stream = new FileOutputStream(file);
        stream.write(jsonString.getBytes("utf-8"));
        stream.close();
    }


    @Test
    public void contextLoads() {
    }

    /**
     *     将sql中的数据存放到elastic中
     */
    @Test
    public void toElastic(SkuInfo skuInfo) {

        Long l = 64l;
        SkuInfo skuInfo1 = skuInfoMapper.selectByPrimaryKey(l);
        SkuAttrValue skuAttrValue = new SkuAttrValue();
        skuAttrValue.setSkuId(l);
        List<SkuAttrValue> skuAttrValueList1 = skuAttrValueMapper.select(skuAttrValue);
        skuInfo1.setSkuAttrValueList(skuAttrValueList1);
        toElastic(skuInfo1);

        // 转化es中的sku信息
        SkuLsInfo skuLsInfo = new SkuLsInfo();
        try {
            BeanUtils.copyProperties(skuLsInfo,skuInfo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        String id = skuLsInfo.getId();

        Index build = new Index.Builder(skuLsInfo).index("guli2019").type("SkuLsInfo").id(id).build();

        try {
            jestClient.execute(build);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}