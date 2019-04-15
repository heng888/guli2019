package com.neusoft.managerweb.controller;

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
public class ManagerController {
    @Reference
    private OrderService orderService;

    @Reference
    private UserService userService;


    /**
     * 调到主页
     */
    @RequestMapping("/index.do")
    public String index(){
        return "index";
    }

    /**
     * 跳到属性管理页面
     */
    @RequestMapping("/attrListPage")
    public String attrListPage(){
        return "attrListPage";
    }

    /**
     * 跳到spu管理页面
     */
    @RequestMapping("/spuListPage")
    public String spuListPage(){
        return "spuListPage";
    }
}
