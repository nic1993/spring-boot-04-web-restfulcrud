package com.ntes.work.mapper;

import com.ntes.work.Dto.GoodsDto;
import com.ntes.work.entities.Goods;
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

    @Insert({
            "<script>",
            "insert into finance(buyerid,buyername,goodsid,time,goodsnum,price)" ,
            " values ",
            "<foreach collection='goodsDtos' item='dto' index='key' separator=','>" ,
            "(#{dto.buyerid},#{dto.buyername},#{dto.goodsid},#{dto.time},#{dto.goodsnum},#{dto.price})" ,
            "</foreach>",
             "</script>"
    })
    public Integer insertFinance(@Param(value = "goodsDtos") List<GoodsDto> goodsDtos);

    @Select("select f.goodsid,f.time,g.goodsname,f.price,g.location,f.goodsnum from finance f left join goods g on f.goodsid=g.id where f.buyername=#{buyername}")
    public List<GoodsDto> getFinance(String buyername);
}

