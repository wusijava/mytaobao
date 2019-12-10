package com.taobao.taobao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Description   :  测试哈是map容量初始化
 * @ Author        :  wusi
 * @ CreateDate    :  2019/12/9$ 17:42$
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestHashMap {
    int aHundredMillion = 10000000;
    @Test
    public  void getTime1(){

        //初始化一个hashmap不指定初始容量
        Map<Integer, Integer> map = new HashMap<>();
        long s1 = System.currentTimeMillis();
        for (int i = 0; i < aHundredMillion; i++) {
            map.put(i, i);
        }
        long s2 = System.currentTimeMillis();
        System.out.println("未初始化容量，耗时 ： " + (s2 - s1)+"毫秒");
    }
    //给定初始化大小的map  一半
    @Test
    public void getTime2(){
        Map<Integer, Integer> map1 = new HashMap<>(aHundredMillion / 2);

        long s5 = System.currentTimeMillis();
        for (int i = 0; i < aHundredMillion; i++) {
            map1.put(i, i);
        }
        long s6 = System.currentTimeMillis();
        System.out.println("初始化容量5000000，耗时 ： " + (s6 - s5));
    }

    //给定初始化大小的map  全量
    @Test
    public void getTime3(){
        Map<Integer, Integer> map1 = new HashMap<>(aHundredMillion );

        long s5 = System.currentTimeMillis();
        for (int i = 0; i < aHundredMillion; i++) {
            map1.put(i, i);
        }
        long s6 = System.currentTimeMillis();
        System.out.println("初始化容量10000000，耗时 ： " + (s6 - s5));
    }
}
