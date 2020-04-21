package com.a528854302;

import com.a528854302.entity.Article;
import com.a528854302.entity.Message;
import com.a528854302.entity.ResponseResult;
import com.a528854302.entity.TbUser;
import com.a528854302.service.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedisTemplate {
    @Autowired
    RedisTemplate redisTemplate;
    @org.junit.Test
    public void test() throws JsonProcessingException {
        Message message = new Message();
        message.setId("11111");
        message.setContent("你好嗄");
        message.setSendDate(new Date());
        TbUser tbUser = new TbUser();
        tbUser.setUsername("asdasdsa");
        tbUser.setPassword("aaaaa");
        message.setFrom(tbUser);
        ResponseResult<Object> responseResult = new ResponseResult<>(message);
        String s = new ObjectMapper().writeValueAsString(responseResult);
        redisTemplate.opsForValue().set("respons",responseResult,5L, TimeUnit.MINUTES);
        Object respons = redisTemplate.opsForValue().get("respons");
        System.out.println(respons);
    }

}
