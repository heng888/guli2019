package com.neusoft.javabean.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "sku_info")
public class SkuInfo implements Serializable {
    /**
     * 库存id(itemID)
     */
    @Id
    private Long id;

    /**
     * 商品id
     */
    @Column(name = "spu_id")
    private Long spuId;

    /**
     * 价格
     */
    private Long price;

    /**
     * sku名称
     */
    @Column(name = "sku_name")
    private String skuName;

    /**
     * 商品规格描述
     */
    @Column(name = "sku_desc")
    private String skuDesc;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 品牌(冗余)
     */
    @Column(name = "tm_id")
    private Long tmId;

    /**
     * 三级分类id（冗余)
     */
    @Column(name = "catalog3_id")
    private Long catalog3Id;

    /**
     * 默认显示图片(冗余)
     */
    @Column(name = "sku_default_img")
    private String skuDefaultImg;

    /**
     * 获取库存id(itemID)
     *
     * @return id - 库存id(itemID)
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置库存id(itemID)
     *
     * @param id 库存id(itemID)
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商品id
     *
     * @return spu_id - 商品id
     */
    public Long getSpuId() {
        return spuId;
    }

    /**
     * 设置商品id
     *
     * @param spuId 商品id
     */
    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 获取sku名称
     *
     * @return sku_name - sku名称
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * 设置sku名称
     *
     * @param skuName sku名称
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    /**
     * 获取商品规格描述
     *
     * @return sku_desc - 商品规格描述
     */
    public String getSkuDesc() {
        return skuDesc;
    }

    /**
     * 设置商品规格描述
     *
     * @param skuDesc 商品规格描述
     */
    public void setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc == null ? null : skuDesc.trim();
    }

    /**
     * 获取重量
     *
     * @return weight - 重量
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * 设置重量
     *
     * @param weight 重量
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * 获取品牌(冗余)
     *
     * @return tm_id - 品牌(冗余)
     */
    public Long getTmId() {
        return tmId;
    }

    /**
     * 设置品牌(冗余)
     *
     * @param tmId 品牌(冗余)
     */
    public void setTmId(Long tmId) {
        this.tmId = tmId;
    }

    /**
     * 获取三级分类id（冗余)
     *
     * @return catalog3_id - 三级分类id（冗余)
     */
    public Long getCatalog3Id() {
        return catalog3Id;
    }

    /**
     * 设置三级分类id（冗余)
     *
     * @param catalog3Id 三级分类id（冗余)
     */
    public void setCatalog3Id(Long catalog3Id) {
        this.catalog3Id = catalog3Id;
    }

    /**
     * 获取默认显示图片(冗余)
     *
     * @return sku_default_img - 默认显示图片(冗余)
     */
    public String getSkuDefaultImg() {
        return skuDefaultImg;
    }

    /**
     * 设置默认显示图片(冗余)
     *
     * @param skuDefaultImg 默认显示图片(冗余)
     */
    public void setSkuDefaultImg(String skuDefaultImg) {
        this.skuDefaultImg = skuDefaultImg == null ? null : skuDefaultImg.trim();
    }
}