package com.neusoft.managerservice.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.neusoft.interfaces.SpuService;
import com.neusoft.javabean.po.*;
import com.neusoft.managerservice.dao.BaseSaleAttrMapper;
import com.neusoft.managerservice.dao.SpuInfoMapper;
import com.neusoft.managerservice.dao.SpuSaleAttrMapper;
import com.neusoft.managerservice.dao.SpuSaleAttrValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpuServiceImpl implements SpuService {

    @Autowired
    SpuInfoMapper spuInfoMapper;

    @Autowired
    BaseSaleAttrMapper baseSaleAttrMapper;

    @Autowired
    SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    /**
     * 通过三级分类查询所有的销售属性
     */
    @Override
    public List<SpuInfo> selectSpuList(Long catalog3Id) {
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setCatalog3Id(catalog3Id);
        return spuInfoMapper.select(spuInfo);
    }

    /**
     * 查询所有的销售属性
     * @return
     */
    @Override
    public List<BaseSaleAttr> baseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }

    /**
     * 添加spu属性表、spu销售属性表、spu销售属性值表
     */
    @Override
    public void saveSpu(SpuInfo spuInfo) {
        spuInfoMapper.insertSelective(spuInfo);
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
            spuSaleAttr.setSpuId(spuInfo.getId());
            spuSaleAttrMapper.insertSelective(spuSaleAttr);
            List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                spuSaleAttrValue.setSaleAttrId(spuSaleAttr.getSaleAttrId());
                spuSaleAttrValue.setSpuId(spuSaleAttr.getSpuId());
                spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
            }
        }

    }
}
