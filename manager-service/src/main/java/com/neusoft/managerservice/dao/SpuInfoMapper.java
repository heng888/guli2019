package com.neusoft.managerservice.dao;


import com.neusoft.javabean.po.SpuInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpuInfoMapper extends Mapper<SpuInfo> {

    //根据spuId查询spu相关的Attr  AttrValue
    List<SpuInfo> selectSpuAll(Long spuId);
}