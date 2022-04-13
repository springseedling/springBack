package com.example.demo.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.common.RedisKey;
import com.example.demo.common.Result;
import com.example.demo.handler.UserThreadLocal;
import com.example.demo.mapper.UserMapper;

import com.example.demo.model.WxUserInfo;
import com.example.demo.pojo.User;
import com.example.demo.pojo.dto.UserDto;
import com.example.demo.utils.JWTUtils;
import net.sf.jsqlparser.statement.select.Limit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.xml.transform.Source;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {

    @Autowired
    WxService wxService;

    @Autowired(required = true)
    UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;


    public Result getSessionId(String code){
        String url= "https://api.weixin.qq.com/sns/jscode2session?appid=wx47e813aba2a68a82&secret=dc7175ef279c8768a9cee7b03f64330d&js_code="+code+"&grant_type=authorization_code";
        String res = HttpUtil.get(url);
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(RedisKey.WX_SESSION_ID + uuid,res,30,TimeUnit.MINUTES);
        String json =  redisTemplate.opsForValue().get(RedisKey.WX_SESSION_ID + uuid);
        Map<String, String> map = new HashMap<>();//
        map.put("sessionId",uuid);
        return Result.SUCCESS(map);
    }


    public Result authLogin(String encryptedData, String iv, String sessionId){
        try {
            String json = wxService.wxDecrypt(encryptedData,sessionId,iv);
            WxUserInfo wxUserInfo = JSON.parseObject(json,WxUserInfo.class);
            String openId = wxUserInfo.getOpenId();
            User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getOpenId,openId).last("Limit 1"));
            UserDto userDto = new UserDto();
            userDto.from(wxUserInfo);
            if(user==null){
                return this.register(userDto);
            }else{
                userDto.setId(user.getId());
                return this.login(userDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.FAIL();
    }
    private Result login(UserDto userDto){
        String token = JWTUtils.sign(userDto.getId());
        userDto.setToken(token);
        userDto.setOpenId(null);
        userDto.setWxUnionId(null);
        redisTemplate.opsForValue().set(RedisKey.TOKEN+token,JSON.toJSONString(userDto),7,TimeUnit.DAYS);
        return Result.SUCCESS(userDto);
    }
    private Result register(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        this.userMapper.insert(user);
        userDto.setId(user.getId());
        return this.login(userDto);
    }

    public Result loginInfo(Boolean refresh){
        UserDto userDto = UserThreadLocal.get();
        if(refresh){
            String token=JWTUtils.sign(userDto.getId());
            System.out.println(token);
            userDto.setToken(token);
            redisTemplate.opsForValue().set(RedisKey.TOKEN + token,JSON.toJSONString(userDto),7,TimeUnit.DAYS);
        }
        return Result.SUCCESS(userDto);
    }
}
