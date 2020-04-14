package com.a528854302;

import com.a528854302.entity.Article;
import com.a528854302.entity.ResponseResult;
import com.a528854302.entity.TbUser;
import com.a528854302.service.ArticleService;
import com.a528854302.service.TbUserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestTbUserService {
    @Autowired
    TbUserService tbUserService;
    @org.junit.Test
    public void test(){
        TbUser tbUser = new TbUser();
        tbUser.setUsername("asdsadasdsa");
        tbUser.setAvatar("img1");
        ResponseResult insert = tbUserService.insert(tbUser);
        System.out.println(insert);
        Date now = new Date();

    }
}
