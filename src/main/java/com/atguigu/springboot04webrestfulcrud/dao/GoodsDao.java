package com.atguigu.springboot04webrestfulcrud.dao;

import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import com.atguigu.springboot04webrestfulcrud.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodsDao {

    @Autowired
    GoodsMapper goodsMapper;

    public List<Goods> getAllGoods(){
        return goodsMapper.getAllGoods();
    }

    public  List<Goods> getGoodsBySeller(String sellername){
       return  goodsMapper.getGoodsBySeller(sellername);
    }

    public Goods getGoodsById(Integer id){
       return goodsMapper.getGoodsById(id);
    }

    public  void updateGoodsById(Goods goods){goodsMapper.updateGoods(goods);}

    public void addGoods(Goods goods){goodsMapper.addGoods(goods);}

}
