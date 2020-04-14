package com.a528854302.entity;

import java.util.HashMap;
import java.util.Map;

public class USER_DATA {
    public static Map<Long,User> userMap=new HashMap<>();

    public static User getById(Long uid){

        return userMap.get(uid);
    }

    static {
        userMap.put(1L,new User(1L,"zhangsan"));
        userMap.put(2L,new User(2L,"lisi"));
        userMap.put(3L,new User(3L,"wangwu"));
    }
}
