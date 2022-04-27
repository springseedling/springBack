package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.mapper.RecordMapper;
import com.example.demo.pojo.Activity;
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

    public List<Record> getAllRecord(int org_id, int item_id, int check_status, int record_order){
        if(record_order==0){
            return recordMapper.getAllRecordASC(org_id,item_id,check_status,record_order);
        }else{
            return recordMapper.getAllRecordDESC(org_id,item_id,check_status,record_order);
        }
    }

    public int checkRecord(int record_id, int check){
//        QueryWrapper queryWrapper = new QueryWrapper<Record>();
//        queryWrapper.eq("record_id",record_id);
//        queryWrapper.eq("record_check",0);
//        if(recordMapper.selectOne(queryWrapper)!=null){
            UpdateWrapper updateWrapper = new UpdateWrapper<Record>();
            updateWrapper.eq("record_id",record_id);
            updateWrapper.set("record_check",check);
            updateWrapper.set("sign_time",new Date());
            if(check==1){
                return recordMapper.updateNum(record_id)+recordMapper.update(null,updateWrapper)-1;
            }
            if(check==-1){
                QueryWrapper queryWrapper = new QueryWrapper<Record>();
                queryWrapper.eq("record_id",record_id);
                queryWrapper.eq("record_check",1);
                if(recordMapper.selectOne(queryWrapper)!=null){
                    return recordMapper.minusNum(record_id)+recordMapper.update(null,updateWrapper)-1;
                }else{
                    return recordMapper.update(null,updateWrapper);
                }
            }
            else{
                return 0;
            }
        }
//        else{
//            return 0;
//        }
//    }
    //待审核的报名记录
    public List<Record> getChecking(int org_id) {
        return recordMapper.getChecking(org_id);
    }

    public List<Activity> getMyAct(int user_id, int record_check){
        return recordMapper.getMyAct(user_id,record_check);
    }
}
