package com.neusoft.javabean.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "base_attr_value")
public class BaseAttrValue implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 属性值名称
     */
    @Column(name = "value_name")
    private String valueName;

    @Override
    public String toString() {
        return "BaseAttrValue{" +
                "id=" + id +
                ", valueName='" + valueName + '\'' +
                ", attrId=" + attrId +
                '}';
    }

    /**
     * 属性id
     */
    @Column(name = "attr_id")
    private Long attrId;

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
     * 获取属性值名称
     *
     * @return value_name - 属性值名称
     */
    public String getValueName() {
        return valueName;
    }

    /**
     * 设置属性值名称
     *
     * @param valueName 属性值名称
     */
    public void setValueName(String valueName) {
        this.valueName = valueName == null ? null : valueName.trim();
    }

    /**
     * 获取属性id
     *
     * @return attr_id - 属性id
     */
    public Long getAttrId() {
        return attrId;
    }

    /**
     * 设置属性id
     *
     * @param attrId 属性id
     */
    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }
}