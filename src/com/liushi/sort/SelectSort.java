package com.liushi.sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @ClassName SelectSort
 * @Description 简单选择排序
 * @Author liushi
 * @Date 2020/9/30 20:00
 * @Version V1.0
 **/
public class SelectSort {

    /**
     * 性能测试结果 cpu i7-8700 16G内存 8W条数据,
     * 排序所消费的时间为 1.863秒
     * 1.852S/1.86S/1.863S/1.885S
     *
     * @param args
     */
    public static void main(String[] args) {
        /*
        int[] arr = {101, 34, 119, 1};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
        */
        Instant before = Instant.now();
        // 测试一下冒泡排序的速度O(n^2),给8W个数组,测试性能
        // 创建要给8W个随机的数组
        int[] randomArr = new int[80000];
        for (int i = 0; i < 80000 - 1; i++) {
            // 生成一个[0,8000000) 数
            randomArr[i] = (int) (Math.random() * 8000000);
        }

        Instant start = Instant.now();
        System.out.println("生成8W条数据所消耗的时间为: " + Duration.between(before, start));
        sort(randomArr);

        Instant end = Instant.now();
        // 输出为ISO 8601持续时间格式 ： PT1M7.039S （1分7.039秒）。
        System.out.println("排序所消耗的时间为: " + Duration.between(start, end));
    }

    /**
     * 选择排序的时间复杂度是O(n^2), 先写内存for循环,再写外层for循环
     * if (min > arr[j]){
     * -- 这里表示 从小到大 升序
     * }
     * if (max < arr[j]){
     * -- 这里表示 从大到小 降序
     * }
     *
     * @param arr 需要排序的数组
     */
    public static void sort(int[] arr) {

        // 在推导的过程中,我们发现了规律,因此,在外面嵌套一个for循环解决
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                // 说明假定的最小值,并不是最小
                if (min > arr[j]) {
                    // 重置minIndex
                    minIndex = j;
                    // 重置min
                    min = arr[j];
                }
            }
            // minIndex = 3  ,min =1
            // 将最小值,放在arr[0],即交换
            // 判断优化-->  没有进入那个if (min > arr[i]) 判断,表示minIndex和min的值没有发生变化,就不需要进行交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            // System.out.println("第" + (i + 1) + "轮后~~");
            // System.out.println(Arrays.toString(arr));
        }

    /*
        // 使用逐步推导的方式来,讲解选择排序
        // 第一轮
        // 原始的数组: 101,34,119,1
        // 第一轮排序: 1,34,119,101
        // 算法 先简单--> 再复杂 ,就是可以把一个复杂的算法,拆分成简单的问题,->逐步解决

        // 第一轮
        // 假定最小值的下标为0,且最小值为arr[0] = 101
        int minIndex = 0;
        int min = arr[0];
        for (int j = 0 + 1; j < arr.length; j++) {
            // 说明假定的最小值,并不是最小
            if (min > arr[j]) {
                // 重置minIndex
                minIndex = j;
                // 重置min
                min = arr[j];
            }
        }
        // minIndex = 3  ,min =1
        // 将最小值,放在arr[0],即交换
        // 判断优化-->
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
        }

        System.out.println("第一轮后~~");
        System.out.println(Arrays.toString(arr)); // 1,34,119,101

        // 第二轮
        minIndex = 1;
        min = arr[1];
        for (int j = 1 + 1; j < arr.length; j++) {
            // 说明假定的最小值,并不是最小
            if (min > arr[j]) {
                // 重置minIndex
                minIndex = j;
                // 重置min
                min = arr[j];
            }
        }
        // minIndex = 3  ,min =1
        // 将最小值,放在arr[0],即交换
        if (minIndex != 1) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }

        System.out.println("第二轮后~~");
        System.out.println(Arrays.toString(arr)); // 1,34,119,101

        // 第二轮
        minIndex = 2;
        min = arr[2];
        for (int j = 2 + 1; j < arr.length; j++) {
            // 说明假定的最小值,并不是最小
            if (min > arr[j]) {
                // 重置minIndex
                minIndex = j;
                // 重置min
                min = arr[j];
            }
        }
        // 将最小值,放在arr[0],即交换
        if (minIndex != 2) {
            arr[minIndex] = arr[2];
            arr[2] = min;
        }

        System.out.println("第三轮后~~");
        System.out.println(Arrays.toString(arr)); // 1,34,101,119
    */
    }
}
