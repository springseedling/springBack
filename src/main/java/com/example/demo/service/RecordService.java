package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.mapper.RecordMapper;
import com.example.demo.pojo.Record;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RecordService {
    @Resource
    RecordMapper recordMapper;
    public int enterAct(int user_id, int item_id, int org_id){
        //先看看有没有重复报名
        QueryWrapper queryWrapper = new QueryWrapper<Record>();
        queryWrapper.eq("user_id",user_id);
        queryWrapper.eq("item_id",item_id);
        if(recordMapper.selectOne(queryWrapper)==null){
            Record record = new Record();
            record.setUser_id(user_id);
            record.setItem_id(item_id);
            record.setOrg_id(org_id);
            record.setSign_time(new Date());
            return recordMapper.insert(record);
        }else{
            return -1;
        }
    }

    public List<Record> getAllRecord(){
        return recordMapper.getAllRecord();
    }

    public int checkRecord(int record_id, int check){
        QueryWrapper queryWrapper = new QueryWrapper<Record>();
        queryWrapper.eq("record_id",record_id);
        queryWrapper.eq("record_check",0);
        if(recordMapper.selectOne(queryWrapper)!=null){
            UpdateWrapper updateWrapper = new UpdateWrapper<Record>();
            updateWrapper.eq("record_id",record_id);
            updateWrapper.set("record_check",check);
            return recordMapper.update(null,updateWrapper);
        }else{
            return 0;
        }
    }
    //待审核的报名记录
    public List<Record> getChecking() {
        return recordMapper.getChecking();
    }
}
