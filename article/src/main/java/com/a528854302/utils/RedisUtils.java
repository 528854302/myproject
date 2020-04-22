package com.a528854302.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName RedisUtils
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/21
 **/
public class RedisUtils {
    /**
     * 根据request生成redisKey
     * @param request
     * @return
     */
    public static String generateRedisKey(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        StringBuilder redisKey = new StringBuilder(requestURI);
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            if (parameterMap.isEmpty()){
                ServletInputStream inputStream = request.getInputStream();
                redisKey.append(IOUtils.toString(inputStream,"UTF-8"));
            }else {
                ObjectMapper objectMapper=new ObjectMapper();
                redisKey.append(objectMapper.writeValueAsBytes(parameterMap));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return redisKey.toString();
    }
}
