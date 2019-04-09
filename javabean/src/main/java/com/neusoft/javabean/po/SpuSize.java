package com.neusoft.javabean.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "spu_size")
public class SpuSize implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 商品id
     */
    @Column(name = "spu_id")
    private Long spuId;

    /**
     * 商品尺寸
     */
    @Column(name = "spu_size")
    private String spuSize;

    /**
     * 是否启用
     */
    @Column(name = "is_enabled")
    private String isEnabled;

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
     * 获取商品尺寸
     *
     * @return spu_size - 商品尺寸
     */
    public String getSpuSize() {
        return spuSize;
    }

    /**
     * 设置商品尺寸
     *
     * @param spuSize 商品尺寸
     */
    public void setSpuSize(String spuSize) {
        this.spuSize = spuSize == null ? null : spuSize.trim();
    }

    /**
     * 获取是否启用
     *
     * @return is_enabled - 是否启用
     */
    public String getIsEnabled() {
        return isEnabled;
    }

    /**
     * 设置是否启用
     *
     * @param isEnabled 是否启用
     */
    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled == null ? null : isEnabled.trim();
    }
}