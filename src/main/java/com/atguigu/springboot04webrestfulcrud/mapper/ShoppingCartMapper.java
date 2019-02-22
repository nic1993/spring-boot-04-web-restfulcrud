package com.atguigu.springboot04webrestfulcrud.mapper;

import com.atguigu.springboot04webrestfulcrud.entities.CartGoods;
import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    @Select("select goodsid,goodsname,goodsnum,goodsprice from ShoppingCart where buyername=#{buyername}")
    public List<CartGoods> getCartGoodsList(String buyername);

    @Select("select goodsname from ShoppingCart where goodsid=#{id}")
    public String getCartGoodsById(Integer id);

    @Update("update ShoppingCart set goodsname=#{goodsname},goodsnum=#{goodsnum},goodsprice=#{goodsprice} where goodsid=#{goodsid}")
    public Integer updateCartGoodsById(CartGoods cartGoods);

    @Insert("insert into ShoppingCart(goodsid,goodsname,goodsnum,goodsprice,buyername,buyerid)" +
    "values(#{goodsid},#{goodsname},#{goodsnum},#{goodsprice},#{buyername},#{buyerid})")
    public Integer addShoppingCart(CartGoods goods);
}
