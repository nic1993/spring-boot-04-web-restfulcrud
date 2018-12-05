package com.atguigu.springboot04webrestfulcrud.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

//给容器中加入自己定义的错误属性
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {



    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String,Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company","atguigu");

        Map<String,Object> ext = (Map<String,Object>)webRequest.getAttribute("etc",0);
        map.put("ext",map);
        return map;
    }
}
