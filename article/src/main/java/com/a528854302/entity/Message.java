package com.a528854302.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "message")
@NoArgsConstructor
public class Message implements Serializable {
    @Id
    private String id;
    /*** 消息状态，0-未读，1-已读 */
    @Indexed
    private Integer status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Field("send_date")
    @Indexed
    private Date sendDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Field("read_date")
    private Date readDate;
    @Indexed
    private TbUser from;
    @Indexed
    private TbUser to;

    private String content;
}
