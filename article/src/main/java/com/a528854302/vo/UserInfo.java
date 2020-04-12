package com.a528854302.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * @ClassName UserInfo
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/12
 **/
@Data
public class UserInfo {

    private String id;
    private String username;
    private String nickname;
    private String avatar;
    private Date createtime;

}
