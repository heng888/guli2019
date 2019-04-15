package com.neusoft.interfaces;

import com.neusoft.javabean.po.OrderInfo;

import java.util.List;

public interface OrderService {
    List<OrderInfo> selectAllOrderInfo();
}
