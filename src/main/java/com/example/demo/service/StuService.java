package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;
import com.example.demo.mapper.StuMapper;
import com.example.demo.pojo.Record;
import com.example.demo.pojo.Stu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class StuService {
    @Resource
    StuMapper stuMapper;

    public Stu getStuById(int id){
        return stuMapper.selectById(id);
    }

}
