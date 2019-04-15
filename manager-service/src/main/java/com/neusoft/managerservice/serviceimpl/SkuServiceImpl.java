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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
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

        //插入SkuImage
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        for (SkuImage skuImage : skuImageList) {
            skuImage.setSkuId(skuInfoId);
            skuImageMapper.insert(skuImage);
        }

        //插入SkuSaleAttrValue
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
            skuSaleAttrValue.setSkuId(skuInfoId);
            skuSaleAttrValueMapper.insert(skuSaleAttrValue);
        }
    }


    /**
     * 删除sku
     * @param skuId
     */
    @Override
    public void deleteSkuInfo(Long skuId) {
        //删除SkuImage
        SkuImage skuImage = new SkuImage();
        skuImage.setSkuId(skuId);
        skuImageMapper.delete(skuImage);

        //删除SkuAttrValue
        SkuAttrValue skuAttrValue = new SkuAttrValue();
        skuAttrValue.setSkuId(skuId);
        skuAttrValueMapper.delete(skuAttrValue);

        //删除SkuSaleAttrValue
        SkuSaleAttrValue skuSaleAttrValue = new SkuSaleAttrValue();
        skuSaleAttrValue.setSkuId(skuId);
        skuSaleAttrValueMapper.delete(skuSaleAttrValue);

        //删除SkuInfo
        skuInfoMapper.deleteByPrimaryKey(skuId);
    }

    /**
     * 修改SkuInfo
     * @param skuInfo
     */
    @Override
    public void updateSkuInfo(SkuInfo skuInfo) {
        //修改SkuInfo
        skuInfoMapper.updateByPrimaryKey(skuInfo);
        Long skuInfoId = skuInfo.getId();
        //先根据skuId删除SkuImage
        SkuImage skuImage = new SkuImage();
        skuImage.setSkuId(skuInfoId);
        skuImageMapper.delete(skuImage);

        //先根据skuId删除SkuAttrValue
        SkuAttrValue skuAttrValue = new SkuAttrValue();
        skuAttrValue.setSkuId(skuInfoId);
        skuAttrValueMapper.delete(skuAttrValue);

        //先根据skuId删除SkuSaleAttrValue
        SkuSaleAttrValue skuSaleAttrValue = new SkuSaleAttrValue();
        skuSaleAttrValue.setSkuId(skuInfoId);
        skuSaleAttrValueMapper.delete(skuSaleAttrValue);


        //插入SkuAttrValue
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        for (SkuAttrValue skuAttrValue1 : skuAttrValueList) {
            skuAttrValue1.setSkuId(skuInfoId);
            skuAttrValueMapper.insert(skuAttrValue1);
        }

        //插入SkuImage
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        for (SkuImage skuImage1 : skuImageList) {
            skuImage1.setSkuId(skuInfoId);
            skuImageMapper.insert(skuImage1);
        }

        //插入SkuSaleAttrValue
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        for (SkuSaleAttrValue skuSaleAttrValue1 : skuSaleAttrValueList) {
            skuSaleAttrValue1.setSkuId(skuInfoId);
            skuSaleAttrValueMapper.insert(skuSaleAttrValue1);
        }
    }


}
