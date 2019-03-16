package com.ntes.work.mapper;

import com.ntes.work.entities.Buyer;
import com.ntes.work.entities.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Loginmapper {

      @Select("select * from seller where name =#{name}")
      public Seller getSellerByName(String name);

      @Select("select * from buyer where name =#{name}")
      public Buyer getBuyerByName(String name);
}
