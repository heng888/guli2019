package com.neusoft.interfaces;

import com.neusoft.javabean.po.*;

import java.util.List;
import java.util.Map;

public interface SpuService {
    //通过三级分类查询所有的销售属性
    List<SpuInfo> selectSpuList(Long catalog3Id);

    //查询所有的销售属性
    List<BaseSaleAttr> baseSaleAttrList();

    //添加spu属性及spu销售属性值
    void saveSpu(SpuInfo spuInfo);

    //根据spuId查询spuImage信息
    List<SpuImage> selectSpuImageBySpuId(Long spuId);

    //删除spu跟关联的spuImage、spuAttr、spuAttrValue
    void deleteSpuInfo(Long spuId);

    //根据spuId查询SpuSaleAttr信息
    List<SpuSaleAttr> selectSpuSaleAttrBySpuId(Long spuId);

    //根据saleAttrId和spuid查询SpuSaleAttrValue
    List<SpuSaleAttrValue> spuSaleAttrValueBySaleAttrId(SpuSaleAttrValue spuSaleAttrValue);

    //根据spuId查询查询SpuSaleAttr跟SpuSaleAttrValue
    List<SpuSaleAttr> selectSaleAttrAndAttrValueByspuId(Long spuId);

    //修改Spu
    void updateSpu(SpuInfo spuInfo);

    //根据spuId查询spu相关的Attr  AttrValue
    List<SpuSaleAttr> selectSpuAll(Long spuId);


    //根据spuId和skuId查询查询SpuSaleAttr跟SpuSaleAttrValue
    List<SpuSaleAttr> spuSaleAttrListCheckBySku(Map map);
}
