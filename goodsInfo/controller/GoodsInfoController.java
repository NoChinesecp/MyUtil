


        package com.qhit.goodsInfo.controller;

        import com.netflix.client.http.HttpRequest;
        import com.netflix.client.http.HttpResponse;
        import com.qhit.goodsInfo.pojo.GoodsInfo;
        import com.qhit.goodsInfo.service.IGoodsInfoService;
        import org.springframework.web.bind.annotation.RequestMapping;
        import javax.annotation.Resource;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.util.List;
        import java.util.Map;

        import org.springframework.web.bind.annotation.RestController;

/**
 * Created by GeneratorCode on 2019/05/06
 */

@RestController
@RequestMapping("/Cart")
class GoodsInfoController {

    @Resource
    IGoodsInfoService goodsInfoService;

//    @RequestMapping("/insert")
//    public void insert(GoodsInfo goodsInfo) {
//        goodsInfoService.insert(goodsInfo);
//    }
//
//    @RequestMapping("/delete")
//    public void delete(Integer gid) {
//        goodsInfoService.delete(gid);
//    }
//
//    @RequestMapping("/update")
//    public void update(GoodsInfo goodsInfo) {
//        goodsInfoService.update(goodsInfo);
//    }
//
//    @RequestMapping("/updateSelective")
//    public void updateSelective(GoodsInfo goodsInfo) {
//        goodsInfoService.updateSelective(goodsInfo);
//    }

//    @RequestMapping("/load")
//    public GoodsInfo load(Integer gid) {
//        GoodsInfo goodsInfo = goodsInfoService.findById(gid);
//        return goodsInfo;
//    }
//
//    @RequestMapping("/list")
//    public List<GoodsInfo> list()  {
//        List<GoodsInfo> list = goodsInfoService.findAll();
//
//        return list;
//    }
//
//    @RequestMapping("/search")
//    public List<GoodsInfo> search(GoodsInfo goodsInfo) {
//        List<GoodsInfo> list = goodsInfoService.search(goodsInfo);
//        return list;
//    }

    /*
    *  购物车添加或者修改某一件商品
    */
    @RequestMapping("/addCart")
    public Boolean addCart(GoodsInfo goodsInfo,HttpSession session) {

        Boolean flag = goodsInfoService.addCart(goodsInfo,session);
        return flag;
    }

    /*
    *   删除购物车某一件商品
    */
    @RequestMapping("/delCart")
    public Boolean delCart(HttpSession session,Integer shopId) {

        Boolean flag = goodsInfoService.delCart(session,shopId);
        return flag;
    }


    /*
    *   获取某用户的所有购物车
    */
    @RequestMapping("/getCart")
    public String  getCart(Integer userId) {
        String cartJson = goodsInfoService.getCart(userId);
        return cartJson;
    }

    @RequestMapping("/getAllCart")
    public String  getAllCart(HttpSession session) {
        String cartJson = goodsInfoService.getAllCart(session);
        return cartJson;
    }

    @RequestMapping("/test")
    public Boolean test(HttpSession session) {
        String id = session.getId();
        System.out.println(id);
        return null;
    }

}
