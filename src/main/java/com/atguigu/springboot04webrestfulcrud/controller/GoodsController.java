package com.atguigu.springboot04webrestfulcrud.controller;

import com.atguigu.springboot04webrestfulcrud.Service.GoodService;
import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsController {

    @Autowired
    GoodService goodService;

    @ResponseBody
    @GetMapping("/goods")
    public Map<String,Object> getAllGoods(){
        List<Goods> allGoods = goodService.getAllGoods();
        Map<String,Object> map = new HashMap<>();
        map.put("allgoods",allGoods);
        return map;
    }

    @ResponseBody
    @GetMapping("/goods/{name}")
    public Map<String,Object> getGoodsBySeller(@PathVariable("name") String name){
        List<Goods> allGoods = goodService.getGoodsBySeller(name);
        Map<String,Object> map = new HashMap<>();
        map.put("allgoods",allGoods);
        return map;
    }

    @GetMapping("/info/{id}")
    public String getGoodsById(@PathVariable("id") Integer id,Model model){
        System.out.println("===========");

        Goods goods = goodService.getGoodsById(id);
        model.addAttribute("goods",goods);
        return "info/detail";
    }
}
