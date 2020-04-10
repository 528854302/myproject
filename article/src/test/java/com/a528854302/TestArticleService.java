package com.a528854302;

import com.a528854302.entity.Article;
import com.a528854302.mapper.ArticleMapper;
import com.a528854302.service.ArticleService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestArticleService {
    @Autowired
    ArticleService articleService;
    @org.junit.Test
    public void test(){
        Article article = new Article();
        article.setColumnid("safsdfsd");
        articleService.insert(article);

    }
}
