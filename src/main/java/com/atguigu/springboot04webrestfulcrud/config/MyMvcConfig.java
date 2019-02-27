package com.atguigu.springboot04webrestfulcrud.config;

import com.atguigu.springboot04webrestfulcrud.component.BuyerLoginHandlerInterceptor;
import com.atguigu.springboot04webrestfulcrud.component.MyLocaleResolver;
import com.atguigu.springboot04webrestfulcrud.component.SellerLoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    public static String REAL_PATH;
    public static String TEMP_PATH;
    public static String ROOT_PATH;

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/seller.html").setViewName("/seller/home");
                registry.addViewController("/seller/home.html").setViewName("/seller/home");
                registry.addViewController("/info/edit.html").setViewName("/info/edit");
                registry.addViewController("/success.html").setViewName("/info/success");
                registry.addViewController("/buyer.html").setViewName("/buyer/home.html");
                registry.addViewController("/buyer/home.html").setViewName("/buyer/home");
                registry.addViewController("/buyer/cart.html").setViewName("/buyer/cart");
                registry.addViewController("/buyer/finance.html").setViewName("/buyer/finance");

            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {


//                super.addInterceptors(registry);
//                静态资源；  *.css , *.js
//                SpringBoot已经做好了静态资源映射
                registry.addInterceptor(new BuyerLoginHandlerInterceptor()).addPathPatterns("/buyer","/buyer/lists","/buyer/detail/{id}","/buyer/finance","/buyer.html","/buyer/finance.html","buyer/cart.html","/buyer/cart.html");
                registry.addInterceptor(new SellerLoginHandlerInterceptor()).addPathPatterns("/goods/{name}","/seller/detail/{id}","/edit","/edit/{id}","/add","/seller.html","/info/edit.html");

               }

        };
        return  webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return  new MyLocaleResolver();
    }
}
