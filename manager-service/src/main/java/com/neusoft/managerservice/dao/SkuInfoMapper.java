package com.neusoft.managerservice.dao;


import com.neusoft.javabean.po.SkuInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SkuInfoMapper extends Mapper<SkuInfo> {
    //查询skuInfo相关的spuImage、spuAttrValue、spuSaleAttrValue
    List<SkuInfo> selectSkuInfoBySpuId(Long spuId);
}