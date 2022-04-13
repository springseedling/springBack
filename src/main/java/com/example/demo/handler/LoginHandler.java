package com.example.demo.handler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.common.RedisKey;
import com.example.demo.common.Result;
import com.example.demo.pojo.dto.UserDto;
import com.example.demo.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginHandler implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(!handlerMethod.hasMethodAnnotation(NoAuth.class)){
            String token = request.getHeader("Authorization");
            boolean vertify = JWTUtils.verify(token);
            if(!vertify){
                return noLoginResponse(response);
            }
            String userJson = redisTemplate.opsForValue().get(RedisKey.TOKEN+token);
            if (StringUtils.isBlank(userJson)) {
                return noLoginResponse(response);
            }
            UserDto userDto = JSON.parseObject(userJson, UserDto.class);
            UserThreadLocal.put(userDto);
        }
        return true;
    }
    private boolean noLoginResponse(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.FAIL("未登录")));
        return false;
    }
}
