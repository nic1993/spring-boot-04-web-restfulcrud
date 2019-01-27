package com.atguigu.springboot04webrestfulcrud.Service;

import com.atguigu.springboot04webrestfulcrud.dao.LoginDao;
import com.atguigu.springboot04webrestfulcrud.entities.Buyer;
import com.atguigu.springboot04webrestfulcrud.entities.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp {

    @Autowired
    LoginDao loginDao;

    public  boolean getBuyerByName(String name,String password){
        if(name == null) return  false;
        Buyer buyer = loginDao.getBuyerByName(name);
        if(buyer == null || !password.equals(buyer.getPassword())){
            return false;
        }
        return  true;
    }

    public Seller getSellerByName(String name,String password){
        if(name == null) return  null;
        Seller seller = loginDao.getSellerByName(name);
        return seller;
    }

}
