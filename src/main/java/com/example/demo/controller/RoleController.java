package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.handler.NoAuth;
import com.example.demo.mapper.SignUpMapper;
import com.example.demo.pojo.SignIn;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @GetMapping("/signUp")
    @NoAuth
    public Result addRole(String username, String password, String code, String role){
        if(roleService.addRole(username, password, code, role)==1){
            return Result.SUCCESS("注册成功！");
        }else{
            return Result.FAIL("注册失败！");
        }
    }

    @GetMapping("/signIn")
    @NoAuth
    public Result findRole(String username, String password){
        SignIn one = roleService.findRole(username,password);
        if(one!=null){
            return Result.SUCCESS(one);
        }else{
            return Result.FAIL("登陆失败！");
        }
    }

}
