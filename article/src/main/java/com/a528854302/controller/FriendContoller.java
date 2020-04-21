package com.a528854302.controller;

import com.a528854302.entity.Friend;
import com.a528854302.vo.FriendVo;
import com.a528854302.entity.ResponseResult;
import com.a528854302.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName FriedContoller
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/13
 **/
@RestController
@CrossOrigin
@RequestMapping("/api/friend")
public class FriendContoller {

    @Autowired
    FriendService friendService;

    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody Friend friend) {
        return friendService.insert(friend);
    }

    @GetMapping("/selectByUserId/{userid}")
    public ResponseResult<List<FriendVo>> selectByUserId(@PathVariable("userid") String userid) {
        return friendService.selectFriendVoByUserId(userid);
    }



}
