package com.neusoft.javabean.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "ware_order_task_detail")
public class WareOrderTaskDetail implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * sku_id
     */
    @Column(name = "sku_id")
    private Long skuId;

    /**
     * sku名称
     */
    @Column(name = "sku_name")
    private String skuName;

    /**
     * 购买个数
     */
    @Column(name = "sku_num")
    private Integer skuNum;

    /**
     * 工作单编号
     */
    @Column(name = "task_id")
    private Long taskId;

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
     * 获取购买个数
     *
     * @return sku_num - 购买个数
     */
    public Integer getSkuNum() {
        return skuNum;
    }

    /**
     * 设置购买个数
     *
     * @param skuNum 购买个数
     */
    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }

    /**
     * 获取工作单编号
     *
     * @return task_id - 工作单编号
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * 设置工作单编号
     *
     * @param taskId 工作单编号
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}