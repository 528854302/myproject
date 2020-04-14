package com.a528854302.entity;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class ResponseResult<T> implements Serializable {
    private Integer code;// 返回码
    private String message;//返回信息
    private T data;// 返回数据

    public ResponseResult( Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult() {
        this.code=StatusCode.OK;
        this.message="success";
    }

    public ResponseResult(T data) {
        this.code=StatusCode.OK;
        this.message="success";
        this.data=data;
    }

    public ResponseResult(String errorMessage) {
        this.code=StatusCode.ERROR;
        this.message=errorMessage;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}