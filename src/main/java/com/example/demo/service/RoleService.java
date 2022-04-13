package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.PhoneCode;
import com.example.demo.common.ResultCode;
import com.example.demo.controller.SmsController;
import com.example.demo.mapper.SignInMapper;
import com.example.demo.mapper.SignUpMapper;
import com.example.demo.pojo.SignIn;
import com.example.demo.pojo.SignUp;
import com.example.demo.utils.TxSmsUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleService {
    @Resource
    SignInMapper signInMapper;
    @Resource
    SignUpMapper signUpMapper;
    public int addRole(String username, String password, String code, String role){
        if(PhoneCode.Code.equals(code)){

            SignUp signUp = new SignUp();
            SignIn signIn = new SignIn();

            signUp.setUsername(username);
            signUp.setPassword(password);
            signUp.setStatus("已审核");
            signUp.setRole(role);
            int r= signUpMapper.insert(signUp);

            signIn.setUid(signUp.getUid());
            signIn.setUsername(username);
            signIn.setPassword(password);
            signIn.setRole(role);
            signInMapper.insert(signIn);

            return r;
        }else{
            return 0;
        }
    }
    public SignIn findRole(String username, String password){
        QueryWrapper queryWrapper = new QueryWrapper<SignIn>();
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",password);
        return signInMapper.selectOne(queryWrapper);
    }
}
