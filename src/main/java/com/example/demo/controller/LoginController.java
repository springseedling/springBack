package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.handler.NoAuth;
import com.example.demo.model.WXAuth;
import com.example.demo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/getSessionId")
    @NoAuth
    public Result getSessionId(String code){
        return loginService.getSessionId(code);

    }
    @GetMapping("/authLogin")
    @NoAuth
//    public Result authLogin(@RequestBody WXAuth wxAuth) {
//        System.out.println(wxAuth);
//        Result result = loginService.authLogin(wxAuth);
//        log.info("{}",result);
//        return result;
//    }
    public Result authLogin(String encryptedData, String iv, String sessionId) {
        Result result = loginService.authLogin(encryptedData,iv,sessionId);
        log.info("{}",result.toString());
        return result;
    }
    @GetMapping("loginInfo")
    public Result UserInfo(boolean refresh){
        return loginService.loginInfo(refresh);
    }
    @GetMapping("/authToken")
    public Result authToken(){
        return Result.SUCCESS("已验证登录身份！");
    }
}
