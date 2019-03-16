package com.ntes.work.dao;

import com.ntes.work.Dto.GoodsDto;
import com.ntes.work.entities.CartGoods;
import com.ntes.work.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoppingCartDao {

    @Autowired
    ShoppingCartMapper shoppingCartMapper;



    public Integer addShoppingCart(CartGoods goods){
           return shoppingCartMapper.addShoppingCart(goods);
    }

    public List<GoodsDto> getCartGoodsList(String buyername){
        return shoppingCartMapper.getCartGoodsList(buyername);
    }

    public String getCartGoodsById(Integer id){
        return shoppingCartMapper.getCartGoodsById(id);
    }

    public Integer updateCartGoodsById(CartGoods cartGoods){return shoppingCartMapper.updateCartGoodsById(cartGoods);}

    public Integer updateCartGoodsNum(Integer id,Integer num,String buyername){return  shoppingCartMapper.updateCartGoodsNum(id,num,buyername);}

    public Integer deleteShoppingCart(String buyername){
        return shoppingCartMapper.deleteShoppingCart(buyername);
    }
}
