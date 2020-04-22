package com.a528854302.service;

import com.a528854302.entity.Article;
import com.a528854302.entity.ResponseResult;
import com.a528854302.mapper.ArticleMapper;
import com.a528854302.vo.ArticleVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    /**
     * 首页文章的分页和联表条件查询文章Vo
     * @param page
     * @param channelid
     * @return
     */
    public ResponseResult selectVolistByPage(Page<ArticleVo> page,String channelid) {
        if (channelid==null){
            channelid="1";
        }
        return new ResponseResult(articleMapper.selectVoListPage(page,channelid));
    }


    /**
     * 根据id查询
     * @param id
     * @return
     */
    public ResponseResult selectById(String id) {
        return new ResponseResult<ArticleVo>(articleMapper.selectVoById(id));
    }

    /**
     * 更新
     * @param article
     * @return
     */
    public ResponseResult update(Article article) {
        return articleMapper.updateById(article)>0?
                new ResponseResult():
                new ResponseResult("failed");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    public ResponseResult deleteById(String id) {
        return articleMapper.deleteById(id)>0?
                new ResponseResult():
                new ResponseResult("failed");
    }

    /**
     * 插入
     * @param article
     * @return
     */
    public ResponseResult insert(Article article){
        if (StringUtils.isEmpty(article.getUserid())){
            return new ResponseResult("未登录");
        }
        //article.setId(String.valueOf(IdWorker.getId()));
        return articleMapper.insert(article)>0?new ResponseResult():
        new ResponseResult("failed");
    }


    /**
     * 根据map条件查询
     * @param map
     * @return
     */
    public List<Article> selectListByMap(Map map){
        return  articleMapper.selectByMap(map);
    }

    /*public IPage selectByPage(Map map, int page, int size) {
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
    }*/

    /**
     * 根据作者id查询
     * @param uid
     * @return
     */
    public ResponseResult selectByUid(String uid) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",uid);
        return new ResponseResult(articleMapper.selectList(queryWrapper));
    }

    /**
     * 文章收藏
     * @param userid
     * @param articleid
     * @return
     */
    public ResponseResult collect(String userid, String articleid) {
        try {
            articleMapper.collect(userid, articleid);
        }catch (Exception e){
            return new ResponseResult("你已收藏过该文章");
        }
        return new ResponseResult();
    }

    /**
     * 根据用户id查询用户收藏的文章
     * @param userid
     * @return
     */
    public ResponseResult selectCollectedByUserid(String userid) {
        return new ResponseResult(articleMapper.selcetCollectedByUserid(userid));
    }


    public ResponseResult selectByFriends(String uid) {
        return new ResponseResult(articleMapper. selectByFriends(uid));
    }

    /**
     * 随机查询num条
     * @param num
     * @return
     */
    public ResponseResult selectRamdom(int num){
        if (num<=0){
            num=10;
        }
        return new ResponseResult(articleMapper.selectRandom(num));
    }
}
