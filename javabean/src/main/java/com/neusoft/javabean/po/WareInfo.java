package com.neusoft.javabean.po;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "ware_info")
public class WareInfo implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 区域编号
     */
    private String areacode;

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
     * 获取仓库名称
     *
     * @return name - 仓库名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置仓库名称
     *
     * @param name 仓库名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取区域编号
     *
     * @return areacode - 区域编号
     */
    public String getAreacode() {
        return areacode;
    }

    /**
     * 设置区域编号
     *
     * @param areacode 区域编号
     */
    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }
}