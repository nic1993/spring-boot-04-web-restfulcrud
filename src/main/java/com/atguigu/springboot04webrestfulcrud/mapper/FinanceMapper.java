package com.atguigu.springboot04webrestfulcrud.mapper;

import com.atguigu.springboot04webrestfulcrud.Dto.GoodsDto;
import com.atguigu.springboot04webrestfulcrud.entities.CartGoods;
import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FinanceMapper {

    @Select("select * from goods where id in (select goodsid from finance where buyername=#{buyername})")
    public List<Goods> getGoodsByBuyer(String buyername);

    @Select("select buyername from finance where goodsid=#{id}")
    public String getGoodsByFinance(Integer id);

    @Insert("insert into finance(buyerid,buyername,goodsid,time,goodsnum,curprice) values " +
            "<foreach collection='list' item='goodsDto' index='index' separator=','>" +
            "(#{goodsDto.buyerid}，#{goodsDto.buyername},#{goodsDto.goodsid},#{goodsDto.time},#{goodsDto.goodsnum},#{goodsDto.price})" +
            "</foreach>")
    public void insertFinance(@Param(value = "list") List<GoodsDto> goodsDtos);

    @Select("select f.goodsid,f.time,g.goodsname,f.curprice,g.location  from finance f left join goods g on f.goodsid=g.id where f.buyername=#{buyername}")
    public List<GoodsDto> getFinance(String buyername);
}

