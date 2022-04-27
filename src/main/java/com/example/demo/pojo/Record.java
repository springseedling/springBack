package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("record")
public class Record {
    @TableId(value="record_id", type = IdType.AUTO)
    private Integer record_id;
    private int user_id;
    private int item_id;
    private int org_id;
    private int record_check;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date sign_time;
    @TableField(exist = false)
    private Activity activity;
    @TableField(exist = false)
    private Stu stu;
}

