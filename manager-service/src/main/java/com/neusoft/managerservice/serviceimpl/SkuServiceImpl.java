package com.neusoft.managerservice.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.neusoft.interfaces.SkuService;
import com.neusoft.javabean.po.*;
import com.neusoft.managerservice.dao.SkuAttrValueMapper;
import com.neusoft.managerservice.dao.SkuImageMapper;
import com.neusoft.managerservice.dao.SkuInfoMapper;
import com.neusoft.managerservice.dao.SkuSaleAttrValueMapper;
import com.neusoft.util.RedisUtil;
import io.searchbox.client.JestClient;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    SkuInfoMapper skuInfoMapper;
    @Autowired
    SkuImageMapper skuImageMapper;
    @Autowired
    SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    SkuSaleAttrValueMapper skuSaleAttrValueMapper;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    JestClient jestClient;

    /**
     * 查询skuInfo信息
     * @param spuId
     * @return
     */
    @Override
    public List<SkuInfo> getSkuListBySpu(Long spuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setSpuId(spuId);
        List<SkuInfo> skuInfos = skuInfoMapper.select(skuInfo);
        return skuInfos;
    }


    /**
     * 查询SkuInfo及相关的SkuImage、SkuAttrValue、SkuSaleAttrValue
     */
    @Override
    public List<SkuInfo> selectSkuInfoBySpuId(Long spuId) {
        return skuInfoMapper.selectSkuInfoBySpuId(spuId);
    }

    /**
     * 新增skuInfo
     */
    @Override
    public void saveSku(SkuInfo skuInfo) {
        //插入SkuInfo
        skuInfoMapper.insert(skuInfo);
        Long skuInfoId = skuInfo.getId();
        //插入SkuAttrValue
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        if(skuAttrValueList!=null && skuAttrValueList.size()>0){
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(skuInfoId);
                skuAttrValueMapper.insert(skuAttrValue);
            }
        }

        //插入SkuSaleAttrValue
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
       if(skuSaleAttrValueList!=null&&skuSaleAttrValueList.size()>0){
           for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
               skuSaleAttrValue.setSkuId(skuInfoId);
               skuSaleAttrValueMapper.insert(skuSaleAttrValue);
           }
       }

        //插入SkuImage
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        for (SkuImage skuImage : skuImageList) {
            skuImage.setSkuId(skuInfoId);
            skuImageMapper.insert(skuImage);
        }

        //将每次添加的数据存到es中
        //toEs(skuInfoId);
    }

    /**
     * 将每次添加的数据存到es中
     * @param skuInfoId
     */

    public void toEs(Long skuInfoId){
        SkuInfo skuInfo1 = skuInfoMapper.selectByPrimaryKey(skuInfoId);
        SkuAttrValue skuAttrValue = new SkuAttrValue();
        skuAttrValue.setSkuId(skuInfoId);
        List<SkuAttrValue> skuAttrValueList1 = skuAttrValueMapper.select(skuAttrValue);
        skuInfo1.setSkuAttrValueList(skuAttrValueList1);
        //toElastic(skuInfo1);

    }


    /**
     * 删除sku
     * @param skuId
     */
    @Override
    public void deleteSkuInfo(Long skuId) {
        //删除SkuImage
        SkuImage skuImage = new SkuImage();
        skuImage.setSkuId(skuId);
        skuImageMapper.delete(skuImage);

        //删除SkuAttrValue
        SkuAttrValue skuAttrValue = new SkuAttrValue();
        skuAttrValue.setSkuId(skuId);
        skuAttrValueMapper.delete(skuAttrValue);

        //删除SkuSaleAttrValue
        SkuSaleAttrValue skuSaleAttrValue = new SkuSaleAttrValue();
        skuSaleAttrValue.setSkuId(skuId);
        skuSaleAttrValueMapper.delete(skuSaleAttrValue);

        //删除SkuInfo
        skuInfoMapper.deleteByPrimaryKey(skuId);

        Delete build = new Delete.Builder(skuId.toString()).index("guli2019").type("SkuLsInfo").build();
        try {
            jestClient.execute(build);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改SkuInfo
     * @param skuInfo
     */
    @Override
    public void updateSkuInfo(SkuInfo skuInfo) {
        //修改SkuInfo
        skuInfoMapper.updateByPrimaryKey(skuInfo);
        Long skuInfoId = skuInfo.getId();
        //先根据skuId删除SkuImage
        SkuImage skuImage = new SkuImage();
        skuImage.setSkuId(skuInfoId);
        skuImageMapper.delete(skuImage);

        //先根据skuId删除SkuAttrValue
        SkuAttrValue skuAttrValue = new SkuAttrValue();
        skuAttrValue.setSkuId(skuInfoId);
        skuAttrValueMapper.delete(skuAttrValue);

        //先根据skuId删除SkuSaleAttrValue
        SkuSaleAttrValue skuSaleAttrValue = new SkuSaleAttrValue();
        skuSaleAttrValue.setSkuId(skuInfoId);
        skuSaleAttrValueMapper.delete(skuSaleAttrValue);


        //插入SkuAttrValue
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
       if(skuAttrValueList!=null && skuAttrValueList.size()>0){
           for (SkuAttrValue skuAttrValue1 : skuAttrValueList) {
               skuAttrValue1.setSkuId(skuInfoId);
               skuAttrValueMapper.insert(skuAttrValue1);
           }
       }

        //插入SkuImage
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        for (SkuImage skuImage1 : skuImageList) {
            skuImage1.setSkuId(skuInfoId);
            skuImageMapper.insert(skuImage1);
        }

        //插入SkuSaleAttrValue
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        if(skuSaleAttrValueList!=null && skuSaleAttrValueList.size()>0){
            for (SkuSaleAttrValue skuSaleAttrValue1 : skuSaleAttrValueList) {
                skuSaleAttrValue1.setSkuId(skuInfoId);
                skuSaleAttrValueMapper.insert(skuSaleAttrValue1);
            }
        }

        //将每次修改的数据更新到es中
        //toEs(skuInfoId);
    }


    //先在redis中查询，如果没有再调数据库查询
    @Override
    public SkuInfo getSkuInfoBySkuId(Long skuId) {
        SkuInfo skuInfo = null;
        //查询redis缓存
        Jedis jedis = redisUtil.getJedis();
        String key = "sku:"+skuId+":info";
        String val =jedis.get(key);

        //通过上一个缓存锁在数据库查询到的数据是否为空
        if("empty".equals(val)){
            System.out.println(Thread.currentThread().getName()+"发现数据库中暂时没有该商品，直接返回空对象");
            return skuInfo;
        }

        //判断redis是否有查询到数据
        if(StringUtils.isBlank(val)){
            System.out.println(Thread.currentThread().getName()+"发现缓存中暂时没有数据，申请分布式锁");
            //如果在缓存中没有查询到数据，申请缓存锁
            String OK = jedis.set("sku:" + skuId + ":lock", "1", "nx", "px", 5000);
            //判断是否申请到缓存锁
            if("OK".equals(OK)){
/*                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                System.out.println(Thread.currentThread().getName()+"获得分布式锁，开始访问数据库");
                //如果得到缓存锁，查询DB
                skuInfo = getSkuInfoBySkuIdFormDB(skuId);

                //判断在数据库是否查到数据
                if(skuInfo!=null){
                    System.out.println(Thread.currentThread().getName()+"通过分布式锁，查询到数据，同步缓存，然后会还锁");
                    //如果数据库查到数据，同步缓存
                    jedis.set(key,JSON.toJSONString(skuInfo));
                    System.out.println(Thread.currentThread().getName()+"从数据库正常得到数据");
                }else{
                    System.out.println(Thread.currentThread().getName()+"通过分布式锁，没有查询到数据，通知下一个在10秒之内不要访问该sku");
                    //设置要查询的key为empty(空)，方便通知下一个查询的
                    jedis.setex(key,3,"empty");
                }

                //归还缓存锁
                jedis.del("sku:" + skuId + ":lock");
            }else{
                //没有得到缓存锁，自旋
                System.out.println(Thread.currentThread().getName()+"分布式锁被占用，开始自旋");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getSkuInfoBySkuId(skuId);
            }
        }else{
            //正常转换数据
            System.out.println(Thread.currentThread().getName()+"从缓存正常得到数据");
            skuInfo = JSON.parseObject(val, SkuInfo.class);
        }
        return skuInfo;
    }

    //在数据库中根据skuId查询skuInfo相关的spuImage、spuAttrValue、spuSaleAttrValue
    private SkuInfo getSkuInfoBySkuIdFormDB(Long skuId) {
        //查询skuInfo
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);
        SkuInfo skuInfo1 = skuInfoMapper.selectOne(skuInfo);

          if(skuInfo1!=null){
              //查询skuImage并存入skuInfo
              SkuImage skuImage = new SkuImage();
              skuImage.setSkuId(skuId);
              List<SkuImage> skuImages = skuImageMapper.select(skuImage);
              skuInfo1.setSkuImageList(skuImages);
          }

        return skuInfo1;
    }


    /**
     * 通过spuId查询SaleAttrValue集合
     * @param spuId
     * @return
     */
    @Override
    public List<SkuInfo> selectSkuSaleAttrValueListBySpuId(Long spuId) {
        return skuInfoMapper.selectSkuSaleAttrValueListBySpuId(spuId);
    }


    /**
     * 通过三级分类id查询SkuIn
     * @param catalog3Id
     * @return
     */
    @Override
    public List<SkuInfo> getSkuListByCatalog3Id(Long catalog3Id) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setCatalog3Id(catalog3Id);
        List<SkuInfo> skuInfoList = skuInfoMapper.select(skuInfo);
        for (SkuInfo info : skuInfoList) {
            Long infoId = info.getId();
            SkuAttrValue skuAttrValue = new SkuAttrValue();
            skuAttrValue.setSkuId(infoId);
            List<SkuAttrValue> skuAttrValueList = skuAttrValueMapper.select(skuAttrValue);
            info.setSkuAttrValueList(skuAttrValueList);
        }
        return skuInfoList;
    }

    /**
     * 根据键盘输入模糊查询
     * @param keyword
     * @return
     */
    @Override
    public List<SkuInfo> getSkuListBykeyWord(String keyword) {
        Example example = new Example(SkuInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("skuName",keyword);
        List<SkuInfo> skuInfos = skuInfoMapper.selectByExample(example);
        return skuInfos;
    }

    /**
     *     将sql中的数据存放到elastic中
     */
    public void toElastic(SkuInfo skuInfo) {

        // 转化es中的sku信息
            SkuLsInfo skuLsInfo = new SkuLsInfo();
            try {
                BeanUtils.copyProperties(skuLsInfo,skuInfo);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            String id = skuLsInfo.getId();

            Index build = new Index.Builder(skuLsInfo).index("guli2019").type("SkuLsInfo").id(id).build();

            try {
                jestClient.execute(build);
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
