package com.a528854302.service;

import com.a528854302.entity.Friend;
import com.a528854302.entity.Message;
import com.a528854302.entity.StatusCode;
import com.a528854302.repository.MessageRepository;
import com.a528854302.vo.FriendVo;
import com.a528854302.entity.ResponseResult;
import com.a528854302.mapper.FriendMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FriendService
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/13
 **/
@Service
public class FriendService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    FriendMapper friendMapper;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MessageRepository messageRepository;

    public ResponseResult insert(Friend friend){
        if (StringUtils.isEmpty(friend.getUserid())||StringUtils.isEmpty(friend.getFriendid())){
            friend.setState(0);
            return new ResponseResult("参数不合法");
        }
        if (friend.getUserid().equals(friend.getFriendid())){
            return new ResponseResult("不可以添加自己");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userid",friend.getUserid());
        queryWrapper.eq("friendid",friend.getFriendid());
        Friend friend1 = friendMapper.selectOne(queryWrapper);
        if (friend1==null){
            friendMapper.insert(friend);
            String userid = friend.getUserid();
            String friendid = friend.getFriendid();
            friend.setUserid(friendid);
            friend.setFriendid(userid);
            friendMapper.insert(friend);
            return new ResponseResult(StatusCode.OK,"添加好友成功",null);
        }else {
            return new ResponseResult("ta已是你的好友，请勿重复添加");
        }
    }


    public ResponseResult updateById(Friend friend){
        return new ResponseResult(friendMapper.updateById(friend));
    }

    public ResponseResult<List<FriendVo>> selectFriendVoByUserId(String userid) {
        List<FriendVo> friends = friendMapper.selectFriendVoByUserId(userid);


        for (FriendVo friend : friends) {
            //查询朋友发给我的，且未读的消息
            Criteria fromCriteria = Criteria.where("from.id").is(friend.getFriendid()).and("to.id").is(userid);
            Criteria unreadCriteria = Criteria.where("status").is(0);
            Criteria criteria = new Criteria().andOperator(fromCriteria,unreadCriteria);
            int count = (int) mongoTemplate.count(new Query(criteria),Message.class);
            friend.setUnreadmsg(count);
        }
        return new ResponseResult(friends);
    }
}
