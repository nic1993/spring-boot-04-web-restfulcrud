package com.atguigu.springboot04webrestfulcrud.Service;

import com.atguigu.springboot04webrestfulcrud.Dto.GoodsDto;
import com.atguigu.springboot04webrestfulcrud.dao.GoodsDao;
import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {

    @Autowired
    GoodsDao goodsDao;


    public List<Goods> getAllGoods(){
        return goodsDao.getAllGoods();
    }

    public List<Goods> getGoodsBySeller(String sellername){
       return  goodsDao.getGoodsBySeller(sellername);
    }

    public Goods getGoodsById(Integer id){
        return  goodsDao.getGoodsById(id);
    }

    public void updateGoodsById(Goods goods){goodsDao.updateGoodsById(goods);}

    public void addGoods(Goods goods){goodsDao.addGoods(goods);}

    public List<Goods> getGoodsByBuyer(String buyername){
        return  goodsDao.getGoodsByBuyer(buyername);
    }

    public List<Goods> getnopurchasegoods(String buyername){
        return goodsDao.getnopurchasegoods(buyername);
    }

    public String getGoodsByFinance(Integer id){
        return goodsDao.getGoodsByFinance(id);
    }
}
