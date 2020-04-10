package com.a528854302.service;

import com.a528854302.entity.Comment;
import com.a528854302.repository.CommentRepository;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CommentService implements BaseService<Comment> {
    @Autowired
    private CommentRepository repository;
    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public List<Comment> selectAll() {
        return repository.findAll();
    }

    @Override
    public Comment selectById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public boolean update(Comment comment) {
        return repository.save(comment)!=null?true:false;
    }

    @Override
    public boolean deleteById(String id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean insert(Comment comment) {
        comment.set_id(IdWorker.getIdStr());
        comment.setCreatetime(new Date());
        return repository.save(comment)!=null?true:false;
    }

    public List<Comment> selectByArticleId(String articleId) {
        return repository.findByArticleid(articleId);
    }

    public void thumbup(String id) {
        ////查询评论
        //Comment comment = commentDao.findById(id).get();
        ////修改点赞数
        //comment.setThumbup(comment.getThumbup() + 1);
        //commentDao.save(comment);
         //以上操作需要操作两次数据库，性能较低，service方法优化如下
        //修改条件
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        //修改的数据
        Update update = new Update();
        //在原来的基础上加一
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "comment");
    }
}
