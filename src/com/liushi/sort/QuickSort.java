package com.liushi.sort;

import java.util.Arrays;

/**
 * @ClassName QuickSort
 * @Description 快速排序
 * @Author liushi
 * @Date 2020/10/12 8:37
 * @Version V1.0
 **/
public class QuickSort {

    /**
     * 性能测试结果 cpu i7-8700 16G内存 800W条数据,
     * 自己看博客写法--0.945S/0.905S/0.906S/0.928S/0.912S/0.928S/0.928S
     * 韩顺平老师写法--1.056S/1.032S/1.017S/1.033S/1.037S/1.017S
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        sort(arr, 0, arr.length - 1);
        System.out.println("排序后的数组为: " + Arrays.toString(arr));
       /* Instant before = Instant.now();
        // 测试一下快速排序的速度,给800W个数组,测试性能
        // 创建要给8W个随机的数组
        int[] randomArr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            // 生成一个[0,8000000) 数
            randomArr[i] = (int) (Math.random() * 80000000);
        }

        Instant start = Instant.now();
        System.out.println("生成800W条数据所消耗的时间为: " + Duration.between(before, start));
        sort(randomArr, 0, randomArr.length - 1);

        Instant end = Instant.now();
        // 输出为ISO 8601持续时间格式 ： PT1M7.039S （1分7.039秒）。
        System.out.println("快速排序所消耗的时间为: " + Duration.between(start, end));*/
    }


    /**
     * 根据CSDN博主写的vayneXiao快速排序
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void sort(int[] arr, int left, int right) {
        int i = left;
        int j = right;
        // 交换的临时变量
        int temp = 0;
        // pivot就是存的准基数
        int pivot = arr[left];
        if (left > right) {
            return;
        }
        // while循环的目的是让pivot值小放到左边,比pivot值大放在右边
        while (i != j) {
            // 顺序很重要,要先从右边开始找
            while (arr[j] >= pivot && i < j) {
                j--;
            }
            // 再找左边的
            while (arr[i] <= pivot && i < j) {
                i++;
            }
            // 交换两个数在数组中的位置
            if (i < j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // 最终将基数归位,要把那个基数和哨兵因碰撞而探测结束的位置,进行数值交换
        arr[left] = arr[i];
        arr[i] = pivot;

        // 先处理左边的,这是一个递归过程
        sort(arr, left, i - 1);
        // 这个right > i 是处理数组越界异常,具体原因未搞明白
        if (right > i) {
            // 继续处理右边的,这是一个递归过程
            sort(arr, i + 1, right);
        }
    }

    /**
     * 韩顺平老师写法
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量，作为交换时使用
        //while循环的目的是让比pivot 值小放到左边,比pivot 值大放到右边
        while (l < r) {
            //在pivot的左边一直找,找到大于等于pivot值,才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找,找到小于等于pivot值,才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l >= r说明pivot 的左右两的值，已经按照左边全部是
            //小于等于pivot值，右边全部是大于等于pivot值
            if (l >= r) {
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值 相等 r--， 前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        // 如果 l == r, 必须l++, r--, 否则为出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
