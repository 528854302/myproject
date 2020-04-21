package com.a528854302;

import com.a528854302.entity.Article;
import com.a528854302.entity.Comment;
import com.a528854302.repository.CommentRepository;
import com.a528854302.service.CommentService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCommentService {

    @Autowired
    CommentService service;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    CommentRepository repository;

    @org.junit.Test
    public void test(){
//        Criteria criteria = Criteria.where("articleid").is("");
//        List<Comment> comments = mongoTemplate.find(new Query(criteria), Comment.class);
        List<Comment> list = repository.findByArticleid("1248209768389123424");
        System.out.println(list);

    }
}
