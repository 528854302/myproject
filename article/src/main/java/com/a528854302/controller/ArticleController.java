package com.a528854302.controller;


import com.a528854302.entity.Article;
import com.a528854302.entity.PageResult;
import com.a528854302.entity.ResponseResult;
import com.a528854302.entity.StatusCode;
import com.a528854302.service.ArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * @ClassName ArticleController
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/08
 **/
@RestController
@CrossOrigin
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

   @GetMapping("/select")
    public ResponseResult select() {
        return articleService.selectAll();
    }

    @GetMapping(value = "/select/{id}")
    public ResponseResult selectById(@PathVariable String id) {
        return articleService.selectById(id);
    }

    @GetMapping(value = "/selectByUid/{uid}")
    public ResponseResult selectByUid(@PathVariable String uid) {
        return articleService.selectByUid(uid);
    }

    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody Article article) {
        return articleService.insert(article);
    }
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Article article) {

       return articleService.update(article);
    }

    @GetMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable String id) {
       return articleService.deleteById(id);
    }

    @GetMapping(value="/page/{page}/{size}/{channelid}")
    public ResponseResult selectByPage( @PathVariable int page, @PathVariable int size,@PathVariable String channelid) {
//        IPage iPage = articleService.selectByPage(map, page, size);
//        return new ResponseResult(StatusCode.OK, "查询成功", new PageResult(iPage.getTotal(), iPage.getRecords()));
        return articleService.selectVolistByPage(new Page<>(page,size),channelid);
    }




    @GetMapping("/collect/{userid}/{articleid}")
    public ResponseResult collect(@PathVariable String userid,
                                  @PathVariable String articleid){
       return articleService.collect(userid,articleid);
    }


    @GetMapping("/getCollected/{userid}")
    public ResponseResult getCollected(@PathVariable String userid){
        return articleService.selectCollectedByUserid(userid);
    }

}

