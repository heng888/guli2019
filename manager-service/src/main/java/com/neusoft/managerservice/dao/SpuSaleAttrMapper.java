package com.neusoft.managerservice.dao;


import com.neusoft.javabean.po.SpuSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SpuSaleAttrMapper extends Mapper<SpuSaleAttr> {

    //根据spuId查询查询SpuSaleAttr跟SpuSaleAttrValue
    List<SpuSaleAttr> selectSaleAttrAndAttrValueByspuId(Long spuId);

    //根据spuId和skuId查询查询SpuSaleAttr跟SpuSaleAttrValue
    List<SpuSaleAttr> spuSaleAttrListCheckBySku(Map map);

}