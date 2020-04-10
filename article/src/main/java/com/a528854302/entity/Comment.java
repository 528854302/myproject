package com.a528854302.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "comment")
public class Comment implements Serializable {
    @Id
    private String _id;
    @Indexed
    private String articleid;
    private String content;
    @Indexed
    private String userid;
    @Indexed
    private String parentid;
    @Indexed
    private Date createtime;
    private Integer thumbup;
}
