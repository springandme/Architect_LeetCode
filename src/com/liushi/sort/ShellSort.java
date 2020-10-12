package com.liushi.sort;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * @ClassName ShellSort
 * @Description 希尔排序
 * @Author liushi
 * @Date 2020/10/1 9:05
 * @Version V1.0
 **/
public class ShellSort {

    /**
     * 性能测试结果 cpu i7-8700 16G内存 8W条数据,
     * 使用交换排序所消费的时间为 5.275秒
     * 5.289S/5.235S/5.297S/5.268S/5.302S
     *
     * @param args
     */
    public static void main(String[] args) {
        /*
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("没有排序");
        System.out.println(Arrays.toString(arr));
        sortExchange(arr);
        System.out.println(Arrays.toString(arr));
        */

        Instant before = Instant.now();
        // 测试一下插入排序的速度O(n^2),给8W个数组,测试性能
        // 创建要给8W个随机的数组
        int[] randomArr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个[0,8000000) 数
            randomArr[i] = (int) (Math.random() * 8000000);
        }

        Instant start = Instant.now();
        System.out.println("生成8W条数据所消耗的时间为: " + Duration.between(before, start));
        sortExchange(randomArr);

        Instant end = Instant.now();
        // 输出为ISO 8601持续时间格式 ： PT1M7.039S （1分7.039秒）。
        System.out.println("希尔排序所消耗的时间为: " + Duration.between(start, end));
    }

    /**
     * 希尔排序时,对有序序列在插入时采取交换法
     *
     * @param arr 需要排序的数组
     */
    public static void sortExchange(int[] arr) {
        // 需要交换的临时变量
        int temp = 0;
        // int count = 0;

        // 根据前面的逐步分析,在外层加一个for循环处理
        for (int gap = arr.length; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //　遍历各组中所有的元素(共gap组) ,步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            // System.out.println("希尔排序第" + ++count + "轮排序后");
            // System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 希尔排序是,对有序序列在插入时采取移位法
     *
     * @param arr 需要排序的数组
     */
    public static void sortMove(int[] arr) {

        // 增量gap,并逐步的缩小增量


    }

    /**
     * 希尔排序的推导过程
     *
     * @param arr 需要排序的数组
     */
    public static void deriveSort(int[] arr) {
        // 需要交换的临时变量
        int temp = 0;
        // 使用逐步推导的方式来编写希尔排序

        // 希尔排序的第一轮排序
        // 因为第一轮排序,是将10个数组分成了5组
        for (int i = 5; i < arr.length; i++) {
            // 遍历各组中所有元素(共2组,每组有五个元素),步长5 [0,5] [1,6] [2,7] [3,8] [4,9]
            for (int j = i - 5; j >= 0; j -= 5) {
                // 如果当前元素大于加上步长后的按个元素,说明交换 -_- 2020年10月1日09:38:08,使用临时变量的temp,交换两个数竟然写错了,赶紧去面壁去!!!
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第一轮排序后");
        System.out.println(Arrays.toString(arr)); // [3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
        // 希尔排序的第二轮排序
        // 因为第二轮排序,是将10个数组分成5/2 =2 组
        for (int i = 5 / 2; i < arr.length; i++) {
            // 遍历各组中所有元素(共5组,每组有两个元素),步长5/2 = 2 [3,1,0,9,7] [5,6,8,4,2]
            for (int j = i - 5 / 2; j >= 0; j -= 5 / 2) {
                // 如果当前元素大于加上步长后的按个元素,说明交换
                if (arr[j] > arr[j + 5 / 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5 / 2];
                    arr[j + 5 / 2] = temp;
                }
            }
        }
        System.out.println("第二轮排序后");
        System.out.println(Arrays.toString(arr)); // [0, 2, 1, 4, 3, 5, 7, 6, 9, 8]

        // 希尔排序的第三轮排序
        // 因为第二轮排序,是将10个数组分成2/2 =1 组
        for (int i = 5 / 2 / 2; i < arr.length; i++) {
            // 遍历各组中所有元素(共1组,每组有十个元素),步长2/2 = 1 [0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
            for (int j = i - 5 / 2 / 2; j >= 0; j -= 5 / 2 / 2) {
                // 如果当前元素大于加上步长后的按个元素,说明交换
                if (arr[j] > arr[j + 5 / 2 / 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5 / 2 / 2];
                    arr[j + 5 / 2 / 2] = temp;
                }
            }
        }
        System.out.println("第三轮排序后");
        System.out.println(Arrays.toString(arr)); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
