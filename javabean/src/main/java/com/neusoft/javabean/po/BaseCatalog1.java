package com.neusoft.javabean.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "base_catalog1")
public class BaseCatalog1 implements Serializable {


    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    @Transient
    private List<BaseCatalog2> catalog2List;

    public List<BaseCatalog2> getCatalog2List() {
        return catalog2List;
    }

    public void setCatalog2List(List<BaseCatalog2> catalog2List) {
        this.catalog2List = catalog2List;
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
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}