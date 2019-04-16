package com.neusoft.managerservice.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.neusoft.interfaces.SpuService;
import com.neusoft.javabean.po.*;
import com.neusoft.managerservice.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Autowired
    SpuImageMapper spuImageMapper;



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
     * 添加spu属性表、spu销售属性表、spu销售属性值表、skuImage表
     */
    @Override
    public void saveSpu(SpuInfo spuInfo) {
        //插入spuInfo
        spuInfoMapper.insertSelective(spuInfo);
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
            spuSaleAttr.setSpuId(spuInfo.getId());
            //插入SpuSaleAttr
            spuSaleAttrMapper.insertSelective(spuSaleAttr);
            List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                spuSaleAttrValue.setSaleAttrId(spuSaleAttr.getSaleAttrId());
                spuSaleAttrValue.setSpuId(spuInfo.getId());
                //插入SpuSaleAttrValue
                spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
            }
        }

        //插入SpuImage
        List<SpuImage> imageList = spuInfo.getSpuImageList();
        for (SpuImage spuImage : imageList) {
            spuImage.setSpuId(spuInfo.getId());
            spuImageMapper.insertSelective(spuImage);
        }
    }

    /**
     * 根据spuId查询spuImage信息
     */
    @Override
    public List<SpuImage> selectSpuImageBySpuId(Long spuId) {
        SpuImage spuImage = new SpuImage();
        spuImage.setSpuId(spuId);
        return spuImageMapper.select(spuImage);
    }

    /**
     * 删除spu跟关联的spuImage、spuAttr、spuAttrValue
     */
    @Override
    public void deleteSpuInfo(Long spuId) {
        //根据spuid删除SpuImage
        SpuImage spuImage = new SpuImage();
        spuImage.setSpuId(spuId);
        spuImageMapper.delete(spuImage);
        //根据spuId删除SpuSaleAttrValue
        SpuSaleAttrValue spuSaleAttrValue = new SpuSaleAttrValue();
        spuSaleAttrValue.setSpuId(spuId);
        spuSaleAttrValueMapper.delete(spuSaleAttrValue);
        //根据spuId删除SpuSaleAttr
        SpuSaleAttr spuSaleAttr = new SpuSaleAttr();
        spuSaleAttr.setSpuId(spuId);
        spuSaleAttrMapper.delete(spuSaleAttr);
        //根据spuId删除SpuInfo
        spuInfoMapper.deleteByPrimaryKey(spuId);
    }

    /**
     * 根据spuId查询SpuSaleAttr信息
     */
    @Override
    public List<SpuSaleAttr> selectSpuSaleAttrBySpuId(Long spuId) {
        SpuSaleAttr spuSaleAttr = new SpuSaleAttr();
        spuSaleAttr.setSpuId(spuId);
        return spuSaleAttrMapper.select(spuSaleAttr);
    }

    /**
     * 根据saleAttrId和spuid查询SpuSaleAttrValue
     */
    @Override
    public List<SpuSaleAttrValue> spuSaleAttrValueBySaleAttrId(SpuSaleAttrValue spuSaleAttrValue) {
        return spuSaleAttrValueMapper.select(spuSaleAttrValue);
    }

    //根据spuId查询查询SpuSaleAttr跟SpuSaleAttrValue
    @Override
    public List<SpuSaleAttr> selectSaleAttrAndAttrValueByspuId(Long spuId) {
        return spuSaleAttrMapper.selectSaleAttrAndAttrValueByspuId(spuId);
    }

    /**
     * 修改spu属性表、spu销售属性表、spu销售属性值表、skuImage表
     */
    @Override
    public void updateSpu(SpuInfo spuInfo) {
        //修改SpuInfo
        spuInfoMapper.updateByPrimaryKey(spuInfo);
        Long spuId = spuInfo.getId();

        //修改SpuImage(先删除在插入)
        SpuImage spuImage = new SpuImage();
        spuImage.setSpuId(spuId);
        spuImageMapper.delete(spuImage);
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        for (SpuImage image : spuImageList) {
            image.setSpuId(spuId);
            spuImageMapper.insert(image);
        }

        //修改SpuSaleAttrValue(先删除在插入)
        //根据spuId删除SpuSaleAttrValue
        SpuSaleAttrValue spuSaleAttrValue1 = new SpuSaleAttrValue();
        spuSaleAttrValue1.setSpuId(spuId);
        spuSaleAttrValueMapper.delete(spuSaleAttrValue1);

        //修改SpuSaleAttr(先删除在插入)
        //根据spuId删除SpuSaleAttr
        SpuSaleAttr spuSaleAttr = new SpuSaleAttr();
        spuSaleAttr.setSpuId(spuId);
        spuSaleAttrMapper.delete(spuSaleAttr);

        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr saleAttr : spuSaleAttrList) {
            saleAttr.setSpuId(spuId);
            //插入SpuSaleAttr
            spuSaleAttrMapper.insert(saleAttr);
            List<SpuSaleAttrValue> spuSaleAttrValueList = saleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                spuSaleAttrValue.setSpuId(spuId);
                //插入SpuSaleAttrValue
                spuSaleAttrValueMapper.insert(spuSaleAttrValue);
            }
        }
    }

    //无用
    @Override
    public List<SpuSaleAttr> selectSpuAll(Long spuId) {
        return spuSaleAttrMapper.selectSaleAttrAndAttrValueByspuId(spuId);
    }


    /**
     * 根据spuId和skuId查询SpuSaleAttr
     * @param map
     * @return
     */
    @Override
    public List<SpuSaleAttr> spuSaleAttrListCheckBySku(Map map) {
        return spuSaleAttrMapper.spuSaleAttrListCheckBySku(map);
    }
}
