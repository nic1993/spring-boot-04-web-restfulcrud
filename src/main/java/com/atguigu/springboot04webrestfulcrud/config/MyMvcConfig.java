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
                registry.addViewController("/info/edit.html").setViewName("/info/edit");
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            try {
//                获取跟目录
                File root = new File(ResourceUtils.getURL("classpath:").getPath());
                if (!root.exists()) root = new File("");

                File real = new File(root.getAbsolutePath(), "src/main/resources/static/asserts/img/");
                File temp = new File(root.getAbsolutePath(), "src/main/resources/static/asserts/temp/");

                if (!real.exists()) real.mkdirs();
                if(!temp.exists()) temp.mkdirs();

                REAL_PATH = real.getAbsolutePath();
                TEMP_PATH = temp.getAbsolutePath();
                ROOT_PATH = "/static/asserts/temp/";


                registry.addResourceHandler("asserts/temp/**").addResourceLocations("file:" + TEMP_PATH + "/");
                LoggerFactory.getLogger(MyMvcConfig.class).info("TEMP_PATH=" + TEMP_PATH);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
//        if (mImagesPath.equals("") || mImagesPath.equals("${cbs.imagesPath}")) {
//            String imagesPath = MyMvcConfig.class.getClassLoader().getResource("").getPath();
//            if (imagesPath.indexOf(".jar") > 0) {
//                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
//            } else if (imagesPath.indexOf("classes") > 0) {
//                imagesPath = "file:" + imagesPath.substring(0, imagesPath.indexOf("classes"));
//            }
//            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/")) + "/images/";
//            mImagesPath = imagesPath;
//        }
//        LoggerFactory.getLogger(MyMvcConfig.class).info("imagesPath=" + mImagesPath);
//        registry.addResourceHandler("/temp/**").addResourceLocations(mImagesPath);

    }

    @Bean
    public LocaleResolver localeResolver(){
        return  new MyLocaleResolver();
    }
}
