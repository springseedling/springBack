package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.ActivityMapper;
import com.example.demo.pojo.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrgService {
    @Resource
    ActivityMapper activityMapper;

    public int UploadActivity(String org_name, int org_id, String item_name, String address, String course, String grade, int need_num, int present_num, String release_time, String img, String org_profile, String act_profile, String join_time, String act_time, String join_start, String join_end) {
        Activity activity = new Activity();
        activity.setOrg_name(org_name);
        activity.setOrg_id(org_id);
        activity.setItem_name(item_name);
        activity.setAddress(address);
        activity.setCourse(course);
        activity.setGrade(grade);
        activity.setNeed_num(need_num);
        activity.setPresent_num(present_num);
        activity.setRelease_time(release_time);
        activity.setImg(img);
        activity.setOrg_profile(org_profile);
        activity.setAct_profile(act_profile);
        activity.setJoin_time(join_time);
        activity.setAct_time(act_time);
        activity.setJoin_start(join_start);
        activity.setJoin_end(join_end);
        return activityMapper.insert(activity);
    }

    public List<Activity> getActList(){
        QueryWrapper queryWrapper = new QueryWrapper();
        List<Activity> list = activityMapper.selectList(queryWrapper);
        return list;
    }

    public Activity getALById(String id){
        return activityMapper.selectById(id);
    }

    public List<Activity> getALByName(String item_name){
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("item_name",item_name);
        return activityMapper.selectList(queryWrapper);
    }

    public Map<String, Object> page(String item_name, String address, String status, int page, int size){
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("item_name",item_name);
        queryWrapper.eq("address",address);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
        if(status.equals("即将进行")){
           queryWrapper.gt("join_start",formatter.format(date));
        }
        if(status.equals("招募中")){
          queryWrapper.le("join_start",formatter.format(date));
          queryWrapper.ge("join_end",formatter.format(date));
        }
        if(status.equals("已结束")){
            queryWrapper.lt("join_end",formatter.format(date));
        }
        Page pageInfo = new Page(page,size);
        IPage<Activity> iPage = activityMapper.selectPage(pageInfo, queryWrapper);
        Map<String,Object> pageMap = new HashMap<>(3);
        pageMap.put("total_record",iPage.getTotal());
        pageMap.put("total_page",iPage.getPages());
        pageMap.put("current_data",iPage.getRecords());
        return pageMap;
    }
}
