package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@TableName("signIn")
@Data
@ApiModel
public class SignIn {
    @TableId(value = "uid")
    private Integer uid;
    private String username;
    private String password;
    private String role;
}
