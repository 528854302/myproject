package com.a528854302;

import com.a528854302.entity.Message;
import com.a528854302.entity.User;
import com.a528854302.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMessageTest {
    @Autowired
    MessageService  service;

    @Test
    public void testInsert(){
        Message message = new Message();
        message.setContent("hellow");
        message.setFrom(new User(10L,"zhangsan"));
        message.setTo(new User(11L,"lisi"));
        service.insert(message);
        Message message1 = new Message();
        message1.setContent("hellow too");
        message1.setTo(new User(10L,"zhangsan"));
        message1.setFrom(new User(11L,"lisi"));
        service.insert(message1);
    }
    @Test
    public void testFind(){
        List<Message> list = service.findListByFromAndTo(11L, 10L, 1, 2);
        System.out.println(list);
    }
}
