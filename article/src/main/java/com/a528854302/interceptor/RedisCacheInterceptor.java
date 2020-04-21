package com.a528854302.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName RedisCacheInterceptor
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/21
 **/
@Component
public class RedisCacheInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StringUtils.endsWithIgnoreCase(request.getMethod(),"get")){
            String redisData = redisTemplate.opsForValue().get(generateRedisKey(request));
            if (StringUtils.isEmpty(redisData)){
                return true;
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods",
                    "GET,POST,PUT,DELETE,OPTIONS");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Token");
            response.setHeader("Access-Control-Allow-Credentials", "true");

            response.getWriter().write(redisData);
            return false;
        }
        return true;
    }

    public static String generateRedisKey(HttpServletRequest request) throws IOException {
        String requestURI = request.getRequestURI();
        StringBuilder redisKey = new StringBuilder(requestURI);
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.isEmpty()){
            redisKey.append(IOUtils.toString(request.getInputStream(),"UTF-8"));
        }else {
            ObjectMapper objectMapper=new ObjectMapper();
            redisKey.append(objectMapper.writeValueAsBytes(parameterMap));
        }
        return redisKey.toString();
    }
}
