package com.neusoft.javabean.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "spu_sale_attr_value")
public class SpuSaleAttrValue implements Serializable {
    /**
     * 销售属性值编号
     */
    @Id
    private Long id;

    /**
     * 商品id
     */
    @Column(name = "spu_id")
    private Long spuId;

    /**
     * 销售属性id
     */
    @Column(name = "sale_attr_id")
    private Long saleAttrId;

    /**
     * 销售属性值名称
     */
    @Column(name = "sale_attr_value_name")
    private String saleAttrValueName;

    /**
     * 获取销售属性值编号
     *
     * @return id - 销售属性值编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置销售属性值编号
     *
     * @param id 销售属性值编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商品id
     *
     * @return spu_id - 商品id
     */
    public Long getSpuId() {
        return spuId;
    }

    /**
     * 设置商品id
     *
     * @param spuId 商品id
     */
    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    /**
     * 获取销售属性id
     *
     * @return sale_attr_id - 销售属性id
     */
    public Long getSaleAttrId() {
        return saleAttrId;
    }

    /**
     * 设置销售属性id
     *
     * @param saleAttrId 销售属性id
     */
    public void setSaleAttrId(Long saleAttrId) {
        this.saleAttrId = saleAttrId;
    }

    /**
     * 获取销售属性值名称
     *
     * @return sale_attr_value_name - 销售属性值名称
     */
    public String getSaleAttrValueName() {
        return saleAttrValueName;
    }

    /**
     * 设置销售属性值名称
     *
     * @param saleAttrValueName 销售属性值名称
     */
    public void setSaleAttrValueName(String saleAttrValueName) {
        this.saleAttrValueName = saleAttrValueName == null ? null : saleAttrValueName.trim();
    }
}