package com.ntes.work.controller;


import com.ntes.work.Service.LoginServiceImp;
import com.ntes.work.entities.Buyer;
import com.ntes.work.entities.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    public final static int LOGIN_STATUS_LOGIN_SUCCESS = 1;
    public final static int LOGIN_STATUS_WRONG_PASSWORD = 2;
    public final static int LOGIN_STATUS_POLI_NOT_FOUND = 3;

    public final static String LOGIN_STATUS_POLI_NOT_FOUND_STR = "用户不存在";
    public final static String LOGIN_STATUS_WRONG_PASSWORD_STR = "密码错误";
    public final static String LOGIN_STATUS_LOGIN_SUCCESS_STR = "登陆成功";

    @Autowired
    LoginServiceImp loginService;

//    @Reference
//    LoginService loginService;

    @ResponseBody
    @PostMapping("/buyer/login")
    public  Map<String,Object> login(@RequestParam(value = "username",required = false) String username, @RequestParam(value = "password",required = false) String password,
                         Map<String,Object> map, HttpSession session){
        int loginStatusCode = -1;
        String loginStatusStr = "";

        Map<String,Object> resultMap = new HashMap<>();

        Buyer buyer = loginService.getBuyerByName(username,password);

        if(buyer == null){
            loginStatusCode=LOGIN_STATUS_POLI_NOT_FOUND;
            loginStatusStr=LOGIN_STATUS_POLI_NOT_FOUND_STR;
        }
        else if(!buyer.getPassword().equals(password)){
            loginStatusCode=LOGIN_STATUS_WRONG_PASSWORD;
            loginStatusStr=LOGIN_STATUS_WRONG_PASSWORD_STR;
        } else {
            session.setAttribute("buyer", buyer);
            resultMap.put("buyer", buyer);

            loginStatusCode=LOGIN_STATUS_LOGIN_SUCCESS;
            loginStatusStr=LOGIN_STATUS_LOGIN_SUCCESS_STR;
        }
        resultMap.put("loginStatusCode",loginStatusCode);
        resultMap.put("loginStatusStr",loginStatusStr);
        return  resultMap;
    }

    @ResponseBody
    @PostMapping("/seller/login")
    public  Map<String,Object> sellerlogin(@RequestParam(value = "username",required = false) String username, @RequestParam(value = "password",required = false) String password,
                               HttpSession session) {
        int loginStatusCode = -1;
        String loginStatusStr = "";

        Map<String,Object> resultMap = new HashMap<>();

        Seller seller = loginService.getSellerByName(username,password);

        if(seller == null){
            loginStatusCode=LOGIN_STATUS_POLI_NOT_FOUND;
            loginStatusStr=LOGIN_STATUS_POLI_NOT_FOUND_STR;
        }
        else if(!seller.getPassword().equals(password)){
            loginStatusCode=LOGIN_STATUS_WRONG_PASSWORD;
            loginStatusStr=LOGIN_STATUS_WRONG_PASSWORD_STR;
        } else {
            session.setAttribute("seller", seller);
            resultMap.put("seller", seller);

            loginStatusCode=LOGIN_STATUS_LOGIN_SUCCESS;
            loginStatusStr=LOGIN_STATUS_LOGIN_SUCCESS_STR;
        }
            resultMap.put("loginStatusCode",loginStatusCode);
            resultMap.put("loginStatusStr",loginStatusStr);

            return  resultMap;
    }
}
