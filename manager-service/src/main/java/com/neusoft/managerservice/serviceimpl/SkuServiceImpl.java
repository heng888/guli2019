package com.neusoft.managerservice.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.neusoft.interfaces.SkuService;
import com.neusoft.javabean.po.SkuAttrValue;
import com.neusoft.javabean.po.SkuImage;
import com.neusoft.javabean.po.SkuInfo;
import com.neusoft.javabean.po.SkuSaleAttrValue;
import com.neusoft.managerservice.dao.SkuAttrValueMapper;
import com.neusoft.managerservice.dao.SkuImageMapper;
import com.neusoft.managerservice.dao.SkuInfoMapper;
import com.neusoft.managerservice.dao.SkuSaleAttrValueMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    SkuInfoMapper skuInfoMapper;
    @Autowired
    SkuImageMapper skuImageMapper;
    @Autowired
    SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    SkuSaleAttrValueMapper skuSaleAttrValueMapper;


    /**
     * 查询skuInfo信息
     * @param spuId
     * @return
     */
    @Override
    public List<SkuInfo> getSkuListBySpu(Long spuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setSpuId(spuId);
        List<SkuInfo> skuInfos = skuInfoMapper.select(skuInfo);
        return skuInfos;
    }

    /**
     * 查询SkuInfo及相关的SkuImage、SkuAttrValue、SkuSaleAttrValue
     */
    @Override
    public List<SkuInfo> selectSkuInfoBySpuId(Long spuId) {
        return skuInfoMapper.selectSkuInfoBySpuId(spuId);
    }

    /**
     * 新增skuInfo
     */
    @Override
    public void saveSku(SkuInfo skuInfo) {
        //插入SkuInfo
        skuInfoMapper.insert(skuInfo);
        Long skuInfoId = skuInfo.getId();
        //插入SkuAttrValue
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        for (SkuAttrValue skuAttrValue : skuAttrValueList) {
            skuAttrValue.setSkuId(skuInfoId);
            skuAttrValueMapper.insert(skuAttrValue);
        }
        //插入SkuSaleAttrValue
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
            skuSaleAttrValue.setSkuId(skuInfoId);
            skuSaleAttrValueMapper.insert(skuSaleAttrValue);
        }
        //插入SkuImage
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        for (SkuImage skuImage : skuImageList) {
            skuImage.setSkuId(skuInfoId);
            skuImageMapper.insert(skuImage);
        }
    }
}
