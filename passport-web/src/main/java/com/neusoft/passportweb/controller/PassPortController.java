package com.neusoft.passportweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.neusoft.interfaces.CartService;
import com.neusoft.interfaces.UserService;
import com.neusoft.javabean.po.CartInfo;
import com.neusoft.javabean.po.UserInfo;
import com.neusoft.webutils.util.CookieUtil;
import com.neusoft.webutils.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PassPortController {

    @Reference
    UserService userService;

    @Reference
    CartService cartService;

    /**
     * 跳转到登陆页面
     * @param returnURL
     * @param map
     * @return
     */
    @RequestMapping("index")
    public String index(String returnURL, ModelMap map){
        //将原始url返回页面
        map.put("returnURL",returnURL);
        return "index";
    }

    /**
     * 验证用户名跟密码是否正确
     * 颁发token
     * 重定向原始请求
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo){
        //验证用户名跟密码是否正确
        UserInfo user = userService.login(userInfo);
        if(user==null){
            //用户名或者密码错误
            return "username or password err";
        }else{
            //颁发token,使用jwt
            //重定向原始请求
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("userId",user.getId().toString());
            hashMap.put("nickName",user.getNickName());

            String token = "";
            token = JwtUtil.encode("guli2019",hashMap,getMyIp(request));
            //合并同步购物车数据
            String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);

            List<CartInfo> cartInfos = null;
            if(cartListCookie!=null){
                cartInfos = JSON.parseArray(cartListCookie, CartInfo.class);

            }
            cartService.combineCart(cartInfos,user.getId());
            //删除cookie中的购物车数据
            CookieUtil.setCookie(request,response,"cartListCookie","",0,true);
            return token;
        }
    }

    /**
     * 获取ip地址
     * @param request
     * @return
     */
    private String getMyIp(HttpServletRequest request) {

        String ip = "";
        ip = request.getHeader("x-forwarded-for");
        if(StringUtils.isBlank(ip)){
            //直接获取ip
            ip = request.getRemoteAddr();
        }
        if(StringUtils.isBlank(ip)){
            //设置一个虚拟ip
            ip = "127.0.0.1";
        }

        return ip;
    }


    /**
     * 验证用户token是否正确
     * @return
     */
    @RequestMapping("verify")
    @ResponseBody
    public String verify(String token,String salt){
        Map userMap = JwtUtil.decode("guli2019", token, salt);
        if (userMap != null) {
            return "success";
            }else {
            return "fail";
        }
    }
}
