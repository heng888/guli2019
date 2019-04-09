package com.neusoft.javabean.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "ware_sku")
public class WareSku implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * skuid
     */
    @Column(name = "sku_id")
    private Long skuId;

    /**
     * 仓库id
     */
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 库存数
     */
    private Integer stock;

    /**
     * 存货名称
     */
    @Column(name = "stock_name")
    private String stockName;

    /**
     * 锁定库存数
     */
    @Column(name = "stock_locked")
    private Integer stockLocked;

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
     * 获取仓库id
     *
     * @return warehouse_id - 仓库id
     */
    public Long getWarehouseId() {
        return warehouseId;
    }

    /**
     * 设置仓库id
     *
     * @param warehouseId 仓库id
     */
    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * 获取库存数
     *
     * @return stock - 库存数
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置库存数
     *
     * @param stock 库存数
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取存货名称
     *
     * @return stock_name - 存货名称
     */
    public String getStockName() {
        return stockName;
    }

    /**
     * 设置存货名称
     *
     * @param stockName 存货名称
     */
    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    /**
     * 获取锁定库存数
     *
     * @return stock_locked - 锁定库存数
     */
    public Integer getStockLocked() {
        return stockLocked;
    }

    /**
     * 设置锁定库存数
     *
     * @param stockLocked 锁定库存数
     */
    public void setStockLocked(Integer stockLocked) {
        this.stockLocked = stockLocked;
    }
}