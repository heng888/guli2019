package com.neusoft.managerservice.dao;


import com.neusoft.javabean.po.SpuSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpuSaleAttrMapper extends Mapper<SpuSaleAttr> {

    //根据spuId查询查询SpuSaleAttr跟SpuSaleAttrValue
    List<SpuSaleAttr> selectSaleAttrAndAttrValueByspuId(Long spuId);


}