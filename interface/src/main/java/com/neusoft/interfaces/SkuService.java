package com.neusoft.interfaces;

import com.neusoft.javabean.po.SkuInfo;

import java.util.List;

public interface SkuService {
    List<SkuInfo> getSkuListBySpu(Long spuId);
    //查询skuInfo相关的spuImage、spuAttrValue、spuSaleAttrValue
    List<SkuInfo> selectSkuInfoBySpuId(Long spuId);

    //新增skuInfo
    void saveSku(SkuInfo skuInfo);
    //删除sku
    void deleteSkuInfo(Long skuId);



}
