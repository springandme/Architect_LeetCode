package com.liushi.test;

/**
 * @ClassName TestDemo
 * @Description TODO
 * @Author liushi
 * @Date 2020/10/1 9:28
 * @Version V1.0
 **/
public class TestDemo {
    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        int temp = 0;
        System.out.println("交换前" + a + " " + b);

        temp = a;
        a = b;
        b = temp;
        System.out.println("交换后" + a + " " + b);
    }
}
