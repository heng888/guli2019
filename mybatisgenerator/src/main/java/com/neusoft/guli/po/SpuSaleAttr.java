package com.neusoft.guli.po;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "spu_sale_attr")
public class SpuSaleAttr implements Serializable {
    /**
     * 编号(业务中无关联)
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
     * 销售属性名称(冗余)
     */
    @Column(name = "sale_attr_name")
    private String saleAttrName;

    /**
     * 获取编号(业务中无关联)
     *
     * @return id - 编号(业务中无关联)
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置编号(业务中无关联)
     *
     * @param id 编号(业务中无关联)
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
     * 获取销售属性名称(冗余)
     *
     * @return sale_attr_name - 销售属性名称(冗余)
     */
    public String getSaleAttrName() {
        return saleAttrName;
    }

    /**
     * 设置销售属性名称(冗余)
     *
     * @param saleAttrName 销售属性名称(冗余)
     */
    public void setSaleAttrName(String saleAttrName) {
        this.saleAttrName = saleAttrName == null ? null : saleAttrName.trim();
    }
}