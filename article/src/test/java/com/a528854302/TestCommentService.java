package com.a528854302;

import com.a528854302.entity.Article;
import com.a528854302.entity.Comment;
import com.a528854302.service.CommentService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCommentService {

    @Autowired
    CommentService service;

    @org.junit.Test
    public void test(){
//        Comment comment = new Comment();
//        comment.set_id("asdsa");
//        comment.setContent("sadasdasdas");
//        comment.setArticleid("111");
//        service.insert(comment);
//        List<Comment> comments = service.selectByArticleId("111");
//        System.out.println(comments);
        service.thumbup("1247889189308043265");

    }
}
