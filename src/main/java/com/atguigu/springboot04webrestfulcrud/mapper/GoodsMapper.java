package com.atguigu.springboot04webrestfulcrud.mapper;

import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper {

    @Select("select * from goods")
    public List<Goods> getAllGoods();

    @Select("select * from goods where sellername=#{sellername}")
    public List<Goods> getGoodsBySeller(String sellername);

    @Select("select * from goods where id=#{id}")
    public Goods getGoodsById(Integer id);

    @Update("update goods set goodsname=#{goodsname},summary=#{summary},location=#{location},price=#{price} where id=#{id}")
    public void updateGoods(Goods goods);


    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into goods(sellername,goodsname,price,location,info,summary) " +
            "values(#{sellername},#{goodsname},#{price},#{location},#{info},#{summary})")
    public void addGoods(Goods goods);

    @Select("select * from goods where id not in (select goodsid from finance where buyername=#{buyername})")
    public List<Goods> getnopurchasegoods(String buyername);


}
