package com.neusoft.guli.po;

import javax.persistence.*;

@Table(name = "base_catalog1_tm")
public class BaseCatalog1Tm {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 品牌id
     */
    @Column(name = "tm_id")
    private Long tmId;

    /**
     * 一级分类id
     */
    @Column(name = "catalog1_id")
    private Long catalog1Id;

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

    /**
     * 获取一级分类id
     *
     * @return catalog1_id - 一级分类id
     */
    public Long getCatalog1Id() {
        return catalog1Id;
    }

    /**
     * 设置一级分类id
     *
     * @param catalog1Id 一级分类id
     */
    public void setCatalog1Id(Long catalog1Id) {
        this.catalog1Id = catalog1Id;
    }
}