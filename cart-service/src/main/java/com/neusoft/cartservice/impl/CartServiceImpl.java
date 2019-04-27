package com.neusoft.cartservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.neusoft.cartservice.dao.CartInfoMaper;
import com.neusoft.interfaces.CartService;
import com.neusoft.javabean.po.CartInfo;
import com.neusoft.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    CartInfoMaper cartInfoMaper;

    @Autowired
    RedisUtil redisUtil;


    /**
     * 根据传过来的参数查询数据库是否已经存在该sku的商品
     * @param cartInfo
     * @return
     */
    @Override
    public CartInfo isCartExists(CartInfo cartInfo) {
        CartInfo cartInfo1 = new CartInfo();
        cartInfo1.setUserId(cartInfo.getUserId());
        cartInfo1.setSkuId(cartInfo.getSkuId());
        return cartInfoMaper.selectOne(cartInfo1);
    }

    /**
     * 更新数据库购物车信息
     * @param cartInfoDb
     */
    @Override
    public void updateCart(CartInfo cartInfoDb) {
        cartInfoMaper.updateByPrimaryKeySelective(cartInfoDb);
    }

    /**
     * 插入购物车数据
     * @param cartInfo
     */
    @Override
    public void saveCart(CartInfo cartInfo) {
        cartInfoMaper.insertSelective(cartInfo);
    }

    /**
     * 更新购物车数据到缓存
     * @param userId
     */
    @Override
    public void syncCache(String userId) {

        Jedis jedis = redisUtil.getJedis();

        CartInfo cartInfo = new CartInfo();
        cartInfo.setUserId(userId);
        List<CartInfo> cartInfos = cartInfoMaper.select(cartInfo);

        HashMap<String, String> hashMap = new HashMap<>();
        for (CartInfo info : cartInfos) {
            hashMap.put(info.getId(), JSON.toJSONString(info));
        }

        jedis.del("carts:"+userId+":info");
        jedis.hmset("carts:"+userId+":info",hashMap);
        jedis.close();
    }

    /**
     * 从缓存中取购物车数据
     * @param userId
     * @return
     */
    @Override
    public List<CartInfo> getCatCache(String userId) {
        List<CartInfo> cartInfos = new ArrayList<>();
        Jedis jedis = redisUtil.getJedis();
        List<String> hvals = jedis.hvals("carts:" + userId + ":info");
        if(hvals != null && hvals.size() >0){
            for (String hval : hvals) {
                CartInfo cartInfo = JSON.parseObject(hval, CartInfo.class);
                cartInfos.add(cartInfo);
            }
        }
        return cartInfos;
    }

    /**
     * 更新购物车选中状态
     * @param cartInfo
     */
    @Override
    public void updateCartCheck(CartInfo cartInfo) {
        Example example = new Example(CartInfo.class);
        example.createCriteria().andEqualTo("skuId",cartInfo.getSkuId()).andEqualTo("userId",cartInfo.getUserId());
        cartInfoMaper.updateByExampleSelective(cartInfo,example);
        syncCache(cartInfo.getUserId());
    }

    /**
     * 用户登陆后同步购物车数据到db
     * @param cartInfos
     * @param userId
     */
    @Override
    public void combineCart(List<CartInfo> cartInfos, Long userId) {

        if(cartInfos!=null){
            for (CartInfo cartInfo : cartInfos) {
                CartInfo cartExists = isCartExists(cartInfo);
                if(cartExists==null){
                    //插入
                    cartInfo.setUserId(String.valueOf(userId));
                    cartInfoMaper.insertSelective(cartInfo);
                }else{
                    //更新
                    cartExists.setSkuNum(cartExists.getSkuNum()+cartInfo.getSkuNum());
                    cartExists.setCartPrice(cartExists.getSkuPrice().multiply(new BigDecimal(cartExists.getSkuNum())));
                    cartInfoMaper.updateByPrimaryKeySelective(cartExists);
                }
            }
        }

        //同步缓存
        syncCache(String.valueOf(userId));
    }


    /**
     * 根据id查询该用户所有选中的商品
     * @param userId
     * @return
     */
    @Override
    public List<CartInfo> getCatCacheByChecked(String userId) {
        List<CartInfo> cartInfos = new ArrayList<>();
        Jedis jedis = redisUtil.getJedis();
        List<String> hvals = jedis.hvals("carts:" + userId + ":info");
        if(hvals != null && hvals.size() >0){
            for (String hval : hvals) {
                CartInfo cartInfo = JSON.parseObject(hval, CartInfo.class);
                if("1".equals(cartInfo.getIsChecked())){
                    cartInfos.add(cartInfo);
                }
            }
        }
        return cartInfos;
    }
}
