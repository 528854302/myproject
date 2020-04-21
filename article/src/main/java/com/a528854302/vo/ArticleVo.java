package com.a528854302.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName ArticleVo
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/13
 **/
@Data
@NoArgsConstructor
public class ArticleVo {
    private String id;//ID
    private String columnid;    //专栏ID
    private String userid;      //用户ID
    private String title;       //标题
    private String content;     //文章正文
    private String image;       //文章封面
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;    //发表日期
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatetime;    //修改日期
    private String ispublic;    //是否公开
    private String istop;       //是否置顶
    private Integer visits;     //浏览量
    private Integer thumbup;    //点赞数
    private Integer comment;    //评论数
    private String state;       //审核状态
    private String channelid;   //所属频道
    private String url;         //URL
    private String type;        //类型
    private String nickname;
    private String avatar;
}
