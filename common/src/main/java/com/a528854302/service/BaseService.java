package com.a528854302.service;

import java.util.List;

public interface BaseService<T> {

    List<T> selectAll();

    T selectById(String id);

    boolean update(T t);

    boolean deleteById(String id);

    boolean insert(T t );
}
