package com.atguigu.springboot04webrestfulcrud.Service;

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

    public  Goods getGoodsById(Integer id){
        return  goodsDao.getGoodsById(id);
    }
}
