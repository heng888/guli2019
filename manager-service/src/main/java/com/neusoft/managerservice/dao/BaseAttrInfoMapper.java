package com.neusoft.managerservice.dao;


import com.neusoft.javabean.po.BaseAttrInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface BaseAttrInfoMapper extends Mapper<BaseAttrInfo> {

    //根据valueIds查询BaseAttrInfo
    List<BaseAttrInfo> getAttrListByValueId(@Param("ids") String valueIds);
}