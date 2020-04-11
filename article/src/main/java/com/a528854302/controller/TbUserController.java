package com.a528854302.controller;

import com.a528854302.entity.ResponseResult;
import com.a528854302.entity.StatusCode;
import com.a528854302.entity.TbUser;
import com.a528854302.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class TbUserController {

    @Autowired
    TbUserService tbUserService;

    @GetMapping(value = "/select/{id}")
    public ResponseResult selectById(@PathVariable String id) {
        TbUser tbUser = tbUserService.selectById(id);
        return new ResponseResult(true, StatusCode.OK, "查询成功", tbUser);
    }

    @GetMapping("/select")
    public ResponseResult select() {
        List<TbUser> list = tbUserService.selectAll();
        return new ResponseResult(true, StatusCode.OK, "查询成功", list);
    }

    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody TbUser user) {
        tbUserService.insert(user);
        return new ResponseResult(true, StatusCode.OK, "新增成功");
    }


    @PostMapping("/update")
    public ResponseResult update(@RequestBody TbUser user) {
        return tbUserService.update(user)?
                new ResponseResult(true, StatusCode.OK, "修改成功"):
                new ResponseResult(false,StatusCode.ERROR,"修改失败");
    }

    @GetMapping("/delete/{id}")
    public ResponseResult deleteById(@PathVariable String id) {
        return tbUserService.deleteById(id)?
                new ResponseResult(true, StatusCode.OK, "删除成功"):
                new ResponseResult(false,StatusCode.ERROR,"删除失败");
    }


}
