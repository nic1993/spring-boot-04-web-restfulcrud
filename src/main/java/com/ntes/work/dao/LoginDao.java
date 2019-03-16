package com.ntes.work.dao;


import com.ntes.work.entities.Buyer;
import com.ntes.work.entities.Seller;
import com.ntes.work.mapper.Loginmapper;
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
