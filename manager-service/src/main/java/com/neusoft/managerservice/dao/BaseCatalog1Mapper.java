package com.neusoft.managerservice.dao;


import com.neusoft.javabean.po.BaseCatalog1;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseCatalog1Mapper extends Mapper<BaseCatalog1> {

    //查询所有的分类的集合的JSON对象
    List<BaseCatalog1> selectAllCatalog();
}