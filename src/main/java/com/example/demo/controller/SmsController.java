package com.example.demo.controller;

import com.example.demo.common.PhoneCode;
import com.example.demo.common.Result;
import com.example.demo.handler.NoAuth;
import com.example.demo.utils.TxSmsUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendSms")
@Api(tags = "发送手机验证码")
public class SmsController {

    @GetMapping("/code")
    @NoAuth
    public static Result sendSms(String number){
        TxSmsUtils txSmsUtils = new TxSmsUtils();
        PhoneCode.Code = txSmsUtils.sendSms(number);
        System.out.println(PhoneCode.Code);
        if(PhoneCode.Code!="0000"){
            return Result.SUCCESS("获取成功！");
        }else{
            return Result.FAIL("手机号有误！");
        }
    }
}
