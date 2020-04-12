package com.a528854302.test;

import com.a528854302.utils.JwtUtils;
import org.junit.Test;

/**
 * @ClassName TestJwtUtils
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/12
 **/
public class TestJwtUtils {

    @Test
    public void testgenerateToken(){
        String s = JwtUtils.generateToken("888885555", "zhangsan");
        System.out.println(s);
    }

    @Test
    public void testCheckToken(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1Mjg4NTQzMDJAcXEuY29tIiwiaWQiOiJhc2RzYSIsIm5hbWUiOiJ6aGFuZ3NhbiIsImlhdCI6MTU4NjY2OTYzNiwiZXhwIjoxNTg2NzA1NjM2fQ.s8Zdd1Jw21pIPL8-tE-CFn_VHpz28Trtx8DKXLcgP64";

        System.out.println(JwtUtils.checkToken(token));
    }

    @Test
    public void testGetUserIdFromToken(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1Mjg4NTQzMDJAcXEuY29tIiwiaWQiOiJhc2RzYSIsIm5hbWUiOiJ6aGFuZ3NhbiIsImlhdCI6MTU4NjY2OTYzNiwiZXhwIjoxNTg2NzA1NjM2fQ.s8Zdd1Jw21pIPL8-tE-CFn_VHpz28Trtx8DKXLcgP64";
        String token1 = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1Mjg4NTQzMDJAcXEuY29tIiwiaWQiOiI4ODg4ODU1NTUiLCJuYW1lIjoiemhhbmdzYW4iLCJpYXQiOjE1ODY2Njk4MTMsImV4cCI6MTU4NjcwNTgxM30.XAU7hcqtWRgQLNE3czVCHG4NxXZw9kSROmzonvjV34g";

        System.out.println(JwtUtils.getUserIdFromToken(token1));
    }
}
