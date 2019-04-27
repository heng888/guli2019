package com.neusoft.interfaces;

import com.neusoft.javabean.po.UserAddress;
import com.neusoft.javabean.po.UserInfo;

import java.util.List;

public interface UserService {
    List<UserInfo> selectAllUserInfo();

    //验证用户名跟密码是否正确
    UserInfo login(UserInfo userInfo);

    //根据用户ID查询该用户的所有收货地址
    List<UserAddress> getUserAddressList(String userId);
}
