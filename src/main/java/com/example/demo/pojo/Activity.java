package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@TableName("item_info")
@ApiModel
public class Activity {
    @TableId(value = "item_id", type = IdType.AUTO)
    private Integer item_id;

    private String org_name;
    private int org_id;
    private String item_name;
    private String address;
    private String course;
    private String grade;
    private int need_num;
    private int present_num;

    private String release_time;

    private String img;
    private String org_profile;
    private String act_profile;
    private String join_time;
    private String act_time;
    private String join_start;
    private String join_end;
}
