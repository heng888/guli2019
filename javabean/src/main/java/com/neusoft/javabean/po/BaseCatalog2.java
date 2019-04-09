package com.neusoft.javabean.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "base_catalog2")
public class BaseCatalog2 implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 二级分类名称
     */
    private String name;

    /**
     * 一级分类编号
     */
    @Column(name = "catalog1_id")
    private Long catalog1Id;

    public BaseCatalog2() {
    }

    public BaseCatalog2(Long id, String name, Long catalog1Id) {
        this.id = id;
        this.name = name;
        this.catalog1Id = catalog1Id;
    }

    @Override
    public String toString() {
        return "BaseCatalog2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", catalog1Id=" + catalog1Id +
                '}';
    }

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
     * 获取二级分类名称
     *
     * @return name - 二级分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置二级分类名称
     *
     * @param name 二级分类名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取一级分类编号
     *
     * @return catalog1_id - 一级分类编号
     */
    public Long getCatalog1Id() {
        return catalog1Id;
    }

    /**
     * 设置一级分类编号
     *
     * @param catalog1Id 一级分类编号
     */
    public void setCatalog1Id(Long catalog1Id) {
        this.catalog1Id = catalog1Id;
    }
}