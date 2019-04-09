package com.neusoft.guli.po;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "spu_color")
public class SpuColor implements Serializable {
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
     * 商品颜色
     */
    private String color;

    /**
     * 文件名
     */
    @Column(name = "file_name")
    private String fileName;

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
     * 获取商品颜色
     *
     * @return color - 商品颜色
     */
    public String getColor() {
        return color;
    }

    /**
     * 设置商品颜色
     *
     * @param color 商品颜色
     */
    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    /**
     * 获取文件名
     *
     * @return file_name - 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名
     *
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
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