package com.a528854302;

import com.a528854302.entity.Message;
import com.a528854302.entity.ResponseResult;
import com.a528854302.service.FriendService;
import com.a528854302.vo.FriendVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName TestFriendService
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/14
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFriendService {

    @Autowired
    FriendService service;

    @Autowired
    MongoTemplate mongoTemplate;
    @Test
    public void  test(){
        ResponseResult<List<FriendVo>> listResponseResult = service.selectFriendVoByUserId("1");
        System.out.println(listResponseResult);



    }

}
