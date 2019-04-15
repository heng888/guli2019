package com.neusoft.userservice.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.neusoft.interfaces.UserService;
import com.neusoft.javabean.po.UserInfo;
import com.neusoft.userservice.dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public List<UserInfo> selectAllUserInfo() {
        return userInfoMapper.selectAll();
    }
}
