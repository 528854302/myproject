package com.a528854302.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName QueryPageBean
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/15
 **/
@Data
@AllArgsConstructor
public class QueryPageBean {
    private Integer page;//当前页
    private Integer rows;//每页条数
    private Integer queryString;//查询条件
}
