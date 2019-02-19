package com.atguigu.springboot04webrestfulcrud.config;

import com.atguigu.springboot04webrestfulcrud.component.MyLocaleResolver;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.FileNotFoundException;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    public static String REAL_PATH;
    public static String TEMP_PATH;
    public static String ROOT_PATH;


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送atguiguqingqiu，也来到success页面
        registry.addViewController("/atguigu").setViewName("success");
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
                registry.addViewController("/seller/home.html").setViewName("/seller/home");
                registry.addViewController("/buyer.html").setViewName("/buyer/home.html");
                registry.addViewController("/buyer/home.html").setViewName("/buyer/home");
                registry.addViewController("/buyer/cart.html").setViewName("/buyer/cart");
                registry.addViewController("/info/edit.html").setViewName("/info/edit");
                registry.addViewController("/success.html").setViewName("info/edSuccess");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {

                //super.addInterceptors(registry);
                //静态资源；  *.css , *.js
                //SpringBoot已经做好了静态资源映射
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/login.html","/","/user/login");
            }
        };
        return  webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return  new MyLocaleResolver();
    }
}
