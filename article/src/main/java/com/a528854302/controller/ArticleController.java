package com.a528854302.controller;


import com.a528854302.entity.Article;
import com.a528854302.entity.PageResult;
import com.a528854302.entity.ResponseResult;
import com.a528854302.entity.StatusCode;
import com.a528854302.service.ArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author a528854302
 * @since 2020-04-08
 */
@RestController
@CrossOrigin
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

   @GetMapping("/select")
    public ResponseResult select() {
        List list = articleService.selectAll();
        return new ResponseResult(true, StatusCode.OK, "查询成功", list);
    }

    @GetMapping(value = "/select/{id}")
    public ResponseResult selectById(@PathVariable String id) {
        Article Article = articleService.selectById(id);
        return new ResponseResult(true, StatusCode.OK, "查询成功", Article);
    }

    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody Article article) {
        articleService.insert(article);
        return new ResponseResult(true, StatusCode.OK, "添加成功");
    }
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Article article) {

       return articleService.update(article)?
               new ResponseResult(true, StatusCode.OK, "修改成功"):
               new ResponseResult(false,StatusCode.ERROR,"修改失败");
    }

    @GetMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable String id) {
       return articleService.deleteById(id)?
               new ResponseResult(true, StatusCode.OK, "删除成功"):
               new ResponseResult(false,StatusCode.ERROR,"删除失败");
    }

    @GetMapping(value="/page/{page}/{size}")
    public ResponseResult selectByPage(@RequestBody Map map, @PathVariable int page, @PathVariable int size) {
        IPage iPage = articleService.selectByPage(map, page, size);
        return new ResponseResult(true, StatusCode.OK, "查询成功", new PageResult(iPage.getTotal(), iPage.getRecords()));
    }
}

