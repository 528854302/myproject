package com.a528854302.config;

import com.a528854302.interceptor.LoginIntorceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName InterceptorConfig
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/12
 **/
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    LoginIntorceptor loginIntorceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntorceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/user/login","/api/article/discover/**"
                        ,"/api/article/select/**","/api/user/insert",
                "/api/article/page/**","/api/user/selectAuthor/**");
    }
}
