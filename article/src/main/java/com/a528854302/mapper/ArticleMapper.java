package com.a528854302.mapper;

import com.a528854302.entity.Article;
import com.a528854302.vo.ArticleVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

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

}
