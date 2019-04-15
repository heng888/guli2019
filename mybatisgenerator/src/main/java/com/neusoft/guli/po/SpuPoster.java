package com.neusoft.guli.po;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "spu_poster")
public class SpuPoster implements Serializable {
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
     * 文件名称
     */
    @Column(name = "img_name")
    private String imgName;

    /**
     * 文件路径
     */
    @Column(name = "img_url")
    private String imgUrl;

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
     * 获取文件名称
     *
     * @return img_name - 文件名称
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * 设置文件名称
     *
     * @param imgName 文件名称
     */
    public void setImgName(String imgName) {
        this.imgName = imgName == null ? null : imgName.trim();
    }

    /**
     * 获取文件路径
     *
     * @return img_url - 文件路径
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置文件路径
     *
     * @param imgUrl 文件路径
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
}