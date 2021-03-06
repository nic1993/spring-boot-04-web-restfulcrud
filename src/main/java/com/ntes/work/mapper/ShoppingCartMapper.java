package com.ntes.work.mapper;

import com.ntes.work.Dto.GoodsDto;
import com.ntes.work.entities.CartGoods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    @Select("select s.goodsid,g.goodsname,s.goodsnum,g.price from ShoppingCart s left join goods g on s.goodsid=g.id where buyername=#{buyername}")
    public List<GoodsDto> getCartGoodsList(String buyername);

    @Select("select goodsname from ShoppingCart where goodsid=#{id}")
    public String getCartGoodsById(Integer id);

    @Update("update ShoppingCart set goodsname=#{goodsname},goodsnum=#{goodsnum},goodsprice=#{goodsprice} where goodsid=#{goodsid}")
    public Integer updateCartGoodsById(CartGoods cartGoods);

    @Update("update ShoppingCart set goodsnum=#{num} where goodsid=#{id} and buyername=#{buyername}")
    public Integer updateCartGoodsNum(@Param(value = "id") Integer id, @Param(value = "num") Integer num, @Param(value = "buyername") String buyername);

    @Insert("insert into ShoppingCart(goodsid,goodsname,goodsnum,goodsprice,buyername,buyerid)" +
    "values(#{goodsid},#{goodsname},#{goodsnum},#{goodsprice},#{buyername},#{buyerid})")
    public Integer addShoppingCart(CartGoods goods);

    @Delete("delete from ShoppingCart where goodsid in (select goodsid from finance where buyername=#{buyername})")
    public Integer deleteShoppingCart(String buyername);


}
