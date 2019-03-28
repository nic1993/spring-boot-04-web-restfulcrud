package com.ntes.work.config;

import com.ntes.work.component.BuyerLoginHandlerInterceptor;
import com.ntes.work.component.SellerLoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {



    private static final String DATE_TIME_FORMATE = "yyyy-MM-dd HH:mm:ss";

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/seller.html").setViewName("seller/home");
                registry.addViewController("/info/edit.html").setViewName("info/edit");
                registry.addViewController("/success.html").setViewName("info/success");
                registry.addViewController("/buyer.html").setViewName("buyer/home");
//                registry.addViewController("/buyer/home.html").setViewName("/buyer/home");
                registry.addViewController("/buyer/cart.html").setViewName("buyer/cart");
                registry.addViewController("/buyer/finance.html").setViewName("buyer/finance");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {

                registry.addInterceptor(new BuyerLoginHandlerInterceptor()).addPathPatterns("/buyer","/buyer/lists","/buyer/detail/{id}","/buyer/finance","/buyer.html","/buyer/finance.html"
                        ,"buyer/cart.html","/buyer/cart.html");
                registry.addInterceptor(new SellerLoginHandlerInterceptor()).addPathPatterns("/goods/{name}","/seller/detail/{id}","/edit","/edit/{id}","/add","/seller.html",
                        "/info/edit.html");

               }
        };
        return  webMvcConfigurer;
    }


    @Bean
    public Converter<String, Date> convertDateTime(){
        return new Converter<String, Date>(){
            @Override
            public Date convert(String source) {
                Date date = null;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMATE);
                try{
                    simpleDateFormat.parse(source);
                }catch (ParseException e){

                }
                return date;
            }
        };
    }


}
