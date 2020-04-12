package com.a528854302.handler;

import com.a528854302.entity.ResponseResult;
import com.a528854302.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public ResponseResult error(Exception e) {
        e.printStackTrace();
        return new ResponseResult(e.getMessage());
    }
}
