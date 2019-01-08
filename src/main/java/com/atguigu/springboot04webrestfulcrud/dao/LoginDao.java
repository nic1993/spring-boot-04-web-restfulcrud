package com.atguigu.springboot04webrestfulcrud.dao;


import com.atguigu.springboot04webrestfulcrud.entities.Buyer;
import com.atguigu.springboot04webrestfulcrud.entities.Seller;
import com.atguigu.springboot04webrestfulcrud.mapper.Loginmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDao {

    @Autowired
    Loginmapper loginmapper;

    public Buyer getBuyerByName(String name){
        Buyer buyer = loginmapper.getBuyerByName(name);
        return  buyer;
    }

    public Seller getSellerByName(String name){
        Seller seller = loginmapper.getSellerByName(name);
        return  seller;
    }

}
