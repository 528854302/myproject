package com.a528854302.controller;

import com.a528854302.entity.Comment;
import com.a528854302.entity.ResponseResult;
import com.a528854302.entity.StatusCode;
import com.a528854302.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    //根据id查询评论
    @GetMapping(value = "/select/{id}")
    public ResponseResult findById(@PathVariable String id) {
        Comment comment = commentService.selectById(id);
        return new ResponseResult(true, StatusCode.OK, "查询成功", comment);
    }

    //查询所有
    @GetMapping("/select")
    public ResponseResult findAll() {
        List<Comment> list = commentService.selectAll();
        return new ResponseResult(true, StatusCode.OK, "查询成功", list);
    }

    //新增
    @PostMapping("/insert")
    public ResponseResult save(@RequestBody Comment comment) {
        commentService.insert(comment);
        return new ResponseResult(true, StatusCode.OK, "新增成功");
    }

    //修改
    @PostMapping("/update")
    public ResponseResult update(@PathVariable String id,
                         @RequestBody Comment comment) {
        comment.set_id(id);
        commentService.update(comment);
        return new ResponseResult(true, StatusCode.OK, "修改成功");
    }

    //删除
    @GetMapping("/delete/{id}")
    public ResponseResult deleteById(@PathVariable String id) {
        commentService.deleteById(id);
        return new ResponseResult(true, StatusCode.OK, "删除成功");
    }

    //根据文章id查询评论列表
    @GetMapping("/selectByArticleId/{articleId}")
    public ResponseResult findByarticleId(@PathVariable String articleId) {
        List<Comment> list = commentService.selectByArticleId(articleId);
        return new ResponseResult(true, StatusCode.OK, "查询成功", list);
    }
}
