package com.atguigu.springboot04webrestfulcrud.Service;

import com.atguigu.springboot04webrestfulcrud.dao.ShoppingCartDao;
import com.atguigu.springboot04webrestfulcrud.entities.CartGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShoppingCartService {

    @Autowired
    ShoppingCartDao shoppingCartDao;

    public Integer addShoppingCart(CartGoods goods){
        return shoppingCartDao.addShoppingCart(goods);
    }

    public List<CartGoods> getCartGoodsList(String buyername){
        return shoppingCartDao.getCartGoodsList(buyername);
    }
}
