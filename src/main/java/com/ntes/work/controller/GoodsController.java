package com.ntes.work.controller;


import com.ntes.work.Dto.GoodsDto;
import com.ntes.work.Service.GoodService;
import com.ntes.work.Util.FileUtils;
import com.ntes.work.entities.Buyer;
import com.ntes.work.entities.Goods;
import com.ntes.work.entities.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        HashMap map = new HashMap();
        FileUtils.upload(file,file.getOriginalFilename(),map);
        return  map;
    }

    @ResponseBody
    @PostMapping("/add")
    public Integer addGoods(Goods goods, HttpSession session){
        String sellername = ((Seller) session.getAttribute("seller")).getName();
        goods.setSellername(sellername);
        goodService.addGoods(goods);
        return  goods.getId();
    }

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
        String buyername = goodService.getGoodsByFinance(id);
        if(buyername == null){
            Goods goods = goodService.getGoodsById(id);
            model.addAttribute("goods",goods);
            return "info/unbuydetail";
        }else {
            Goods goods = goodService.getGoodsByIdFromFinance(id);
            model.addAttribute("goods",goods);
            return "info/buydetail";
        }
    }

    @ResponseBody
    @GetMapping("/buyer/finance")
    public Map<String,Object> getGoodsForBuyer( HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Buyer buyer = (Buyer) session.getAttribute("buyer");
        List<GoodsDto> finance = goodService.getFinance(buyer.getName());
        double sum = 0;
        for(GoodsDto dto : finance){
            sum += dto.getGoodsnum() * dto.getPrice();
        }
        map.put("products",finance);
        map.put("sum",sum);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    public Integer deleteGoodsByid(@RequestParam("id") Integer id){
        Integer row = goodService.deletGoodsById(id);
         return row;
    }
}