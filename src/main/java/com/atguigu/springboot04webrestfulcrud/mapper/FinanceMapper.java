package com.atguigu.springboot04webrestfulcrud.mapper;

import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FinanceMapper {

    @Select("select * from goods where id in (select goodsid from finance where buyername=#{buyername})")
    public List<Goods> getGoodsByBuyer(String buyername);


    @Select("select buyername from finance where goodsid=#{id}")
    public String getGoodsByFinance(Integer id);
}
