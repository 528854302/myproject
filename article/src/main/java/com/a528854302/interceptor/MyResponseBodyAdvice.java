package com.a528854302.interceptor;

import com.a528854302.utils.RedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyResponseBodyAdvice
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/21
 **/
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        if (methodParameter.hasMethodAnnotation(GetMapping.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o==null){
            return o;
        }
        try {
            String redisKey = RedisUtils.generateRedisKey(((ServletServerHttpRequest) serverHttpRequest).getServletRequest());
            String redisData;
            if(o instanceof String){
                redisData = (String)o;
            }else{
                redisData = objectMapper.writeValueAsString(o);
            }
            redisTemplate.opsForValue().set(redisKey,redisData, 5L, TimeUnit.MINUTES);

        }catch (Exception e){
            e.printStackTrace();;
        }
        return o;
    }
}
