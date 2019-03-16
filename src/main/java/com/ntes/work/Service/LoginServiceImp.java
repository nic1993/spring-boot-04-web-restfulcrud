package com.ntes.work.Service;

import com.ntes.work.dao.LoginDao;
import com.ntes.work.entities.Buyer;
import com.ntes.work.entities.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp {

    @Autowired
    LoginDao loginDao;

    public Buyer getBuyerByName(String name, String password){
        if(name == null) return  null;
        Buyer buyer = loginDao.getBuyerByName(name);
        return  buyer;
    }

    public Seller getSellerByName(String name,String password){
        if(name == null) return  null;
        Seller seller = loginDao.getSellerByName(name);
        return seller;
    }

}
