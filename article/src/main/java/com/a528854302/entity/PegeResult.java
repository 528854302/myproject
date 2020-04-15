package com.a528854302.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName PegeResult
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/15
 **/
@Data
@AllArgsConstructor
public class PegeResult implements Serializable {

    private Integer total;//总记录数
    private List rows;//当前页结果

}
