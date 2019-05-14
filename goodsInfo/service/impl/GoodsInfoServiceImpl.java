package com.qhit.goodsInfo.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.qhit.RadisUtil.RedisUtils;
import com.qhit.goodsInfo.service.IGoodsInfoService;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.qhit.goodsInfo.dao.IGoodsInfoDao;
import com.qhit.goodsInfo.pojo.GoodsInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
* Created by GeneratorCode on 2019/05/06
*/

@Service 
public class GoodsInfoServiceImpl  implements IGoodsInfoService {

    @Resource 
    IGoodsInfoDao dao;

    @Resource
    RedisUtils redisUtils;

    @Resource
    Gson gson;

    @Override 
    public boolean insert(Object object) { 
        return dao.insert(object); 
    } 

    @Override 
    public boolean update(Object object) { 
        return dao.update(object); 
    } 

    @Override 
    public boolean updateSelective(Object object) { 
        return dao.updateSelective(object); 
    } 

    @Override 
    public boolean delete(Object id) { 
        GoodsInfo goodsInfo = findById(id); 
        return dao.delete(goodsInfo); 
    } 

    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 

    @Override 
    public GoodsInfo findById(Object id) { 
        List<GoodsInfo> list = dao.findById(id); 
        return  list.get(0); 
    } 

    @Override 
    public List<GoodsInfo> search(GoodsInfo goodsInfo) { 
        return dao.search(goodsInfo);
    }

    @Override
    @Transactional
    public Boolean addCart(GoodsInfo goodsInfo,HttpSession session) {
        //新建一个购物车容器 map
        Map<Integer,GoodsInfo> map =null;
        Integer userId = (Integer) session.getAttribute("baseUserId");   //从session中取出userid
        //判断该用户是否已经拥有购物车
        boolean isHasUserId = redisUtils.hasKey(userId.toString());
        if (!isHasUserId){
            //用户在redis里没有购物车   则新建一个购物车
            map=new Hashtable<>();
            map.put(goodsInfo.getGid(),goodsInfo);  //然后添加商品信息
            String s = gson.toJson(map);    //把购物车转换为json
            redisUtils.set(userId.toString(),s);    //redis添加json    key:userid    value:json
        }else {
            //用户在redis里有cart
            Object cartInfoJson = redisUtils.get(userId.toString());    //从redis中取购物车信息
            Object cartInfo = gson.fromJson(cartInfoJson.toString(), new TypeToken<Map<Integer, GoodsInfo>>() {
            }.getType());   //把redis中取得json转换成我们需要的map
            map = (Map<Integer, GoodsInfo>) cartInfo;
            //遍历购物车 处理购物车信息
            for (Map.Entry<Integer, GoodsInfo> entry : map.entrySet()) {
                if (entry.getKey().equals(goodsInfo.getGid())){      //判断商品id是否一样
                        entry.getValue().setCount(goodsInfo.getCount());    //商品id一样时  更改count值
                    return true;
                }
            }
            map.put(goodsInfo.getGid(),goodsInfo);  //如果不一样的话就添加添加到新的商品到对应的购物车里
            String s = gson.toJson(map);        //转换为json
            redisUtils.set(userId.toString(),s);    //修改redis信息
        }
        return true;
    }


    @Override
    public Boolean delCart(HttpSession session,Integer shopId) {
        //查询userid 然后删除shopid的对象删除

        Integer userId = (Integer) session.getAttribute("baseUserId");   //从session中取出userid
        try {
            Object cartinfoJson = redisUtils.get(userId.toString());   //通过userid 获取购物车信息
            Object cartInfo = gson.fromJson(cartinfoJson.toString(), new TypeToken<Map<Integer, GoodsInfo>>() {
            }.getType());   //获取的购物车信息转为json    cartInfoJson可能为null  抓取异常
            Map<Integer,GoodsInfo> map =new Hashtable<>();  //new   map 用来当作数据容器
            map = (Map<Integer, GoodsInfo>) cartInfo;
            if (map.get(shopId)!=null){     //判断是否有这个商品 如果有就删除
                map.remove(shopId);
                String s = gson.toJson(map);        //删除后的购物车重新存放redis中
                redisUtils.set(userId+"",s);
                return true;
            }else {
                return false;       //没有就return false
            }
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public String getCart(Integer userId) {
        String cartinfoJson = (String) redisUtils.get(userId+"");
       return  cartinfoJson;
    }

    @Override
    public String getAllCart(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("baseUserId");   //从session中取出userid
        String cartinfoJson = (String) redisUtils.get(userId+"");
        Object cartInfo = gson.fromJson(cartinfoJson.toString(), new TypeToken<Map<Integer, GoodsInfo>>() {
        }.getType());   //获取的购物车信息转为json    cartInfoJson可能为null  抓取异常
        Map<Integer,GoodsInfo> map =new Hashtable<>();  //new   map 用来当作数据容器
        map = (Map<Integer, GoodsInfo>) cartInfo;
        return null;
    }


}