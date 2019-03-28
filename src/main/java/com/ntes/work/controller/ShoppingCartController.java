package com.ntes.work.controller;

import com.ntes.work.Dto.GoodsDto;
import com.ntes.work.Service.GoodService;
import com.ntes.work.Service.ShoppingCartService;
import com.ntes.work.entities.Buyer;
import com.ntes.work.entities.CartGoods;
import com.ntes.work.entities.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingCartController {

    @Autowired
    GoodService goodService;

    @Autowired
    ShoppingCartService shoppingCartService;

    public final static int UPDATE_STATUS_FAIL = 1;
    public final static int UPDATE_STATUS_SUCCESS = 2;
    public final static int UPDATE_STATUS_BUY = 3;

    public final static int INSERT_FINANCE_FAIL = 1;
    public final static int INSERT_FINANCE_SUCCESS = 2;

    public final static String UPDATE_STATUS_FAIL_STR = "修改失败";
    public final static String UPDATE_STATUS_SUCCESS_STR = "修改成功";
    public final static String UPDATE_STATUS_BUY_STR = "已购买过该商品!";

    public final static String INSERT_FINANCE__NULL = "购物车为空";
    public final static String INSERT_FINANCE_FAIL_STR = "购买失败";
    public final static String INSERT_FINANCE_SUCCESS_STR = "购买成功";


    @ResponseBody
    @PostMapping("/addCart")
    public Integer addShoppingCart(@RequestParam("id") Integer id, @RequestParam("num") Integer num, HttpSession session){
        if(num == 0){
            throw new RuntimeException();
        }
        Goods goods = goodService.getGoodsById(id);
        String goodsname = shoppingCartService.getCartGoodsById(id);
        Buyer buyer = (Buyer) session.getAttribute("buyer");
        CartGoods cartGoods = new CartGoods();
        cartGoods.setGoodsid(id);
        cartGoods.setGoodsprice(goods.getPrice());
        cartGoods.setGoodsnum(num);
        cartGoods.setGoodsname(goods.getGoodsname());
        cartGoods.setBuyerid(buyer.getId());
        cartGoods.setBuyername(buyer.getName());
        Integer row = 0;
       if(goodsname != null){
           row = shoppingCartService.updateCartGoodsById(cartGoods);
       }else {
           row = shoppingCartService.addShoppingCart(cartGoods);
       }
        return  row;
    }

    @ResponseBody
    @GetMapping("/ShowCart")
    public Map showcart(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Buyer buyer = (Buyer) session.getAttribute("buyer");
        List<GoodsDto> products = shoppingCartService.getCartGoodsList(buyer.getName());
        map.put("products",products);
        return map;
    }

    @ResponseBody
    @PostMapping("/UpdateCart")
    public Map updatecart(@RequestParam("id")Integer id,@RequestParam("num")Integer num,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Buyer buyer = (Buyer) session.getAttribute("buyer");
        try {
            Integer integer = shoppingCartService.updateCartGoodsNum(id, num, buyer.getName());
            if(integer == 0){
                map.put("code",UPDATE_STATUS_BUY);
                map.put("info",UPDATE_STATUS_BUY_STR);
            }else {
                map.put("code",UPDATE_STATUS_SUCCESS);
                map.put("info",UPDATE_STATUS_SUCCESS_STR);
            }
        }catch (Exception e){
            map.put("code",UPDATE_STATUS_FAIL);
            map.put("info",UPDATE_STATUS_FAIL_STR);
        }
        return map;
    }

    @ResponseBody
    @PostMapping("/AddFinance")
    public Map insertFinance(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Buyer buyer = (Buyer) session.getAttribute("buyer");
        try {
        List<GoodsDto> products = shoppingCartService.getCartGoodsList(buyer.getName());

        if(products.size() == 0){
            map.put("code",INSERT_FINANCE_FAIL);
            map.put("info",INSERT_FINANCE__NULL);
            return map;
        }
        for(GoodsDto dto : products){
            dto.setBuyerid(buyer.getId());
            dto.setBuyername(buyer.getName());
            dto.setTime(new Date());
        }


        Integer integer = goodService.insertFinance(products);

        List<Goods> goods = goodService.getGoodsFromCart(buyer.getName());
                for(Goods g : goods){
                    for(GoodsDto dto : products){
                        if(g.getId() == dto.getGoodsid()){
                            Integer sellnum = g.getSellnums() + dto.getGoodsnum();
                            g.setSellnums(sellnum);
                        }
                    }
                }
        goodService.updateGoodsNum(goods);
        shoppingCartService.deleteShoppingCart(buyer.getName());
        map.put("code",INSERT_FINANCE_SUCCESS);
        map.put("info",INSERT_FINANCE_SUCCESS_STR);
        }catch (Exception e){


            map.put("code",INSERT_FINANCE_FAIL);
            map.put("info",INSERT_FINANCE_FAIL_STR);

        }
        return map;
    }
}
