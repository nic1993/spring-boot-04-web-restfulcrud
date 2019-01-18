package com.atguigu.springboot04webrestfulcrud.controller;

import com.atguigu.springboot04webrestfulcrud.Service.GoodService;
import com.atguigu.springboot04webrestfulcrud.Util.FileUtils;
import com.atguigu.springboot04webrestfulcrud.config.MyMvcConfig;
import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import com.atguigu.springboot04webrestfulcrud.entities.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
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
        List<Goods> allGoods = goodService.getGoodsBySeller("seller");
        Map<String,Object> map = new HashMap<>();
        map.put("allgoods",allGoods);
        return map;
    }

    @GetMapping("/info/{id}")
    public String getGoodsById(@PathVariable("id") Integer id, Model model, HttpSession session){
        Goods goods = goodService.getGoodsById(id);
        model.addAttribute("goods",goods);
        if(session.getAttribute("sellerUser") != null){
            return "info/sellerdetail";
        }else if(session.getAttribute("buyerUser") != null){
            return "info/buyerdetail";
        }else{
            return "info/detail";
        }
    }

    @PutMapping("/edit")
    public String updateGoodsById(Goods goods){
        goodService.updateGoodsById(goods);
        return "info/edSuccess";
    }

    @GetMapping("/edit/{id}")
    public String getGoodsForEdit(@PathVariable("id")Integer id,Model model){
        Goods goods = goodService.getGoodsById(id);
        model.addAttribute("goods",goods);
        model.addAttribute("loc",goods.getLocation());
        return  "info/edit";
    }

    @ResponseBody
    @PostMapping("/edit")
    public Map getGoodsForEdit(@RequestParam(value = "file",required = false) MultipartFile file){
        Map<String,Object> map = FileUtils.upload(file,file.getOriginalFilename());
        return  map;
    }

//    @PostMapping("/add")
//    public String addGoods(Goods goods,HttpSession session){
//        String sellername = (String) session.getAttribute("sellerUser");
//        goods.setSellername(sellername);
//
//        goodService.addGoods(goods);
//    }

}
