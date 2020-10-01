package com.liushi.sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @ClassName BubbleSort
 * @Description 冒泡排序
 * @Author liushi
 * @Date 2020/9/30 17:04
 * @Version V1.0
 **/
public class BubbleSort {

    /**
     * 性能测试结果 cpu i7-8700 16G内存 8W条数据,
     * 使用临时变量测试 int temp = 0; 排序所消费的时间为 7.043秒
     * 不是用临时变量, 用 arr[i] = arr[i]^arr[i+1]; 排序所消费的时间为 10.616秒
     *
     * @param args
     */
    public static void main(String[] args) {
        // int[] arr = {3, 9, -1, 10, -2, 14, 0};
        // int[] arr = {3, 8, 7, 10, 11, 14, 16};
        // System.out.println("没有排序的数组");
        // System.out.println(Arrays.toString(arr));

        Instant before = Instant.now();
        // 测试一下冒泡排序的速度O(n^2),给8W个数组,测试性能
        // 创建要给8W个随机的数组
        int[] randomArr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个[0,8000000) 数
            randomArr[i] = (int) (Math.random() * 8000000);
        }

        // long beforeSort = System.currentTimeMillis();
        Instant start = Instant.now();
        System.out.println("生成8W条数据所消耗的时间为: " + Duration.between(before, start));
        sort(randomArr);
        // System.out.println("排序后的数组");
        // System.out.println(Arrays.toString(arr));
        // long laterSort = System.currentTimeMillis();
        // System.out.println("排序所消耗的时间为: " + (laterSort - beforeSort));
        Instant end = Instant.now();
        // 输出为ISO 8601持续时间格式 ： PT1M7.039S （1分7.039秒）。
        System.out.println("排序所消耗的时间为: " + Duration.between(start, end));
    }

    /**
     * 冒泡排序的时间复杂度, O(n^2), 两层for循环,先写内循环,在写外循环
     *
     * @param arr 需要排序的数组
     */
    public static void sort(int[] arr) {
        // 交换的临时变量
        int temp = 0;
        // 标识变量,标识是否进行过交换
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大,则交换
                if (arr[j] > arr[j + 1]) {
                    // 进行了交换,就把这个标记变量设置为true
                    flag = true;
                    // arr[j] = arr[j + 1] ^ arr[j];
                    // arr[j + 1] = arr[j + 1] ^ arr[j];
                    // arr[j] = arr[j + 1] ^ arr[j];
                    temp = arr[i];
                    arr[i + 1] = temp;
                    arr[i] = arr[i + 1];
                }
            }
            // 在一趟排序中,一次交换都没发生过,直接退出外循环,表明数组已经排好序了
            // 正常的BubbleSort排序趟数是array.length-1
            if (!flag) {
                break;
            } else {
                // 进行了交换,记得一定要重置flag为false!,进行下次判断
                flag = false;
            }
            // System.out.println("第" + (i + 1) + "趟排序后的数组");
            // System.out.println(Arrays.toString(arr));
        }
    }
}
