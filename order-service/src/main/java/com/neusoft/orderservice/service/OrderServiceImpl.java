package com.neusoft.orderservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.neusoft.interfaces.OrderService;
import com.neusoft.javabean.po.OrderInfo;
import com.neusoft.orderservice.dao.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public List<OrderInfo> selectAllOrderInfo(){
        return orderInfoMapper.selectAll();
    }
}
