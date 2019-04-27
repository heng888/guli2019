package com.neusoft.interfaces;

import com.neusoft.javabean.po.CartInfo;

import java.util.List;

public interface CartService {
    //根据传过来的参数查询数据库是否已经存在该sku的商品
    CartInfo isCartExists(CartInfo cartInfo);
    //更新数据库购物车信息
    void updateCart(CartInfo cartInfoDb);
    //插入购物车数据
    void saveCart(CartInfo cartInfo);
    //更新购物车数据到缓存
    void syncCache(String userId);

    //从缓存取购物车数据
    List<CartInfo> getCatCache(String userId);

    //更新购物车选中状态
    void updateCartCheck(CartInfo cartInfo);

    //用户登陆后同步购物车数据到db
    void combineCart(List<CartInfo> cartInfos, Long id);

    //根据id查询该用户所有选中的商品
    List<CartInfo> getCatCacheByChecked(String userId);
}
