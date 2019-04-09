package com.neusoft.guli.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order_info")
public class OrderInfo implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 收件人电话
     */
    @Column(name = "consignee_tel")
    private String consigneeTel;

    /**
     * 总金额
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 订单状态
     */
    @Column(name = "order_status")
    private String orderStatus;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 付款方式
     */
    @Column(name = "payment_way")
    private String paymentWay;

    /**
     * 送货地址
     */
    @Column(name = "delivery_address")
    private String deliveryAddress;

    /**
     * 订单备注
     */
    @Column(name = "order_comment")
    private String orderComment;

    /**
     * 订单交易编号（第三方支付用)
     */
    @Column(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 订单描述(第三方支付用)
     */
    @Column(name = "trade_body")
    private String tradeBody;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 失效时间
     */
    @Column(name = "expire_time")
    private Date expireTime;

    /**
     * 进度状态
     */
    @Column(name = "process_status")
    private String processStatus;

    /**
     * 物流单编号
     */
    @Column(name = "tracking_no")
    private String trackingNo;

    /**
     * 父订单编号
     */
    @Column(name = "parent_order_id")
    private Long parentOrderId;

    /**
     * 图片路径
     */
    @Column(name = "img_url")
    private String imgUrl;

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
     * 获取收货人
     *
     * @return consignee - 收货人
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * 设置收货人
     *
     * @param consignee 收货人
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    /**
     * 获取收件人电话
     *
     * @return consignee_tel - 收件人电话
     */
    public String getConsigneeTel() {
        return consigneeTel;
    }

    /**
     * 设置收件人电话
     *
     * @param consigneeTel 收件人电话
     */
    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel == null ? null : consigneeTel.trim();
    }

    /**
     * 获取总金额
     *
     * @return total_amount - 总金额
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置总金额
     *
     * @param totalAmount 总金额
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取订单状态
     *
     * @return order_status - 订单状态
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态
     *
     * @param orderStatus 订单状态
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
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
     * 获取付款方式
     *
     * @return payment_way - 付款方式
     */
    public String getPaymentWay() {
        return paymentWay;
    }

    /**
     * 设置付款方式
     *
     * @param paymentWay 付款方式
     */
    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay == null ? null : paymentWay.trim();
    }

    /**
     * 获取送货地址
     *
     * @return delivery_address - 送货地址
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * 设置送货地址
     *
     * @param deliveryAddress 送货地址
     */
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress == null ? null : deliveryAddress.trim();
    }

    /**
     * 获取订单备注
     *
     * @return order_comment - 订单备注
     */
    public String getOrderComment() {
        return orderComment;
    }

    /**
     * 设置订单备注
     *
     * @param orderComment 订单备注
     */
    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment == null ? null : orderComment.trim();
    }

    /**
     * 获取订单交易编号（第三方支付用)
     *
     * @return out_trade_no - 订单交易编号（第三方支付用)
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设置订单交易编号（第三方支付用)
     *
     * @param outTradeNo 订单交易编号（第三方支付用)
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    /**
     * 获取订单描述(第三方支付用)
     *
     * @return trade_body - 订单描述(第三方支付用)
     */
    public String getTradeBody() {
        return tradeBody;
    }

    /**
     * 设置订单描述(第三方支付用)
     *
     * @param tradeBody 订单描述(第三方支付用)
     */
    public void setTradeBody(String tradeBody) {
        this.tradeBody = tradeBody == null ? null : tradeBody.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取失效时间
     *
     * @return expire_time - 失效时间
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 设置失效时间
     *
     * @param expireTime 失效时间
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 获取进度状态
     *
     * @return process_status - 进度状态
     */
    public String getProcessStatus() {
        return processStatus;
    }

    /**
     * 设置进度状态
     *
     * @param processStatus 进度状态
     */
    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus == null ? null : processStatus.trim();
    }

    /**
     * 获取物流单编号
     *
     * @return tracking_no - 物流单编号
     */
    public String getTrackingNo() {
        return trackingNo;
    }

    /**
     * 设置物流单编号
     *
     * @param trackingNo 物流单编号
     */
    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo == null ? null : trackingNo.trim();
    }

    /**
     * 获取父订单编号
     *
     * @return parent_order_id - 父订单编号
     */
    public Long getParentOrderId() {
        return parentOrderId;
    }

    /**
     * 设置父订单编号
     *
     * @param parentOrderId 父订单编号
     */
    public void setParentOrderId(Long parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

    /**
     * 获取图片路径
     *
     * @return img_url - 图片路径
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片路径
     *
     * @param imgUrl 图片路径
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
}