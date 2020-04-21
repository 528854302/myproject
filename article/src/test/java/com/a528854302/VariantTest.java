package com.a528854302;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @ClassName VariantTest
 * @Description: TODO
 * @Author 528854302@qq.com
 * @Date 2020/4/18
 **/
public class VariantTest{
    public static int staticVar = 0;
    public int instanceVar = 0;
    public VariantTest(){
        staticVar++;
        instanceVar++;
        System.out.println("staticVar=" + staticVar + ",instanceVar=" + instanceVar);
    }

    public static void main(String[] args) {
        VariantTest variantTest = new VariantTest();
        VariantTest variantTest1 = new VariantTest();
        VariantTest variantTest2 = new VariantTest();
    }
}