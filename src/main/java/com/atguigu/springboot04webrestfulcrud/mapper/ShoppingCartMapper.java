package com.atguigu.springboot04webrestfulcrud.mapper;

import com.atguigu.springboot04webrestfulcrud.entities.CartGoods;
import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    @Select("select goodsid,goodsname,goodsnum,goodsprice from ShoppingCart where buyername=#{buyername}")
    public List<CartGoods> getCartGoodsList(String buyername);

    @Insert("insert into ShoppingCart(goodsid,goodsname,goodsnum,goodsprice,buyername,buyerid)" +
    "values(#{goodsid},#{goodsname},#{goodsnum},#{goodsprice},#{buyername},#{buyerid})")
    public Integer addShoppingCart(CartGoods goods);
}
