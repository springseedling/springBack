package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.Activity;
import com.example.demo.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    List<Record> getAllRecordASC(int org_id,int item_id,int check_status,int record_order);
    List<Record> getAllRecordDESC(int org_id,int item_id,int check_status,int record_order);
    List<Record> getChecking(int org_id);
    int updateNum(int record_id);
    int minusNum(int record_id);
    List<Activity> getMyAct(int user_id, int record_check);
}
