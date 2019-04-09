package com.neusoft.guli.po;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "order_detail")
public class OrderDetail implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 订单编号
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * sku_id
     */
    @Column(name = "sku_id")
    private Long skuId;

    /**
     * sku名称（冗余)
     */
    @Column(name = "sku_name")
    private String skuName;

    /**
     * 图片名称（冗余)
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 购买价格(下单时sku价格）
     */
    @Column(name = "order_price")
    private Long orderPrice;

    /**
     * 购买个数
     */
    @Column(name = "sku_num")
    private String skuNum;

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
     * 获取订单编号
     *
     * @return order_id - 订单编号
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单编号
     *
     * @param orderId 订单编号
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取sku_id
     *
     * @return sku_id - sku_id
     */
    public Long getSkuId() {
        return skuId;
    }

    /**
     * 设置sku_id
     *
     * @param skuId sku_id
     */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取sku名称（冗余)
     *
     * @return sku_name - sku名称（冗余)
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * 设置sku名称（冗余)
     *
     * @param skuName sku名称（冗余)
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    /**
     * 获取图片名称（冗余)
     *
     * @return img_url - 图片名称（冗余)
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片名称（冗余)
     *
     * @param imgUrl 图片名称（冗余)
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * 获取购买价格(下单时sku价格）
     *
     * @return order_price - 购买价格(下单时sku价格）
     */
    public Long getOrderPrice() {
        return orderPrice;
    }

    /**
     * 设置购买价格(下单时sku价格）
     *
     * @param orderPrice 购买价格(下单时sku价格）
     */
    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * 获取购买个数
     *
     * @return sku_num - 购买个数
     */
    public String getSkuNum() {
        return skuNum;
    }

    /**
     * 设置购买个数
     *
     * @param skuNum 购买个数
     */
    public void setSkuNum(String skuNum) {
        this.skuNum = skuNum == null ? null : skuNum.trim();
    }
}