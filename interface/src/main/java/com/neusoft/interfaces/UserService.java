package com.neusoft.interfaces;

import com.neusoft.javabean.po.UserInfo;

import java.util.List;

public interface UserService {
    List<UserInfo> selectAllUserInfo();
}
