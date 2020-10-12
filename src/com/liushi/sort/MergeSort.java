package com.liushi.sort;

import java.time.Duration;
import java.time.Instant;

/**
 * @ClassName MergeSort
 * @Description 归并排序
 * @Author liushi
 * @Date 2020/10/12 18:57
 * @Version V1.0
 **/
public class MergeSort {
    public static int count = 0;

    /**
     * 该算法使用分治策略
     * 分的过程,类似把一个数组分成二叉树过程
     * 治的过程是主要过程:分为3步
     * 1.先把左右两边有序序列的数据按规则拷贝到temp数组中
     * 2.然后再把左右两边剩余的元素,一次拷贝到temp数组中
     * 3.再把temp数组拷贝回到arr数组中,注意:并不是每次都拷贝所有元素
     * -
     * 8个数7次,9个数8次 10个数9次
     * mergeCount = arr.length-1
     * -
     * 性能测试 笔记本 i5-8300H 16G
     * 1.328S/1.28S/1.363S/1.321S/1.314S
     *
     * @param args
     */
    public static void main(String[] args) {
        // int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        // // 归并排序需要一个额外空间
        // int[] temp = new int[arr.length];
        // sort(arr, 0, arr.length - 1, temp);
        // System.out.println("归并排序后= " + Arrays.toString(arr));
        Instant before = Instant.now();
        // 测试一下归并排序的速度,给800W个数组,测试性能
        // 创建要给8W个随机的数组
        int[] randomArr = new int[8000000];
        int[] arrTemp = new int[randomArr.length];
        for (int i = 0; i < 8000000; i++) {
            // 生成一个[0,8000000) 数
            randomArr[i] = (int) (Math.random() * 80000000);
        }

        Instant start = Instant.now();
        System.out.println("生成800W条数据所消耗的时间为: " + Duration.between(before, start));
        sort(randomArr, 0, randomArr.length - 1, arrTemp);

        Instant end = Instant.now();
        // 输出为ISO 8601持续时间格式 ： PT1M7.039S （1分7.039秒）。
        System.out.println("归并排序所消耗的时间为: " + Duration.between(start, end));
    }

    /**
     * 分+合方法
     *
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            // 中间索引
            int mid = (left + right) / 2;
            // 向左递归进行分解
            sort(arr, left, mid, temp);
            // 向右递归进行分解
            sort(arr, mid + 1, right, temp);
            // 每分解一次就合并一次
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并的方法[就是治的过程]
     * 合并次数类似于二叉树有关
     * 8个数7次,9个数8次 10个数9次
     * mergeCount = arr.length-1
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        // 初始化i,左边有序序列的初始索引
        int i = left;
        // 初始化j,右边有序序列的初始索引
        int j = mid + 1;
        // 指向temp数组的当前索引
        int t = 0;

        // 1.先把左右两边(有序)的数据按照规则填充到temp数组
        // 直到左右两边的有序序列,有一遍处理完毕为止

        while (i <= mid && j <= right) {
            // 如果左边的有序序列的当前元素,小于等于右边有序序列的当前元素
            // 即将左边的当前元素,拷贝到temp数组
            // 然后t++,左边有序序列的i++
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                // 反之,右边有序序列元素小于左边有序序列
                // 将右边有序序列的当前元素,填充到temp数组,然后t++,j++
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        // 2.把有剩余数据一边的数据依次全部填充到temp
        // 说明左边有序序列还有剩余的元素,就需要全部填充到temp中
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        // 说明右边有序序列还有剩余的元素,就需要全部填充到temp中
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        // 3.将temp数组的元素拷贝到arr
        // 注意:并不是每次都拷贝所有元素
        t = 0;
        int tempLeft = left;
        // 第一次合并tempLeft = 0 ,right =1 //tempLeft=2 right = 3 //tempLeft=0 right =3
        // 最后一次tempLeft =0 right =7
        // 打印测试,很重要!!!!
        // System.out.println("根据二叉树从左到右第" + ++count + "次合并 tempLeft= " + tempLeft + " right= " + right);
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
