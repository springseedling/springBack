package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.handler.NoAuth;
import com.example.demo.mapper.ActivityMapper;
import com.example.demo.pojo.Activity;
import com.example.demo.service.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/org")
@Api(tags = "支教组织")
public class OrgController {
    @Autowired
    OrgService orgService;
    @GetMapping("/uploadActivity")
    public Object uploadActivity(String org_name, int org_id, String item_name, String address, String course, String grade, int need_num, int present_num, String release_time, String img, String org_profile, String act_profile, String join_time, String act_time, String join_start, String join_end){
        if(orgService.UploadActivity(org_name, org_id, item_name, address, course, grade, need_num, present_num, release_time, img, org_profile, act_profile, join_time, act_time, join_start, join_end)==1) {
            return Result.SUCCESS("操作成功！");
        }else {
            return Result.FAIL("操作失败！");
        }
    }
    @GetMapping("/getActList")
    @NoAuth
    public List<Activity> getActList(){
        return orgService.getActList();
    }
    @GetMapping("/getALById")
    public Activity getALById(String id){
        return orgService.getALById(id);
    }
    @GetMapping("/getALByName")
    @NoAuth
    public List<Activity> getALByName(String item_name){
        return orgService.getALByName(item_name);
    }
    @GetMapping("/page")
    @NoAuth
    public Result page(String item_name, String address, String status, int page, int size){
        Map<String,Object> map = orgService.page(item_name,address,status,page,size);
        return Result.SUCCESS(map);
    }
}
