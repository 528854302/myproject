package com.a528854302.service;

import com.a528854302.entity.ResponseResult;
import com.a528854302.entity.StatusCode;
import com.a528854302.entity.TbUser;
import com.a528854302.mapper.TbUserMapper;
import com.a528854302.utils.JwtUtils;
import com.a528854302.vo.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbUserService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TbUserMapper mapper;


    public ResponseResult<List<TbUser>> selectAll() {
        return new ResponseResult<>( StatusCode.OK
                ,"success",mapper.selectList(null));
    }

    public ResponseResult selectById(String id) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(mapper.selectById(id),userInfo);
        return new ResponseResult(StatusCode.OK,
                "success",userInfo);
    }

    public ResponseResult update(TbUser tbUser) {
        return mapper.updateById(tbUser)>0?
                new ResponseResult():
                new ResponseResult(StatusCode.ERROR,"failed",null);
    }

    public ResponseResult deleteById(String id) {
        return mapper.deleteById(id)>01?
                new ResponseResult():
                new ResponseResult(StatusCode.ERROR,"failed",null);
    }

    public ResponseResult insert(TbUser tbUser) {
        if (this.selectOneByUsername(tbUser.getUsername())!=null){
            return new ResponseResult(StatusCode.ERROR,"用户名已存在",null);
        }
        return mapper.insert(tbUser)>0? new ResponseResult():
                new ResponseResult(StatusCode.ERROR,"failed",null);
    }

    public TbUser selectOneByUsername(String username){
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return mapper.selectOne(queryWrapper);
    }


    public ResponseResult getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        String id = JwtUtils.getUserIdFromToken(token);
        TbUser tbUser = mapper.selectById(id);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(tbUser,userInfo);
        return new ResponseResult(userInfo);
    }

    public ResponseResult selectMyInfoById(String id) {
        TbUser tbUser = mapper.selectById(id);
        tbUser.setPassword(null);
        return new ResponseResult(tbUser);
    }
}
