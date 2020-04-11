package com.a528854302.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@TableName("tb_user")
public class TbUser {
    @Id
    @TableId(type = IdType.ID_WORKER)
    private String id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;
}
