package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 */
@Data
@TableName("user")
@ApiModel
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String nickname;

    private String username;

    private String password;

    private String gender;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 背景图片
     */
    private String background;

    @TableField("phone_number")
    private String phoneNumber;
    @TableField("open_id")
    private String openId;
    @TableField("wx_union_id")
    private String wxUnionId;


}