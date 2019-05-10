package com.neusoft.interfaces;

import com.neusoft.javabean.po.OrderInfo;

import java.util.List;

public interface OrderService {
    List<OrderInfo> selectAllOrderInfo();

    //生成 TradeCode码，防止提交订单回退时的重复提交
    String getTradeCode(String userId);

    //检查TradeCode码是否已经提交过
    boolean checkTradeCode(String userId, String tradeCode);
}
