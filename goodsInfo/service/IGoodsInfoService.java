package com.qhit.goodsInfo.service;

import java.util.List;
import java.util.Map;

import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.qhit.goodsInfo.pojo.GoodsInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
* Created by GeneratorCode on 2019/05/06
*/
public interface IGoodsInfoService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    GoodsInfo findById(Object id);

    List<GoodsInfo> search(GoodsInfo goodsInfo);

    Boolean addCart(GoodsInfo goodsInfo,HttpSession session);

    Boolean delCart(HttpSession session,Integer shopId);

    String getCart(Integer userId);

    String getAllCart(HttpSession session);
}