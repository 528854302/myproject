package com.a528854302.service;

import com.a528854302.entity.Article;
import com.a528854302.entity.ResponseResult;
import com.a528854302.entity.StatusCode;
import com.a528854302.mapper.ArticleMapper;
import com.a528854302.vo.ArticleVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author a528854302
 * @since 2020-04-08
 */
@Service
@SuppressWarnings("all")
public class ArticleService implements BaseService<Article>{
    @Autowired
    private ArticleMapper articleMapper;

    public ResponseResult selectAll() {
        return new ResponseResult<>(articleMapper.selectVoList());
    }



    public ResponseResult selectById(String id) {
        return new ResponseResult<ArticleVo>(articleMapper.selectVoById(id));
    }

    public ResponseResult update(Article article) {
        return articleMapper.updateById(article)>0?
                new ResponseResult():
                new ResponseResult("failed");
    }

    public ResponseResult deleteById(String id) {
        return articleMapper.deleteById(id)>0?
                new ResponseResult():
                new ResponseResult("failed");
    }

    public ResponseResult insert(Article article){
        //article.setId(String.valueOf(IdWorker.getId()));
        return articleMapper.insert(article)>0?new ResponseResult():
        new ResponseResult("failed");
    }




    public List<Article> selectListByMap(Map map){
        return  articleMapper.selectByMap(map);
    }

    public IPage selectByPage(Map map, int page, int size) {
        if (page<0 || size==0){
            page=1;
            size=10;
        }
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        if (map==null){
            map=new HashMap();
        }
        for (Object filed : map.keySet()) {
            //动态sql，例如<if test="null != field"> and field='xxx' </if>
            wrapper.eq(null!=map.get(filed), (String) filed,map.get(filed));
        }
        IPage iPage = articleMapper.selectPage(new Page<>(page, size), wrapper);
        return iPage;
    }
}
