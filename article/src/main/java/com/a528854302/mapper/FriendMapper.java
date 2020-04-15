package com.a528854302.mapper;

import com.a528854302.entity.Friend;
import com.a528854302.vo.FriendVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FriendMapper extends BaseMapper<Friend> {

    @Select("SELECT f.`friendid`,u.`nickname`,u.`avatar` FROM `tb_friend` f \n" +
            "INNER JOIN \n" +
            "`tb_user` u ON f.`friendid`=u.`id` \n" +
            "WHERE  f.`userid`=#{userid} ")
    List<FriendVo> selectFriendVoByUserId(@Param("userid") String userid);
}
