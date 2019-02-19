package com.atguigu.springboot04webrestfulcrud.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ntes.goodservice.provider.Service.GoodService;
import com.ntes.goodservice.provider.entities.Goods;
import org.springframework.stereotype.Component;

@Component
public class TestGoodService {

    @Reference
    GoodService goodService;

    public Goods hello(){
        return goodService.getGoodsById(1);
    }


}
