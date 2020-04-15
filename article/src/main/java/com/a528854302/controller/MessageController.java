package com.a528854302.controller;

import com.a528854302.entity.Message;
import com.a528854302.entity.ResponseResult;
import com.a528854302.entity.StatusCode;
import com.a528854302.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping("/{fromId}/{toId}/{page}/{rows}")
    public ResponseResult getMessageList(@PathVariable("fromId") String fromId
            , @PathVariable("toId") String toId
            ,@PathVariable("page") Integer page
            ,@PathVariable("rows") Integer rows){
        List<Message> list = messageService.readMessageList(fromId, toId, page, rows);
        return new ResponseResult(list);
    }


}
