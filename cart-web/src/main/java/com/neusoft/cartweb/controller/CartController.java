package com.neusoft.cartweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neusoft.interfaces.CartService;
import com.neusoft.interfaces.SkuService;
import com.neusoft.javabean.po.CartInfo;
import com.neusoft.javabean.po.SkuInfo;
import com.neusoft.webutils.annotation.LoginRequire;
import com.neusoft.webutils.util.CookieUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Reference
    SkuService skuService;

    @Reference
    CartService cartService;

    //跳到添加购物车成功页面
    @LoginRequire(ifNeedSuccess = false)
    @RequestMapping("cartSuccess")
    public String cartSuccess(@ModelAttribute("skuInfo") SkuInfo skuInfo){
        return "success";
    }


    /**
     * 购物车商品状态改变
     * @return
     */
    @LoginRequire(ifNeedSuccess = false)
    @RequestMapping("checkCart")
    public String checkCart(HttpServletRequest request,HttpServletResponse response,ModelMap map,CartInfo cartInfo){
        String userId=(String) request.getAttribute("userId");
        List<CartInfo> cartInfos = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal(0);
        //修改购物车选中状态
        if(StringUtils.isNotBlank(userId)){
            //更新db和缓存
            cartInfo.setUserId(userId);
            cartService.updateCartCheck(cartInfo);
            //更新数据后将最新数据查询出来
            //取缓存数据
            cartInfos = cartService.getCatCache(userId);
        }else{
            //更新
            //取cookie中的数据
            String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);
           //更新cookie
            if(StringUtils.isNotBlank(cartListCookie)){
                cartInfos = JSON.parseArray(cartListCookie, CartInfo.class);
                for (CartInfo info : cartInfos) {
                    if(info.getSkuId().toString().equals(cartInfo.getSkuId())){
                        info.setIsChecked(cartInfo.getIsChecked());
                    }
                }
            }
            //操作完成后覆盖cookie
            CookieUtil.setCookie(request,response,"cartListCookie",JSON.toJSONString(cartInfos),60*60*24*7,true);
        }

       /* //更新数据后将最新数据查询出来
        if(StringUtils.isBlank(userId)){
            //取cookie中的数据
            String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);
            if(StringUtils.isNotBlank(cartListCookie)){
                cartInfos = JSON.parseArray(cartListCookie, CartInfo.class);
            }
        }else{
            //取缓存数据
            cartInfos = cartService.getCatCache(userId);
        }*/

        for (CartInfo cartInfo1 : cartInfos) {
            if("1".equals(cartInfo1.getIsChecked())){
                BigDecimal decimal = cartInfo1.getCartPrice();
                totalPrice=totalPrice.add(decimal);
            }
        }
        map.put("cartList",cartInfos);
        map.put("totalPrice",totalPrice);
        return "cartListInner";
    }


    /**
     * 购物车详情
     * @return
     */
    @LoginRequire(ifNeedSuccess = false)
    @RequestMapping("cartList")
    public String cartList(HttpServletRequest request, ModelMap map){
        String userId=(String) request.getAttribute("userId");
        List<CartInfo> cartInfos = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal(0);
        if(StringUtils.isBlank(userId)){
            //取cookie中的数据
            String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);
           if(StringUtils.isNotBlank(cartListCookie)){
              cartInfos = JSON.parseArray(cartListCookie, CartInfo.class);
           }
        }else{
            //取缓存数据
            cartInfos = cartService.getCatCache(userId);
            if (cartInfos == null) {

                }

        }

            for (CartInfo cartInfo : cartInfos) {
                if("1".equals(cartInfo.getIsChecked())){
                    BigDecimal decimal = cartInfo.getCartPrice();
                    totalPrice=totalPrice.add(decimal);
                }
            }
        map.put("cartList",cartInfos);
        map.put("totalPrice",totalPrice);
        return "cartList";
    }

    /**
     * 添加商品到购物车
     * @return
     */
    @LoginRequire(ifNeedSuccess = false)
    @RequestMapping("addToCart")
    public String addToCart(HttpServletRequest request, HttpServletResponse response, CartInfo cartInfo,
                                RedirectAttributes attr){
        String id = cartInfo.getSkuId();
        SkuInfo skuInfo = skuService.getSkuInfoBySkuId(Long.parseLong(id));
        cartInfo.setCartPrice(skuInfo.getPrice().multiply(new BigDecimal((cartInfo.getSkuNum()))));
        cartInfo.setIsChecked("1");
        cartInfo.setImgUrl(skuInfo.getSkuDefaultImg());
        cartInfo.setSkuPrice(skuInfo.getPrice());
        cartInfo.setSkuName(skuInfo.getSkuName());

        String userId=(String) request.getAttribute("userId");
        List<CartInfo> cartInfos = new ArrayList<>();
        if(StringUtils.isBlank(userId)){
            //用户未登陆，操作cookie
            String cartListCookieStr = CookieUtil.getCookieValue(request, "cartListCookie", true);
            if(StringUtils.isBlank(cartListCookieStr)){
                //如果缓cookie不存在，新建cookie
                cartInfos.add(cartInfo);
            }else{
                //如果缓cookie存在
                cartInfos = JSON.parseArray(cartListCookieStr, CartInfo.class);
                //判断是否重复的sku
                boolean b = isNewCart(cartInfos,cartInfo);
                if(b){
                    cartInfos.add(cartInfo);//添加cookie
                }else{
                    //更新cookie
                    for (CartInfo info : cartInfos) {
                        String skuId = String.valueOf(info.getSkuId());
                        if(skuId.equals(cartInfo.getSkuId())){
                            info.setSkuNum(info.getSkuNum()+cartInfo.getSkuNum());
                            info.setCartPrice(info.getSkuPrice().multiply(new BigDecimal(info.getSkuNum())));
                        }
                    }
                }
            }
            //操作完成后覆盖cookie
            CookieUtil.setCookie(request,response,"cartListCookie",JSON.toJSONString(cartInfos),60*60*24*7,true);

        }else {
            //用户已登陆，操作Db
            //根据传过来的参数查询数据库是否已经存在该sku的商品
            cartInfo.setUserId("1");
            CartInfo cartInfoDb = cartService.isCartExists(cartInfo);
            if(cartInfoDb != null){
                //更新数据库
                cartInfoDb.setSkuNum(cartInfo.getSkuNum()+cartInfoDb.getSkuNum());
                cartInfoDb.setCartPrice(cartInfoDb.getSkuPrice().multiply(new BigDecimal(cartInfoDb.getSkuNum())));
                cartService.updateCart(cartInfoDb);
            }else{
                //插入到数据库
                cartService.saveCart(cartInfo);
            }
            //更新缓存
            cartService.syncCache(userId);
        }

        attr.addFlashAttribute("skuInfo",skuInfo);
        attr.addFlashAttribute("skuNum",cartInfo.getSkuNum());
        return "redirect:/cartSuccess";
    }

    /**
     * 判断缓存里面的sku是否存在
     * @param cartInfos
     * @param cartInfo
     * @return
     */
    private boolean isNewCart(List<CartInfo> cartInfos, CartInfo cartInfo) {
        boolean b = true;
        for (CartInfo info : cartInfos) {
            String skuId = info.getSkuId();
            if(skuId.equals(cartInfo.getSkuId())){
                b = false;
            }
        }
        return b;
    }
}
