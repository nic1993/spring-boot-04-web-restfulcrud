package com.atguigu.springboot04webrestfulcrud.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ntes.loginserviceprovider.Service.LoginService;

import com.ntes.loginserviceprovider.entities.Seller;

import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    @Reference
    LoginService loginService;

    public Seller hello(){
        System.out.println(loginService==null);

        return loginService.getSellerByName("seller","relles");
    }
}
