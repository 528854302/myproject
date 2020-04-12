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
    public ResponseResult selectById(@PathVariable String id) {
        return commentService.selectById(id);
    }

    @GetMapping("/select")
    public ResponseResult select() {
        return commentService.selectAll();
    }

    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody Comment comment) {
        return commentService.insert(comment);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody Comment comment) {
        return commentService.update(comment);
    }

    @GetMapping("/delete/{id}")
    public ResponseResult deleteById(@PathVariable String id) {
        return commentService.deleteById(id);
    }

    @GetMapping("/selectByArticleId/{articleId}")
    public ResponseResult selectByarticleId(@PathVariable String articleId) {
        return commentService.selectByArticleId(articleId);
    }
}
