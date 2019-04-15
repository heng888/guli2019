package com.neusoft.guli.po;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order_log")
public class OrderLog implements Serializable {
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
     * 发生时间
     */
    @Column(name = "operate_date")
    private Date operateDate;

    /**
     * 状态描述
     */
    @Column(name = "log_comment")
    private String logComment;

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
     * 获取发生时间
     *
     * @return operate_date - 发生时间
     */
    public Date getOperateDate() {
        return operateDate;
    }

    /**
     * 设置发生时间
     *
     * @param operateDate 发生时间
     */
    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    /**
     * 获取状态描述
     *
     * @return log_comment - 状态描述
     */
    public String getLogComment() {
        return logComment;
    }

    /**
     * 设置状态描述
     *
     * @param logComment 状态描述
     */
    public void setLogComment(String logComment) {
        this.logComment = logComment == null ? null : logComment.trim();
    }
}