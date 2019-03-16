package com.ntes.work.dao;

import com.ntes.work.Dto.GoodsDto;
import com.ntes.work.entities.Goods;
import com.ntes.work.mapper.FinanceMapper;
import com.ntes.work.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodsDao {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    FinanceMapper financeMapper;


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

    public List<Goods> getGoodsByBuyer(String buyername){
        return  financeMapper.getGoodsByBuyer(buyername);
    }

    public List<Goods> getnopurchasegoods(String buyername){
        return goodsMapper.getnopurchasegoods(buyername);
    }

    public String getGoodsByFinance(Integer id){
        return financeMapper.getGoodsByFinance(id);
    }
    public List<GoodsDto> getFinance(String buyername){
          return financeMapper.getFinance(buyername);
    }

    public Integer insertFinance(List<GoodsDto> goodsDtos){
          return financeMapper.insertFinance(goodsDtos);
    }

    public List<Goods> getGoodsFromCart(String buyername){
        return goodsMapper.getGoodsFromCart(buyername);
    }

    public void updateGoodsNum(List<Goods> goods){
        goodsMapper.updateGoodsNum(goods);
    }

    public Integer deletGoodsById(Integer id){
        return goodsMapper.deletGoodsById(id);
    }

    public Goods getGoodsByIdFromFinance(Integer id){
        return goodsMapper.getGoodsByIdFromFinance(id);
    }





}
