package com.atguigu.springboot04webrestfulcrud.controller;

import com.atguigu.springboot04webrestfulcrud.Service.GoodService;
import com.atguigu.springboot04webrestfulcrud.Service.ShoppingCartService;

import com.atguigu.springboot04webrestfulcrud.entities.Buyer;
import com.atguigu.springboot04webrestfulcrud.entities.CartGoods;
import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingCartController {

    @Autowired
    GoodService goodService;

    @Autowired
    ShoppingCartService shoppingCartService;


    @ResponseBody
    @PostMapping("/addCart")
    public Integer addShoppingCart(@RequestParam("id") Integer id, @RequestParam("num") Integer num, HttpSession session){
        if(num == 0){
            throw new RuntimeException();
        }
        Buyer buyer = (Buyer) session.getAttribute("buyerUser");
        Goods goods = goodService.getGoodsById(id);
        CartGoods cartGoods = new CartGoods();
        cartGoods.setGoodsid(id);
        cartGoods.setGoodsprice(goods.getPrice());
        cartGoods.setGoodsnum(num);
        cartGoods.setGoodsname(goods.getGoodsname());
        cartGoods.setBuyerid(buyer.getId());
        cartGoods.setBuyername(buyer.getName());
        Integer row = shoppingCartService.addShoppingCart(cartGoods);
        return  row;
    }

    @ResponseBody
    @GetMapping("/ShowCart")
    public Map showcart(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Buyer buyer = (Buyer) session.getAttribute("buyerUser");
        System.out.println();
        List<CartGoods> products = shoppingCartService.getCartGoodsList(buyer.getName());
        map.put("products",products);
        return map;
    }
}
