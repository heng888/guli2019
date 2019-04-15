package com.neusoft.guli.po;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "user_address")
public class UserAddress implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 用户地址
     */
    @Column(name = "user_address")
    private String userAddress;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 收件人
     */
    private String consignee;

    /**
     * 联系方式
     */
    @Column(name = "phone_num")
    private String phoneNum;

    /**
     * 是否是默认
     */
    @Column(name = "is_default")
    private String isDefault;

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
     * 获取用户地址
     *
     * @return user_address - 用户地址
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * 设置用户地址
     *
     * @param userAddress 用户地址
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取收件人
     *
     * @return consignee - 收件人
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * 设置收件人
     *
     * @param consignee 收件人
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    /**
     * 获取联系方式
     *
     * @return phone_num - 联系方式
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置联系方式
     *
     * @param phoneNum 联系方式
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    /**
     * 获取是否是默认
     *
     * @return is_default - 是否是默认
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否是默认
     *
     * @param isDefault 是否是默认
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }
}