package com.atguigu.springboot04webrestfulcrud.controller;


import com.atguigu.springboot04webrestfulcrud.Dto.GoodsDto;
import com.atguigu.springboot04webrestfulcrud.Service.GoodService;
import com.atguigu.springboot04webrestfulcrud.Util.FileUtils;


import com.atguigu.springboot04webrestfulcrud.entities.Buyer;
import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import com.atguigu.springboot04webrestfulcrud.entities.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
    @GetMapping("/seller/goods")
    public Map<String,Object> getGoodsBySeller(HttpSession session){
        Seller seller = (Seller) session.getAttribute("seller");
        List<Goods> goods = null;
        if(seller != null){
            goods = goodService.getGoodsBySeller(seller.getName());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("allgoods",goods);
        return map;
    }

    @GetMapping("/detail/{id}")
    public String getGoods(@PathVariable("id") Integer id, Model model){
        Goods goods = goodService.getGoodsById(id);
        model.addAttribute("goods",goods);
        return "info/detail";
    }

    @GetMapping("/seller/detail/{id}")
    public String getGoodsById(@PathVariable("id") Integer id, Model model){
        Goods goods = goodService.getGoodsById(id);
        model.addAttribute("goods",goods);
        return "info/sellerdetail";
    }

    @ResponseBody
    @PutMapping("/edit")
    public Integer updateGoodsById(Goods goods){
        goodService.updateGoodsById(goods);
        return goods.getId();
    }

    @GetMapping("/edit/{id}")
    public String getGoodsForEdit(@PathVariable("id")Integer id,Model model){
        Goods goods = goodService.getGoodsById(id);
        model.addAttribute("goods",goods);
        model.addAttribute("loc",goods.getLocation());
        return  "info/edit";
    }

    @RequestMapping("/success")
    public String EditSuccess(){
        return  "redirect:/success.html";
    }

    @ResponseBody
    @PostMapping("/edit")
    public Map getGoodsForEdit(@RequestParam(value = "file",required = false) MultipartFile file){
        Map<String,Object> map = FileUtils.upload(file,file.getOriginalFilename());
        return  map;
    }

    @ResponseBody
    @PostMapping("/add")
    public Integer addGoods(Goods goods,HttpSession session){
        String sellername = ((Seller) session.getAttribute("seller")).getName();
        goods.setSellername(sellername);
        goodService.addGoods(goods);
        return  goods.getId();
    }

//    @RequestMapping("/success")
//    public ModelAndView Success(@ModelAttribute("id")Integer id, RedirectAttributes redirectAttributes){
//        Map<String, ?> flashAttributes = redirectAttributes.getFlashAttributes();
//        ModelAndView modelAndView = new ModelAndView("/info/edSuccess");
//        modelAndView.addObject("id",flashAttributes.get("id"));
//        return   modelAndView;
//    }

      @ResponseBody
      @GetMapping("/buyer")
      public Map<String,Object> getBuyerGoods(HttpSession session){
          Buyer buyer = (Buyer) session.getAttribute("buyer");
          Map<String,Object> map = new HashMap<>();
          List<Goods> buygoods = goodService.getGoodsByBuyer(buyer.getName());
          map.put("buygoods",buygoods);
          List<Goods> nobuygoods = goodService.getnopurchasegoods(buyer.getName());
          map.put("nobuygoods",nobuygoods);
          return map;
      }

     @ResponseBody
     @GetMapping("/buyer/lists")
     public Map<String,Object> getnopurchasegoods(HttpSession session){
         Buyer buyer = (Buyer) session.getAttribute("buyer");
         Map<String,Object> map = new HashMap<>();
         List<Goods> buygoods = goodService.getnopurchasegoods(buyer.getName());
         map.put("buygoods",buygoods);
         return map;
     }

    @GetMapping("/buyer/detail/{id}")
    public String getGoodsForBuyer(@PathVariable("id") Integer id, Model model){
        Goods goods = goodService.getGoodsById(id);
        String buyername = goodService.getGoodsByFinance(id);
        model.addAttribute("goods",goods);
        if(buyername == null){
            return "info/unbuydetail";
        }else {
            return "info/buydetail";
        }
    }

    @ResponseBody
    @GetMapping("/buyer/finance")
    public Map<String,Object> getGoodsForBuyer( HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Buyer buyer = (Buyer) session.getAttribute("buyer");
        List<GoodsDto> finance = goodService.getFinance(buyer.getName());
        map.put("products",finance);
        return map;
    }
}