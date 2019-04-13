package com.neusoft.managerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.neusoft.interfaces.SkuService;
import com.neusoft.javabean.po.SkuInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SkuController {

    @Reference
    private SkuService skuService;

    /**
     * 查询SkuInfo及相关的SkuImage、SkuAttrValue、SkuSaleAttrValue
     */
    @RequestMapping("getSkuListBySpu")
    @ResponseBody
    public List<SkuInfo> getSkuListBySpu(Long spuId){
        List<SkuInfo> skuInfos =skuService.selectSkuInfoBySpuId(spuId);
        return skuInfos;
    }


    /**
     * 新增skuInfo
     */
    @RequestMapping("saveSku")
    @ResponseBody
    public String saveSku(SkuInfo skuInfo){
        skuService.saveSku(skuInfo);
        return "新增成功";
    }
}
