package com.a528854302.repository;

import com.a528854302.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment,String> {
    List<Comment> findByArticleid(String articleid);
}
