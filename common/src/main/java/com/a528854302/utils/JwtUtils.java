package com.a528854302.utils;

import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @ClassName JwtUtils
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/12
 **/
public class JwtUtils {
    //token过期时间
    private final static Integer EXPIRE = 1000*60*60*10;
    //密钥
    private final static String SECRET = "528854302@qq.com";

    public static String generateToken(String id,String username){

        String token = Jwts.builder()
                .setSubject(SECRET)    //设置签名
                .claim("id", id)        //设置传入的参数
                .claim("name", username)
                .setIssuedAt(new Date())    //发行时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)) //过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET)  //签名算法
                .compact(); //压缩
        return token;
    }

    public static boolean checkToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        }catch (Exception e){
            return false;
        }
        return true;
    }


    public static String getUserIdFromToken(String token){
            if (StringUtils.isEmpty(token)){
                return null;
            }
            Jws<Claims> claimsJws =
                    Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return (String) claimsJws.getBody().get("id");

    }



}
