package com.ntes.work.Service;

import com.ntes.work.Dto.GoodsDto;
import com.ntes.work.dao.ShoppingCartDao;
import com.ntes.work.entities.CartGoods;
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

    public List<GoodsDto> getCartGoodsList(String buyername){
        return shoppingCartDao.getCartGoodsList(buyername);
    }

    public String getCartGoodsById(Integer id){
        return shoppingCartDao.getCartGoodsById(id);
    }

    public Integer updateCartGoodsById(CartGoods cartGoods){return shoppingCartDao.updateCartGoodsById(cartGoods);}

    public Integer updateCartGoodsNum(Integer id,Integer num,String buyername){return  shoppingCartDao.updateCartGoodsNum(id,num,buyername);}

    public Integer deleteShoppingCart(String buyername){
        return shoppingCartDao.deleteShoppingCart(buyername);
    }
}
