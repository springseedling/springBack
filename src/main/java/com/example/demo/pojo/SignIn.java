package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("sign_in")
@Data
public class SignIn {
    @TableId(value = "uid")
    private Integer uid;
    private String username;
    private String password;
    private String role;
}
