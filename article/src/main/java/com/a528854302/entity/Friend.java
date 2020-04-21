package com.a528854302.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Friend
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/13
 **/
@Data
@TableName("tb_friend")
public class Friend implements Serializable {
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String userid;
    private String friendid;
    private Integer state;//0代表发出好友申请，暂未读，1代表已读暂未通过，2代表通过好友申请
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    private String content;
}
