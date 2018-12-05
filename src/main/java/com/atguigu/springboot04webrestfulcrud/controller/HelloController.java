package com.atguigu.springboot04webrestfulcrud.controller;

import com.atguigu.springboot04webrestfulcrud.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {


    @ResponseBody
    @RequestMapping("/hello")
    public  String hello(@RequestParam("user") String user){

        if(user.equals("aaa")){
            throw new UserNotExistException();
        }
        return  "Hello World";
    }

    @RequestMapping("/success")
    public  String success(Map<String,Object> map){
        map.put("hello","你好");
        map.put("users", Arrays .asList("zhangsan","lisi","wangwu"));
        return  "success";
    }
}
