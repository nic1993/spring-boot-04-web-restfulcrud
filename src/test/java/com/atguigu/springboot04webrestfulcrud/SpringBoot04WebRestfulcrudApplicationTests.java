package com.atguigu.springboot04webrestfulcrud;

import com.atguigu.springboot04webrestfulcrud.entities.Buyer;
import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import com.atguigu.springboot04webrestfulcrud.mapper.GoodsMapper;
import com.atguigu.springboot04webrestfulcrud.mapper.Loginmapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot04WebRestfulcrudApplicationTests {

	@Autowired
    Loginmapper loginmapper;

	@Autowired
	GoodsMapper goodsMapper;

	@Test
	public void contextLoads() {
		Buyer buyer =  loginmapper.getBuyerByName("buyer");
		System.out.println(buyer.getName() + " " + buyer.getPassword());
	}


	@Test
	public  void test(){
		Goods goods = goodsMapper.getGoodsById(1);
		System.out.println(goods.getGoodsname());

	}

}
