package com.neusoft.javabean.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "sku_image")
public class SkuImage implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 商品id
     */
    @Column(name = "sku_id")
    private Long skuId;

    /**
     * 图片名称（冗余）
     */
    @Column(name = "img_name")
    private String imgName;

    /**
     * 图片路径(冗余)
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 商品图片id
     */
    @Column(name = "spu_img_id")
    private Long spuImgId;

    /**
     * 是否默认
     */
    @Column(name = "is_default")
    private String isDefault;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商品id
     *
     * @return sku_id - 商品id
     */
    public Long getSkuId() {
        return skuId;
    }

    /**
     * 设置商品id
     *
     * @param skuId 商品id
     */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取图片名称（冗余）
     *
     * @return img_name - 图片名称（冗余）
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * 设置图片名称（冗余）
     *
     * @param imgName 图片名称（冗余）
     */
    public void setImgName(String imgName) {
        this.imgName = imgName == null ? null : imgName.trim();
    }

    /**
     * 获取图片路径(冗余)
     *
     * @return img_url - 图片路径(冗余)
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片路径(冗余)
     *
     * @param imgUrl 图片路径(冗余)
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * 获取商品图片id
     *
     * @return spu_img_id - 商品图片id
     */
    public Long getSpuImgId() {
        return spuImgId;
    }

    /**
     * 设置商品图片id
     *
     * @param spuImgId 商品图片id
     */
    public void setSpuImgId(Long spuImgId) {
        this.spuImgId = spuImgId;
    }

    /**
     * 获取是否默认
     *
     * @return is_default - 是否默认
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否默认
     *
     * @param isDefault 是否默认
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }
}