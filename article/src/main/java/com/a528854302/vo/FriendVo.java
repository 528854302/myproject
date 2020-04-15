package com.a528854302.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName FriendVo
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/14
 **/
@Data
public class FriendVo implements Serializable {
    private String friendid;
    private String nickname;
    private String avatar;
    private Integer unreadmsg;//未读的消息数
}
