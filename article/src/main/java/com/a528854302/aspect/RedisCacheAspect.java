package com.a528854302.aspect;

import com.a528854302.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName CacheAspect
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/21
 **/
@Component
@Aspect
public class RedisCacheAspect {
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Pointcut("execution(* com.a528854302.controller.ArticleController.*(..))")
    public void pointcut(){}

    @Around("pointcut()")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String method = joinPoint.getSignature().getName().toLowerCase();
        if (request.getMethod().toLowerCase().contains("get") &&
        method.contains("select")){
            String redisKey = RedisUtils.generateRedisKey(request);
            String redisData = redisTemplate.opsForValue().get(redisKey);
            Object ret=null;
            if (redisData==null || "".equals(redisData)){
                ret = joinPoint.proceed(joinPoint.getArgs());
            }else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods",
                        "GET,POST,PUT,DELETE,OPTIONS");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Token");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.getWriter().write(redisData);
            }
            return ret;
        }else {
            return joinPoint.proceed(joinPoint.getArgs());
        }
    }

}
