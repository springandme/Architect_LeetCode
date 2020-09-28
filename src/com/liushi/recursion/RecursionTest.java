package com.liushi.recursion;

/**
 * @ClassName RecursionTest
 * @Description TODO
 * @Author liushi
 * @Date 2020/9/26 17:51
 * @Version V1.0
 **/
public class RecursionTest {
    public static void main(String[] args) {
        // test(4);
        System.out.println(factorial(4));
    }

    // 打印问题,了解递归调用机制
    public static void test(int i) {
        if (i > 2) {
            test(i - 1);
        } /*else {
            System.out.println("i=" + i);
        }*/
        System.out.println("i=" + i);
    }

    // 阶乘
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
