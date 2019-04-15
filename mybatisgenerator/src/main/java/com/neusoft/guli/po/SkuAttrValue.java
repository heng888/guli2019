package com.neusoft.guli.po;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "sku_attr_value")
public class SkuAttrValue implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 属性id（冗余)
     */
    @Column(name = "attr_id")
    private Long attrId;

    /**
     * 属性值id
     */
    @Column(name = "value_id")
    private Long valueId;

    /**
     * skuid
     */
    @Column(name = "sku_id")
    private Long skuId;

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
     * 获取属性id（冗余)
     *
     * @return attr_id - 属性id（冗余)
     */
    public Long getAttrId() {
        return attrId;
    }

    /**
     * 设置属性id（冗余)
     *
     * @param attrId 属性id（冗余)
     */
    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    /**
     * 获取属性值id
     *
     * @return value_id - 属性值id
     */
    public Long getValueId() {
        return valueId;
    }

    /**
     * 设置属性值id
     *
     * @param valueId 属性值id
     */
    public void setValueId(Long valueId) {
        this.valueId = valueId;
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
}