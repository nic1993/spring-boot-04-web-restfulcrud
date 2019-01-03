package com.atguigu.springboot04webrestfulcrud.mapper;

import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {

    @Select("select * from goods")
    public List<Goods> getAllGoods();

    @Select("select * from goods where sellername=#{sellername}")
    public List<Goods> getGoodsBySeller(String sellername);

    @Select("select * from goods where id=#{id}")
    public Goods getGoodsById(Integer id);



}
