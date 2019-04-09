package com.neusoft.guli.po;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "base_sale_attr")
public class BaseSaleAttr implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 销售属性名称
     */
    private String name;

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
     * 获取销售属性名称
     *
     * @return name - 销售属性名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置销售属性名称
     *
     * @param name 销售属性名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}