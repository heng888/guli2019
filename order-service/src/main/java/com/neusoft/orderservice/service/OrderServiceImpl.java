package com.neusoft.orderservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.neusoft.interfaces.OrderService;
import com.neusoft.javabean.po.OrderInfo;
import com.neusoft.orderservice.dao.OrderInfoMapper;
import com.neusoft.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import redis.clients.jedis.Jedis;


import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<OrderInfo> selectAllOrderInfo(){
        return orderInfoMapper.selectAll();
    }

    /**
     * 生成 TradeCode码，防止提交订单回退时的重复提交
     * @param userId
     * @return
     */
    @Override
    public String getTradeCode(String userId) {
        String k = "userId:"+userId+":tradeCode";
        String v = UUID.randomUUID().toString();
        Jedis jedis = redisUtil.getJedis();
        jedis.setex(k,60*30,v);
        jedis.close();
        return v;
    }

    /**
     * 检查TradeCode码是否已经提交过
     * @param userId
     * @param tradeCode
     * @return
     */
    @Override
    public boolean checkTradeCode(String userId, String tradeCode) {
        boolean b = false;
        String k = "userId:"+userId+":tradeCode";
        Jedis jedis = redisUtil.getJedis();
        String s = jedis.get(k);
        if(StringUtils.isNotBlank(s)&&s.equals(tradeCode)){
            b = true;
            jedis.del(k);
        }
        jedis.close();
        return b;
    }
}
