package com.ntes.work.Service;


import com.ntes.work.Dto.GoodsDto;
import com.ntes.work.dao.GoodsDao;
import com.ntes.work.entities.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Goods getGoodsByIdFromFinance(Integer id){
        return goodsDao.getGoodsByIdFromFinance(id);
    }

    @Transactional(rollbackFor = Exception.class)
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

    public List<GoodsDto> getFinance(String buyername){
        return goodsDao.getFinance(buyername);
    }

    public Integer insertFinance(List<GoodsDto> goodsDtos){
        return goodsDao.insertFinance(goodsDtos);
    }

    public List<Goods> getGoodsFromCart(String buyername){
        return goodsDao.getGoodsFromCart(buyername);
    }

    public void updateGoodsNum(List<Goods> goods){
        goodsDao.updateGoodsNum(goods);
    }

    public Integer deletGoodsById(Integer id){
        return goodsDao.deletGoodsById(id);
    }


}
