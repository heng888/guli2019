package com.neusoft.itemweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.neusoft.interfaces.SkuService;
import com.neusoft.interfaces.SpuService;
import com.neusoft.javabean.po.SkuInfo;
import com.neusoft.javabean.po.SkuSaleAttrValue;
import com.neusoft.javabean.po.SpuSaleAttr;
import com.neusoft.javabean.po.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ItemController {

    @Reference
    SkuService skuService;
    @Reference
    SpuService spuService;

    /**
     * 查询skuInfo信息
     * @param skuId
     * @param map
     * @return
     */
    @RequestMapping("{skuId}.html")
    public String item(@PathVariable("skuId") Long skuId, ModelMap map){
        //查询SkuInfo
        SkuInfo skuInfo = skuService.getSkuInfoBySkuId(skuId);
        map.put("skuInfo",skuInfo);
        Long spuId = skuInfo.getSpuId();
        /*List<SpuSaleAttr> spuSaleAttrs = spuService.selectSaleAttrAndAttrValueByspuId(spuId);
        map.put("spuSaleAttrListCheckBySku",spuSaleAttrs);*/
        //查询SpuSaleAttr
        Map<String, Long> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("skuId",skuId);
        stringStringHashMap.put("spuId",spuId);
        List<SpuSaleAttr> spuSaleAttrs = spuService.spuSaleAttrListCheckBySku(stringStringHashMap);
        map.put("spuSaleAttrListCheckBySku",spuSaleAttrs);

        //根据spuId查询相关的SkuInfo集合
        List<SkuInfo> skuInfos = skuService.selectSkuSaleAttrValueListBySpuId(spuId);
        HashMap<String, Long> hashMap = new HashMap<>();
        //将skuInfo的skuId当做map的值，skuSaleAttrValue的组合当做键放入map
        for (SkuInfo info : skuInfos) {
            String k="";
            List<SkuSaleAttrValue> skuSaleAttrValueList = info.getSkuSaleAttrValueList();
            for (SkuSaleAttrValue saleAttrValue : skuSaleAttrValueList) {
                k=k+"|"+saleAttrValue.getSaleAttrValueId();
            }
            hashMap.put(k,info.getId());
        }
        //将hashMap对象转换为json对象
        String skuJson = JSON.toJSONString(hashMap);
        map.put("skuJson",skuJson);
        return "item";
    }


    @RequestMapping("toIndex")
    public String index(ModelMap map){
        map.put("hello","Hello");
        List<UserInfo> users = new ArrayList<UserInfo>();
        for (int i = 0; i <5 ; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName("小小"+i);
            userInfo.setPasswd("123456"+1);
            users.add(userInfo);
        }
        map.put("users",users);
        return "index";
    }
}
