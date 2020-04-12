package com.a528854302.interceptor;

import com.a528854302.entity.ResponseResult;
import com.a528854302.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginIntorceptor
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/12
 **/
@Component
public class LoginIntorceptor implements HandlerInterceptor {

    @Autowired
    ObjectMapper objectMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        response.setContentType("text/html;charset=UTF-8");
        if (StringUtils.isEmpty(token)){
            ResponseResult<Object> responseResult
                    = new ResponseResult<>("未登录");
            response.getWriter().write(objectMapper.writeValueAsString(responseResult));
            return false;
        }
        if (!JwtUtils.checkToken(token)){
            ResponseResult<Object> responseResult =
                    new ResponseResult<>("登录验证失败，请重新登录");
            response.getWriter().write(objectMapper.writeValueAsString(responseResult));
            return false;
        }
        return true;
    }
}
