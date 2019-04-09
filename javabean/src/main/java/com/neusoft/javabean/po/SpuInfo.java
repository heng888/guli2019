package com.neusoft.javabean.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "spu_info")
public class SpuInfo implements Serializable {
    /**
     * 商品id
     */
    @Id
    private Long id;

    /**
     * 商品名称
     */
    @Column(name = "spu_name")
    private String spuName;

    /**
     * 商品描述(后台简述）
     */
    private String description;

    /**
     * 三级分类id
     */
    @Column(name = "catalog3_id")
    private Long catalog3Id;

    /**
     * 品牌id
     */
    @Column(name = "tm_id")
    private Long tmId;

    /**
     * 获取商品id
     *
     * @return id - 商品id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置商品id
     *
     * @param id 商品id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商品名称
     *
     * @return spu_name - 商品名称
     */
    public String getSpuName() {
        return spuName;
    }

    /**
     * 设置商品名称
     *
     * @param spuName 商品名称
     */
    public void setSpuName(String spuName) {
        this.spuName = spuName == null ? null : spuName.trim();
    }

    /**
     * 获取商品描述(后台简述）
     *
     * @return description - 商品描述(后台简述）
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置商品描述(后台简述）
     *
     * @param description 商品描述(后台简述）
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取三级分类id
     *
     * @return catalog3_id - 三级分类id
     */
    public Long getCatalog3Id() {
        return catalog3Id;
    }

    /**
     * 设置三级分类id
     *
     * @param catalog3Id 三级分类id
     */
    public void setCatalog3Id(Long catalog3Id) {
        this.catalog3Id = catalog3Id;
    }

    /**
     * 获取品牌id
     *
     * @return tm_id - 品牌id
     */
    public Long getTmId() {
        return tmId;
    }

    /**
     * 设置品牌id
     *
     * @param tmId 品牌id
     */
    public void setTmId(Long tmId) {
        this.tmId = tmId;
    }
}