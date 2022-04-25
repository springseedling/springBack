package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("stu_info")
public class Stu {
    @TableId(value = "uid")
    private Integer uid;
    private String stu_name;
    private String stu_school;
    private String stu_subject;
    private String stu_phone;
}
