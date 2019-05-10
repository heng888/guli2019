package com.neusoft.userservice.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.neusoft.interfaces.UserService;
import com.neusoft.javabean.po.UserAddress;
import com.neusoft.javabean.po.UserInfo;
import com.neusoft.userservice.dao.UserAddressMapper;
import com.neusoft.userservice.dao.UserInfoMapper;
import com.neusoft.util.RedisUtil;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    UserAddressMapper userAddressMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<UserInfo> selectAllUserInfo() {
        return userInfoMapper.selectAll();
    }

    /**
     * 验证用户名跟密码是否正确
     * @param userInfo
     * @return
     */
    @Override
    public UserInfo login(UserInfo userInfo) {
        UserInfo user = userInfoMapper.selectOne(userInfo);

        if(user !=null){
            //同步缓存
            Jedis jedis = redisUtil.getJedis();
            jedis.set("user:"+user.getId()+":info", JSON.toJSONString(user));
            jedis.close();
        }

        return user;
    }

    /**
     * 根据用户ID查询该用户的所有收货地址
     * @param userId
     * @return
     */
    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(Long.valueOf(userId));
        return userAddressMapper.select(userAddress);
    }
}
