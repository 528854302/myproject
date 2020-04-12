package com.a528854302.service;

import com.a528854302.entity.ResponseResult;

import java.util.List;

public interface BaseService<T> {

    ResponseResult<List<T>> selectAll();

    ResponseResult<T> selectById(String id);

    ResponseResult update(T t);

    ResponseResult deleteById(String id);

    ResponseResult insert(T t );

}
