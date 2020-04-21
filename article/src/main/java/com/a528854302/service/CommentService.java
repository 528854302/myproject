package com.a528854302.service;

import com.a528854302.entity.Comment;
import com.a528854302.entity.ResponseResult;
import com.a528854302.entity.TbUser;
import com.a528854302.mapper.TbUserMapper;
import com.a528854302.repository.CommentRepository;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TbUserMapper tbUserMapper;

    @Override
    public ResponseResult<List<Comment>> selectAll() {
        List<Comment> all = repository.findAll();
        for (Comment comment : all) {
            TbUser tbUser = tbUserMapper.selectById(comment.getUserid());
            comment.setNickname(tbUser.getNickname());
            comment.setAvatar(tbUser.getAvatar());
        }
        return new ResponseResult<>(all);
    }

    @Override
    public ResponseResult<Comment> selectById(String id) {
        return new ResponseResult<>(repository.findById(id).get());
    }

    @Override
    public ResponseResult update(Comment comment) {
        return repository.save(comment)!=null?
                new ResponseResult():
                new ResponseResult("failed");
    }

    @Override
    public ResponseResult deleteById(String id) {
        repository.deleteById(id);
        return new ResponseResult();
    }

    @Override
    public ResponseResult insert(Comment comment) {
        if (StringUtils.isEmpty(comment.getUserid())){
            return new ResponseResult("请先登录");
        }
        comment.setId(IdWorker.getIdStr());
        comment.setCreatetime(new Date());
        return repository.save(comment)!=null?
                new ResponseResult():
                new ResponseResult("failed");
    }

    public ResponseResult<List<Comment>> selectByArticleId(String articleId) {


//        Criteria criteria = Criteria.where("articleid").is(articleId);
//        List<Comment> comments = mongoTemplate.find(new Query(criteria), Comment.class);
        List<Comment> comments = repository.findByArticleid(articleId);
        for (Comment comment : comments) {
            TbUser tbUser = tbUserMapper.selectById(comment.getUserid());
            comment.setAvatar(tbUser.getAvatar());
            comment.setNickname(tbUser.getNickname());
        }
        return new ResponseResult<>(comments);
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
