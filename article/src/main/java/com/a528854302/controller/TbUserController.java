package com.a528854302.controller;

import com.a528854302.entity.ResponseResult;
import com.a528854302.entity.StatusCode;
import com.a528854302.entity.TbUser;
import com.a528854302.service.TbUserService;
import com.a528854302.utils.JwtUtils;
import com.a528854302.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
/**
 * @ClassName ArticleController
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/09
 **/
@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class TbUserController {

    @Autowired
    TbUserService tbUserService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody TbUser user){
        TbUser tbUser = tbUserService.selectOneByUsername(user.getUsername());
        if (tbUser==null){
            return new ResponseResult(StatusCode.ERROR,"登陆失败，用户名或密码错误",null);
        }
        if (tbUser.getPassword().equals(user.getPassword())){
            String token = JwtUtils.generateToken(tbUser.getId(), tbUser.getUsername());
            return new ResponseResult(StatusCode.OK,"login success",token);
        }else {
            return new ResponseResult(StatusCode.ERROR,"登陆失败，用户名或密码错误",null);
        }
    }

    @GetMapping("/info")
    public ResponseResult userInfo(HttpServletRequest request){
        return tbUserService.getUserInfo(request);
    }

    @GetMapping(value = "/select/{id}")
    public ResponseResult selectById(@PathVariable String id) {
        return tbUserService.selectById(id);
    }

    @GetMapping("/select")
    public ResponseResult select() {
        return tbUserService.selectAll();
    }

    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody TbUser user) {
        return tbUserService.insert(user);
    }


    @PostMapping("/update")
    public ResponseResult update(@RequestBody TbUser user) {
        return tbUserService.update(user);
    }

    @GetMapping("/delete/{id}")
    public ResponseResult deleteById(@PathVariable String id) {
        return tbUserService.deleteById(id);
    }



}
