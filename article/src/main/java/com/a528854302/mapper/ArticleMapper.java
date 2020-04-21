package com.a528854302.mapper;

import com.a528854302.entity.Article;
import com.a528854302.vo.ArticleVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author a528854302
 * @since 2020-04-08
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("SELECT a.*,u.`nickname`,u.`avatar` FROM `tb_article` a,`tb_user` u WHERE a.`userid`=u.`id`")
    List<ArticleVo> selectVoList();

    @Select("SELECT a.*,u.`nickname`,u.`avatar` FROM `tb_article` a,`tb_user` u WHERE a.`userid`=u.`id` AND a.`id`=#{id}")
    ArticleVo selectVoById(@Param("id") String id);

    @Select("SELECT a.*,u.`nickname`,u.`avatar` FROM `tb_article` a,`tb_user` u WHERE a.`userid`=u.`id` and a.channelid=#{channelid}")
    IPage<ArticleVo> selectVoListPage(Page<ArticleVo> page,@Param("channelid") String channelid);

    @Insert("INSERT INTO `tb_article_collect` VALUES (#{userid},#{articleid})")
    void collect(@Param("userid") String userid,@Param("articleid") String articleid);

    @Select("SELECT a.*,u.`nickname`,u.`avatar` FROM `tb_article` a INNER JOIN \n" +
            "`tb_user` u ON a.`userid`=u.`id` \n" +
            "INNER JOIN `tb_article_collect` ac ON a.`id`=ac.`articleid` \n" +
            "WHERE ac.`userid`=#{userid}")
    List<ArticleVo> selcetCollectedByUserid(String userid);
}
