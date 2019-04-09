package com.neusoft.javabean.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "base_trademark")
public class BaseTrademark implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 属性值
     */
    @Column(name = "tm_name")
    private String tmName;

    /**
     * 品牌logo的图片路径
     */
    @Column(name = "logo_url")
    private String logoUrl;

    /**
     * 是否启用
     */
    @Column(name = "is_enable")
    private String isEnable;

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
     * 获取属性值
     *
     * @return tm_name - 属性值
     */
    public String getTmName() {
        return tmName;
    }

    /**
     * 设置属性值
     *
     * @param tmName 属性值
     */
    public void setTmName(String tmName) {
        this.tmName = tmName == null ? null : tmName.trim();
    }

    /**
     * 获取品牌logo的图片路径
     *
     * @return logo_url - 品牌logo的图片路径
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * 设置品牌logo的图片路径
     *
     * @param logoUrl 品牌logo的图片路径
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    /**
     * 获取是否启用
     *
     * @return is_enable - 是否启用
     */
    public String getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否启用
     *
     * @param isEnable 是否启用
     */
    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable == null ? null : isEnable.trim();
    }
}