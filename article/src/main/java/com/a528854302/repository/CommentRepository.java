package com.a528854302.repository;

import com.a528854302.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends MongoRepository<Comment,String> {
    List<Comment> findByArticleid(String articleid);
}
