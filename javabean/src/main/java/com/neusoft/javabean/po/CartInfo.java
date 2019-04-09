package com.neusoft.javabean.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "cart_info")
public class CartInfo implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * skuid
     */
    @Column(name = "sku_id")
    private Long skuId;

    /**
     * 放入购物车时价格
     */
    @Column(name = "cart_price")
    private BigDecimal cartPrice;

    /**
     * 数量
     */
    @Column(name = "sku_num")
    private Integer skuNum;

    /**
     * 图片文件
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * sku名称 (冗余)
     */
    @Column(name = "sku_name")
    private String skuName;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取skuid
     *
     * @return sku_id - skuid
     */
    public Long getSkuId() {
        return skuId;
    }

    /**
     * 设置skuid
     *
     * @param skuId skuid
     */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取放入购物车时价格
     *
     * @return cart_price - 放入购物车时价格
     */
    public BigDecimal getCartPrice() {
        return cartPrice;
    }

    /**
     * 设置放入购物车时价格
     *
     * @param cartPrice 放入购物车时价格
     */
    public void setCartPrice(BigDecimal cartPrice) {
        this.cartPrice = cartPrice;
    }

    /**
     * 获取数量
     *
     * @return sku_num - 数量
     */
    public Integer getSkuNum() {
        return skuNum;
    }

    /**
     * 设置数量
     *
     * @param skuNum 数量
     */
    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }

    /**
     * 获取图片文件
     *
     * @return img_url - 图片文件
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片文件
     *
     * @param imgUrl 图片文件
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * 获取sku名称 (冗余)
     *
     * @return sku_name - sku名称 (冗余)
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * 设置sku名称 (冗余)
     *
     * @param skuName sku名称 (冗余)
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }
}