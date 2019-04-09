package com.neusoft.javabean.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "spu_image")
public class SpuImage implements Serializable {
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
     * 图片名称
     */
    @Column(name = "img_name")
    private String imgName;

    /**
     * 图片路径
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
     * 获取图片名称
     *
     * @return img_name - 图片名称
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * 设置图片名称
     *
     * @param imgName 图片名称
     */
    public void setImgName(String imgName) {
        this.imgName = imgName == null ? null : imgName.trim();
    }

    /**
     * 获取图片路径
     *
     * @return img_url - 图片路径
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片路径
     *
     * @param imgUrl 图片路径
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
}