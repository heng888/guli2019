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


    //修改skuInfo
    void updateSkuInfo(SkuInfo skuInfo);

    //根据skuId查询SkuInfo信息
    SkuInfo getSkuInfoBySkuId(Long skuId);


    //根据spuId查询相关的所有skuInfo
    List<SkuInfo> selectSkuSaleAttrValueListBySpuId(Long spuId);

    //通过三级分类id查询SkuInfo
    List<SkuInfo> getSkuListByCatalog3Id(Long catalog3Id);

    //通过键盘输入模糊查询SkuInfo
    List<SkuInfo> getSkuListBykeyWord(String keyword);
}
