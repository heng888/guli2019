package com.neusoft.javabean.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user_details")
public class UserDetails implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 身份证编号
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 用户
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户手机号
     */
    @Column(name = "user_phone")
    private String userPhone;

    /**
     * 用户籍贯
     */
    private String hometown;

    /**
     * 用户住址
     */
    @Column(name = "address_id")
    private Long addressId;

    /**
     * 0女1男
     */
    private String sex;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取身份证编号
     *
     * @return id_card - 身份证编号
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置身份证编号
     *
     * @param idCard 身份证编号
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    /**
     * 获取用户
     *
     * @return user_id - 用户
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户
     *
     * @param userId 用户
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户手机号
     *
     * @return user_phone - 用户手机号
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置用户手机号
     *
     * @param userPhone 用户手机号
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * 获取用户籍贯
     *
     * @return hometown - 用户籍贯
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * 设置用户籍贯
     *
     * @param hometown 用户籍贯
     */
    public void setHometown(String hometown) {
        this.hometown = hometown == null ? null : hometown.trim();
    }

    /**
     * 获取用户住址
     *
     * @return address_id - 用户住址
     */
    public Long getAddressId() {
        return addressId;
    }

    /**
     * 设置用户住址
     *
     * @param addressId 用户住址
     */
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    /**
     * 获取0女1男
     *
     * @return sex - 0女1男
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置0女1男
     *
     * @param sex 0女1男
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}