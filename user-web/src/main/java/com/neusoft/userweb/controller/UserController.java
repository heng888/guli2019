package com.neusoft.userweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.neusoft.interfaces.OrderService;
import com.neusoft.interfaces.UserService;
import com.neusoft.javabean.po.OrderInfo;
import com.neusoft.javabean.po.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {
    @Reference
    private OrderService orderService;

    @Reference
    private UserService userService;
    @RequestMapping("/select.do")
    public String insertOrder(ModelMap map){
        List<OrderInfo> orderInfos = orderService.selectAllOrderInfo();
        for(OrderInfo orderInfo : orderInfos){
            System.out.println(orderInfo);
        }

        List<UserInfo> userInfos = userService.selectAllUserInfo();
        for(UserInfo userInfo : userInfos){
            System.out.println(userInfo);
        }
        map.put("orderInfos",orderInfos);
        return "index";
    }
}
