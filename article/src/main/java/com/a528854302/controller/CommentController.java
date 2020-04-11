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

    @GetMapping(value = "/select/{id}")
    public ResponseResult findById(@PathVariable String id) {
        Comment comment = commentService.selectById(id);
        return new ResponseResult(true, StatusCode.OK, "查询成功", comment);
    }

    @GetMapping("/select")
    public ResponseResult select() {
        List<Comment> list = commentService.selectAll();
        return new ResponseResult(true, StatusCode.OK, "查询成功", list);
    }

    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody Comment comment) {
        commentService.insert(comment);
        return new ResponseResult(true, StatusCode.OK, "新增成功");
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody Comment comment) {
        commentService.update(comment);
        return new ResponseResult(true, StatusCode.OK, "修改成功");
    }

    @GetMapping("/delete/{id}")
    public ResponseResult deleteById(@PathVariable String id) {
        return commentService.deleteById(id)?
                new ResponseResult(true, StatusCode.OK, "删除成功"):
                new ResponseResult(false,StatusCode.ERROR,"删除失败");
    }

    @GetMapping("/selectByArticleId/{articleId}")
    public ResponseResult selectByarticleId(@PathVariable String articleId) {
        List<Comment> list = commentService.selectByArticleId(articleId);
        return new ResponseResult(true, StatusCode.OK, "查询成功", list);
    }
}
