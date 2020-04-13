package com.a528854302.controller;

import com.a528854302.entity.Article;
import com.a528854302.entity.Friend;
import com.a528854302.entity.ResponseResult;
import com.a528854302.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName FriedContoller
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/13
 **/
@RestController
@CrossOrigin
@RequestMapping("/api/friend")
public class FrinedContoller {

    @Autowired
    FriendService friendService;

    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody Friend friend) {
        return friendService.insert(friend);
    }



}
