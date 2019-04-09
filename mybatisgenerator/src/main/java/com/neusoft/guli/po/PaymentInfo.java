package com.neusoft.guli.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "payment_info")
public class PaymentInfo implements Serializable {
    /**
     * 编号
     */
    @Id
    private Integer id;

    /**
     * 对外业务编号
     */
    @Column(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 订单编号
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 支付宝交易编号
     */
    @Column(name = "alipay_trade_no")
    private String alipayTradeNo;

    /**
     * 支付金额
     */
    @Column(name = "total_amount")
    private Long totalAmount;

    /**
     * 交易内容
     */
    private String subject;

    /**
     * 支付状态
     */
    @Column(name = "payment_status")
    private String paymentStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 回调时间
     */
    @Column(name = "callback_time")
    private Date callbackTime;

    /**
     * 回调信息
     */
    @Column(name = "callback_content")
    private String callbackContent;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取对外业务编号
     *
     * @return out_trade_no - 对外业务编号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设置对外业务编号
     *
     * @param outTradeNo 对外业务编号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    /**
     * 获取订单编号
     *
     * @return order_id - 订单编号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单编号
     *
     * @param orderId 订单编号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 获取支付宝交易编号
     *
     * @return alipay_trade_no - 支付宝交易编号
     */
    public String getAlipayTradeNo() {
        return alipayTradeNo;
    }

    /**
     * 设置支付宝交易编号
     *
     * @param alipayTradeNo 支付宝交易编号
     */
    public void setAlipayTradeNo(String alipayTradeNo) {
        this.alipayTradeNo = alipayTradeNo == null ? null : alipayTradeNo.trim();
    }

    /**
     * 获取支付金额
     *
     * @return total_amount - 支付金额
     */
    public Long getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置支付金额
     *
     * @param totalAmount 支付金额
     */
    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取交易内容
     *
     * @return subject - 交易内容
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置交易内容
     *
     * @param subject 交易内容
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     * 获取支付状态
     *
     * @return payment_status - 支付状态
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * 设置支付状态
     *
     * @param paymentStatus 支付状态
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus == null ? null : paymentStatus.trim();
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
     * 获取回调时间
     *
     * @return callback_time - 回调时间
     */
    public Date getCallbackTime() {
        return callbackTime;
    }

    /**
     * 设置回调时间
     *
     * @param callbackTime 回调时间
     */
    public void setCallbackTime(Date callbackTime) {
        this.callbackTime = callbackTime;
    }

    /**
     * 获取回调信息
     *
     * @return callback_content - 回调信息
     */
    public String getCallbackContent() {
        return callbackContent;
    }

    /**
     * 设置回调信息
     *
     * @param callbackContent 回调信息
     */
    public void setCallbackContent(String callbackContent) {
        this.callbackContent = callbackContent == null ? null : callbackContent.trim();
    }
}