package com.liushi.sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @ClassName InsertSort
 * @Description 插入排序
 * @Author liushi
 * @Date 2020/9/30 21:01
 * @Version V1.0
 **/
public class InsertSort {

    /**
     * 插入排序简介:
     * 插入排序非常类似于整理扑克牌,在开始摸牌时,左手是空的,牌面朝下放在桌上,一次从桌上摸起一张牌
     * 并将它插入左手一把牌中的正确位置,为了找到这张牌的正确位置,要将它与手中已有的牌从右到左进行比较,
     * 无论什么时候,左手中的牌都是排好序的,
     * --但有个小区别,在数组中插入元素不能像插入扑克牌一样,不可能在两个相邻的存储单元之间再插入一个单元,
     * 因此要将插入点之后的数据依次往后移动一个单元
     * --
     * 性能测试结果 cpu i7-8700 16G内存 8W条数据,
     * 排序所消费的时间为 0.532秒
     * 0.589S/0.532S/0.529S/0.528S/0.527S
     *
     * @param args
     */
    public static void main(String[] args) {
      /*
        int[] arr = {101, 34, 119, 1};
        sort2(arr);
        System.out.println("排序后的数组为: " + Arrays.toString(arr));
      */
        Instant before = Instant.now();
        // 测试一下插入排序的速度O(n^2),给8W个数组,测试性能
        // 创建要给8W个随机的数组
        int[] randomArr = new int[80000];
        for (int i = 0; i < 80000 - 1; i++) {
            // 生成一个[0,8000000) 数
            randomArr[i] = (int) (Math.random() * 8000000);
        }

        Instant start = Instant.now();
        System.out.println("生成8W条数据所消耗的时间为: " + Duration.between(before, start));
        sort2(randomArr);

        Instant end = Instant.now();
        // 输出为ISO 8601持续时间格式 ： PT1M7.039S （1分7.039秒）。
        System.out.println("简单插入排序所消耗的时间为: " + Duration.between(start, end));
    }

    /**
     * @param arr 需要排序的数组
     */
    public static void sort(int[] arr) {

        // 使用逐步推导的方式讲解,便于理解
        // 第一轮 { [101], 34, 119, 1}; -->{ [34, 101], 119, 1};

        // 定义带插入的数
        int insertVal = arr[1];
        // 即arr[1]的前面这个数的下标
        int insertIndex = 1 - 1;

        // 给insertVal 找到插入的位置
        // 说明
        // 1. insetIndex >= 0 保证在给insertVal找插入位置,不越界
        // 2. insertVal < arr[insertIndex] 待插入的数,还没有找到插入位置
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {

        }
    }

    public static void sort2(int[] arr) {

        int insertVal = 0;
        int insertIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            //
            insertIndex = i - 1;
            // 定义待插入的数
            insertVal = arr[i];
            // 给insertVal 找到正确插入的位置
            // 说明:
            // 1. insertIndex >= 0 保证在给insertVal找插入位置,不越界
            // 2. insertVal < arr[insertIndex] 待插入的数,还没有找到插入的位置
            // 3. arr[insertIndex + 1] = arr[insertIndex] 就是将arr[insertIndex]这个数后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }
    }
}
