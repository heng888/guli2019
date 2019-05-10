package com.neusoft.orderweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.neusoft.interfaces.CartService;
import com.neusoft.interfaces.OrderService;
import com.neusoft.interfaces.UserService;
import com.neusoft.javabean.po.*;
import com.neusoft.webutils.annotation.LoginRequire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Reference
    private OrderService orderService;

    @Reference
    private UserService userService;

    @Reference
    CartService cartService;




    @LoginRequire(ifNeedSuccess = true)
    @RequestMapping("submitOrder")
    public String submitOrder(String tradeCode,HttpServletRequest request, ModelMap map){
        String userId = (String) request.getAttribute("userId");
        boolean b = orderService.checkTradeCode(userId,tradeCode);
        if(b){
            return "payTest";
        }else{
            return "tradeFail";
        }
    }


    /**
     * 跳转到订单提交页面
     * @param request
     * @param map
     * @return
     */
    @LoginRequire(ifNeedSuccess = true)
    @RequestMapping("toTrade")
    public String toTrade(HttpServletRequest request, ModelMap map){
        String userId = (String)request.getAttribute("userId");
        //将被选中的购物车对象转化为订单对象，展示出来
        List<CartInfo> cartInfos = cartService.getCatCacheByChecked(userId);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (CartInfo cartInfo : cartInfos) {
            OrderDetail orderDetail = new OrderDetail();
            //将购物车对象转化为订单对象
            orderDetail.setImgUrl(cartInfo.getImgUrl());
            orderDetail.setOrderPrice(cartInfo.getCartPrice());
            orderDetail.setSkuId(cartInfo.getSkuId());
            orderDetail.setSkuName(cartInfo.getSkuName());
            orderDetail.setSkuNum(cartInfo.getSkuNum().toString());
            orderDetailList.add(orderDetail);
        }

        //查询用户收货得至列表，让用户选择
        List<UserAddress>  userAddressList = userService.getUserAddressList(userId);

        //生成 TradeCode码，防止提交订单回退时的重复提交
        String tradeCode = orderService.getTradeCode(userId);

        map.put("tradeCode",tradeCode);
        map.put("userAddressList",userAddressList);
        map.put("orderDetailList",orderDetailList);
        //总金额
        map.put("totalAmount",gettotalAmount(cartInfos));

        return "trade";
    }

    private BigDecimal gettotalAmount(List<CartInfo> cartInfos) {
        BigDecimal b = new BigDecimal("0");
        for (CartInfo cartInfo : cartInfos) {
            b = b.add(cartInfo.getCartPrice());
        }
        return b;
    }


}
