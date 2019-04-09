package com.neusoft.interfaces;

import com.neusoft.javabean.po.*;

import java.util.List;

public interface AttrService {
    // 查询一级分类的所有信息
    List<BaseCatalog1> selectBaseCatalog1();
    //通过一级分类的id查询二级分类
    List<BaseCatalog2> selectCatalog2ByCatalog1(Long cataLog1);
    //通过二级分类的id查询三级分类
    List<BaseCatalog3> selectCatalog3ByCatalog2(Long cataLog2);
    //通过三级分类的id查询三属性表
    List<BaseAttrInfo> selectAttrInfoByCatalog3(Long cataLog3);

    //添加属性跟属性值
    int insertAttr(BaseAttrInfo baseAttrInfo);

    //删除属性
    void deleteAttr(Long attrInfoId);

    //根据属性id查询对应的属性值
    List<BaseAttrValue> selectAttrValueByAttrId(Long attrInfoId);

    //修改属性
    void updateAttr(BaseAttrInfo baseAttrInfo);
}
