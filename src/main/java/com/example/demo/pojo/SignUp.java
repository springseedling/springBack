package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@TableName("signUp")
@Data
@ApiModel
public class SignUp {
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;
    private String username;
    private String password;
    private String role;
    private String status;
}
