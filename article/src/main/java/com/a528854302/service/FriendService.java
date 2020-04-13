package com.a528854302.service;

import com.a528854302.entity.Friend;
import com.a528854302.entity.ResponseResult;
import com.a528854302.mapper.FriendMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName FriendService
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/13
 **/
@Service
public class FriendService {
    @Autowired
    FriendMapper friendMapper;

    public ResponseResult insert(Friend friend){
        if (StringUtils.isEmpty(friend.getUserid())||StringUtils.isEmpty(friend.getFriendid())){
            friend.setState(0);
            return new ResponseResult("参数不合法");
        }
        if (friend.getUserid().equals(friend.getFriendid())){
            return new ResponseResult("不可以添加自己");
        }
        return new ResponseResult(friendMapper.insert(friend));
    }


    public ResponseResult updateById(Friend friend){
        return new ResponseResult(friendMapper.updateById(friend));
    }

}
