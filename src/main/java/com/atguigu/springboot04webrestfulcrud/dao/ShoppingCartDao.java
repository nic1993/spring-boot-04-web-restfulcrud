package com.atguigu.springboot04webrestfulcrud.dao;

import com.atguigu.springboot04webrestfulcrud.entities.CartGoods;
import com.atguigu.springboot04webrestfulcrud.mapper.ShoppingCartMapper;
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

    public List<CartGoods> getCartGoodsList(String buyername){
        return shoppingCartMapper.getCartGoodsList(buyername);
    }
}
