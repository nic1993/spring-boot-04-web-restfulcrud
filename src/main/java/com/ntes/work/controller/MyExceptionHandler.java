package com.ntes.work.controller;

import com.ntes.work.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//异常处理器
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler({UserNotExistException.class})
    public String handlerException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();

        //传入自己的状态码 要不默认是200
        request.setAttribute("javax.servlet.error.status_code",500);

        map.put("code","user.notexist");
        map.put("message",e.getMessage());

        request.setAttribute("etc",map);
        //转发到/error
        return  "forward:/error";
    }



}
