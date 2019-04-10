package com.neusoft.interfaces;

import com.neusoft.javabean.po.BaseSaleAttr;
import com.neusoft.javabean.po.SpuInfo;

import java.util.List;

public interface SpuService {
    //通过三级分类查询所有的销售属性
    List<SpuInfo> selectSpuList(Long catalog3Id);

    //查询所有的销售属性
    List<BaseSaleAttr> baseSaleAttrList();

    //添加spu属性及spu销售属性值
    void saveSpu(SpuInfo spuInfo);
}
