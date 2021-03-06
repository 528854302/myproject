package com.a528854302.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@TableName("tb_channel")
public class Channel implements Serializable {

    @Id
    private Long id;

    private String name; //频道名称

    private String state; //状态 1显示，0不显示
}
