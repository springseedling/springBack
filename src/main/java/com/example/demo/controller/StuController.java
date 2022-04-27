package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.handler.NoAuth;
import com.example.demo.pojo.Activity;
import com.example.demo.pojo.Record;
import com.example.demo.pojo.Stu;
import com.example.demo.service.RecordService;
import com.example.demo.service.StuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/stu")
public class StuController {
    @Resource
    StuService stuService;
    @Resource
    RecordService recordService;

    @GetMapping("/getStuById/{id}")
    public Result getStuById(@PathVariable("id") int id){
        Stu stu = stuService.getStuById(id);
        return Result.SUCCESS(stu);
    }

    @GetMapping("/enterAct")
    public Result enterAct(int user_id, int item_id, int org_id){
        if(recordService.enterAct(user_id,item_id,org_id)==1) {
            return Result.SUCCESS("报名成功！");
        }else if(recordService.enterAct(user_id,item_id,org_id)==-1){
            return Result.RECORD_HAS_EXISTED();
        }else{
            return Result.FAIL("报名失败！");
        }
    }

    @GetMapping("/getMyAct")
    @NoAuth
    public Result getMyAct(int user_id, int record_check){
        List<Activity> list = recordService.getMyAct(user_id,record_check);
        return Result.SUCCESS(list);
    }
}
