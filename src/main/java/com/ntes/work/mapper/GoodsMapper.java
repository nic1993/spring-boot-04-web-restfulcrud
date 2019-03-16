package com.ntes.work.mapper;


import com.ntes.work.entities.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper {

    @Select("select * from goods")
    public List<Goods> getAllGoods();

    @Select("select * from goods where sellername=#{sellername}")
    public List<Goods> getGoodsBySeller(String sellername);

    @Select("select g.id,g.goodsname,g.location,g.info,g.summary,f.price,f.goodsnum from goods g left join finance f on f.goodsid=g.id where id=#{id}")
    @Results({
            @Result(column = "goodsnum",property = "sellnums")
    })
    public Goods getGoodsByIdFromFinance(Integer id);

    @Select("select * from goods where id=#{id}")
    public Goods getGoodsById(Integer id);

    @Update("update goods set goodsname=#{goodsname},summary=#{summary},location=#{location},price=#{price} where id=#{id}")
    public void updateGoods(Goods goods);

    @Update({
            "<script>",
            "<foreach collection='list' item='goods' index='key' separator=';'>" ,
            "update goods",
            "set sellnums=#{goods.sellnums} where id = #{goods.id}",
            "</foreach>",
            "</script>"
    })
    public void updateGoodsNum(@Param(value = "list") List<Goods> goods);




    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into goods(sellername,goodsname,price,location,info,summary) " +
            "values(#{sellername},#{goodsname},#{price},#{location},#{info},#{summary})")
    public void addGoods(Goods goods);

    @Select("select * from goods where id not in (select goodsid from finance where buyername=#{buyername})")
    public List<Goods> getnopurchasegoods(String buyername);

    @Select("select g.id,g.sellnums from ShoppingCart c left join goods g on g.id = c.goodsid where c.buyername=#{buyername}")
    public List<Goods> getGoodsFromCart(String buyername);

    @Delete("delete from goods where id=#{id}")
    public Integer deletGoodsById(Integer id);


}
