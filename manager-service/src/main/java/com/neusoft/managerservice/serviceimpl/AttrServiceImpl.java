package com.neusoft.managerservice.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.neusoft.interfaces.AttrService;
import com.neusoft.javabean.po.*;
import com.neusoft.managerservice.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AttrServiceImpl implements AttrService {



    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;
    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;
    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;
    /**
     * 查询所有的一级分类
     */
    @Override
    public List<BaseCatalog1> selectBaseCatalog1() {
        return baseCatalog1Mapper.selectAll();
    }

    /**
     * 通过一级分类id查询对应的二级分类
     */
    @Override
    public List<BaseCatalog2> selectCatalog2ByCatalog1(Long cataLog1) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(cataLog1);
        return baseCatalog2Mapper.select(baseCatalog2);
    }

    /**
     * 通过二级分类id查询对应的三级分类
     */
    @Override
    public List<BaseCatalog3> selectCatalog3ByCatalog2(Long cataLog2) {
        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(cataLog2);
        return baseCatalog3Mapper.select(baseCatalog3);
    }

    /**
     * 通过三级id查询属性
     * @param cataLog3
     * @return
     */
    @Override
    public List<BaseAttrInfo> selectAttrInfoByCatalog3(Long cataLog3) {
        BaseAttrInfo attrInfo = new BaseAttrInfo();
        attrInfo.setCatalog3Id(cataLog3);
        return baseAttrInfoMapper.select(attrInfo);
    }

    /**
     * 插入属性
     * @param baseAttrInfo
     * @return
     */
    @Override
    public int insertAttr(BaseAttrInfo baseAttrInfo) {
        baseAttrInfoMapper.insertSelective(baseAttrInfo);
        List<BaseAttrValue> baseAttrValues = baseAttrInfo.getAttrValueList();
        System.out.println(baseAttrInfo.getId());
        for (BaseAttrValue baseAttrValue : baseAttrValues) {
            baseAttrValue.setAttrId(baseAttrInfo.getId());
            baseAttrValueMapper.insert(baseAttrValue);
        }
        return 1;
    }

    /**
     * 删除属性表和对应的属性值
     * @param attrInfoId
     */
    @Override
    public void deleteAttr(Long attrInfoId) {
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrInfoId);
        baseAttrValueMapper.delete(baseAttrValue);
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setId(attrInfoId);
        baseAttrInfoMapper.delete(baseAttrInfo);
    }

    /**
     * 根据属性id查询对应的属性值
     * @param attrInfoId
     * @return 属性值表集合
     */
    @Override
    public List<BaseAttrValue> selectAttrValueByAttrId(Long attrInfoId) {
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrInfoId);
        return baseAttrValueMapper.select(baseAttrValue);
    }

    /**
     * 修改属性
     * @param baseAttrInfo
     */
    @Override
    public void updateAttr(BaseAttrInfo baseAttrInfo) {
        baseAttrInfoMapper.updateByPrimaryKey(baseAttrInfo);
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValue);
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue attrValue : attrValueList) {
            attrValue.setAttrId(baseAttrInfo.getId());
            baseAttrValueMapper.insertSelective(attrValue);
        }
    }

}
