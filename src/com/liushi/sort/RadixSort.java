package com.liushi.sort;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * @ClassName RadixSort
 * @Description 基数排序
 * @Author liushi
 * @Date 2020/10/12 20:43
 * @Version V1.0
 **/
public class RadixSort {

    /**
     * 基数排序基本思想,将所有待比同一为同样的数位长度,数位较短的数前面补零,然后
     * 从最低位[比如个位]开始,依次进行依次排序,这样从最低位排序一直到最高位排序完成以后,数列就变成一个有序序列
     * -
     * 性能测试 笔记本 i5-8300H 16G 800W条数据
     * 0.499S/0.532S/0.53S/0.508S/0.532S/0.491S
     *
     * @param args
     */
    public static void main(String[] args) {
        // int[] arr = {53, 3, 542, 748, 14, 214};
        // System.out.println("待处理的数字为12584");
        // System.out.println("num / 1 % 10    是取个位的值" + 12584 / 1 % 10);
        // System.out.println("num / 10 % 10   是取十位的值" + 12584 / 10 % 10);
        // System.out.println("num / 100 % 10  是取个位的值" + 12584 / 100 % 10);
        // // System.out.println(12584 % 10);
        // // 10的0次幂 = 1,10的1次幂 = 10,10的2次幂 = 100
        // System.out.println((int) Math.pow(10, 0));
        // System.out.println((int) Math.pow(10, 1));
        // System.out.println((int) Math.pow(10, 2));
        // // deriveSort(arr);
        // sort(arr);
        // System.out.println(Arrays.toString(arr));
        Instant before = Instant.now();
        // 测试一下归并排序的速度,给800W个数组,测试性能
        // 创建要给800W个随机的数组
        // 8000W个数据直接内存爆炸
        // 80000000 * 11 * 4 / 1024 /1024 / 1014  =3.3G -->java.lang.OutOfMemoryError: Java heap space
        int[] randomArr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            // 生成一个[0,8000000) 数
            randomArr[i] = (int) (Math.random() * 8000000);
        }

        Instant start = Instant.now();
        System.out.println("生成8000W条数据所消耗的时间为: " + Duration.between(before, start));
        sort(randomArr);

        Instant end = Instant.now();
        // 输出为ISO 8601持续时间格式 ： PT1M7.039S （1分7.039秒）。
        System.out.println("基数排序所消耗的时间为: " + Duration.between(start, end));
    }

    /**
     * 基数排序
     *
     * @param arr 待排数组
     */
    public static void sort(int[] arr) {
        // 定义一个二维数组,表示10个桶,每个桶就是一个一维数组
        // 说明
        // 1.二维数组包含10个以为数组
        // 2.为了防止在放入数的时候,数据溢出,则每个一维数组(桶),大小定为arr.length
        // 3.明确,基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        // 为了记录每个桶,实际存放了多少个数据,我们定义一个一维数组来记录各个桶每次放入的数组
        // bucketElementCounts[0],就是记录bucket[0]这个桶放入数据个数
        int[] bucketElementCounts = new int[10];

        // 根据前面的推导过程,我们可以得到最终的基数排序过程

        // 1.先得到数组中最大的数的位数
        // 假定第一个数就是最大数
        int max = arr[0];
        // 找到数组中最大的位数
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 得到最大数是几位数 -->利用max+"" ->max就类型提升为字符串类型,就可以返回最大位数是几位
        int maxLength = (max + "").length();


        // 使用循环将代码处理下
        for (int i = 0; i < maxLength; i++) {
            // (针对每个元素的对应为进行排序处理), 第一次是个位,第二次是十位,第三次是百位
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素的个位/十位/百位的值
                int digitOfElement = arr[j] / (int) Math.pow(10, i) % 10;
                // 放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            // 按照这个桶的顺序(一维数组的下标一次取出数据,放入原来数组)
            int index = 0;
            // 遍历每一个桶,并将桶中的数据,放入原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                // 如果桶中有数据,我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    // 说明这个桶是有数据,然后循环该桶即第k个桶(即第k个一维数组),放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        // 取出元素放入到arr中
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                // 第一轮处理后, 需要将每个bucketElementCount[k] = 0 !!!!!!
                bucketElementCounts[k] = 0;
            }
            // System.out.println("第" + (i + 1) + "轮的排序结果 arr = " + Arrays.toString(arr));
        }
    }

    /**
     * 基数排序方法的推导过程
     *
     * @param arr 待排数组
     */
    public static void deriveSort(int[] arr) {


        // 定义一个二维数组,表示10个桶,每个桶就是一个一维数组
        // 说明
        // 1.二维数组包含10个以为数组
        // 2.为了防止在放入数的时候,数据溢出,则每个一维数组(桶),大小定为arr.length
        // 3.明确,基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        // 为了记录每个桶,实际存放了多少个数据,我们定义一个一维数组来记录各个桶每次放入的数组
        // bucketElementCounts[0],就是记录bucket[0]这个桶放入数据个数
        int[] bucketElementCounts = new int[10];

        // 第一轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素的个位的值  num % 10  ->个位元素
            int digitOfElement = arr[j] % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        // 按照这个桶的顺序(一维数组的下标一次取出数据,放入原来数组)
        int index = 0;
        // 遍历每一个桶,并将桶中的数据,放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中有数据,我们才放入到原数组
            if (bucketElementCounts[k] != 0) {
                // 说明这个桶是有数据,然后循环该桶即第k个桶(即第k个一维数组),放入
                for (int i = 0; i < bucketElementCounts[k]; i++) {
                    // 取出元素放入到arr中
                    arr[index] = bucket[k][i];
                    index++;
                }
            }
            // 第一轮处理后, 需要将每个bucketElementCount[k] = 0 !!!!!!
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮,对个位的排序结果 arr = " + Arrays.toString(arr));

        //============================================================

        // 第二轮(针对每个元素的十位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素的个位的值  num / 10 % 10  ->十位元素
            int digitOfElement = arr[j] / 10 % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        // 按照这个桶的顺序(一维数组的下标一次取出数据,放入原来数组)
        index = 0;
        // 遍历每一个桶,并将桶中的数据,放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中有数据,我们才放入到原数组
            if (bucketElementCounts[k] != 0) {
                // 说明这个桶是有数据,然后循环该桶即第k个桶(即第k个一维数组),放入
                for (int i = 0; i < bucketElementCounts[k]; i++) {
                    // 取出元素放入到arr中
                    arr[index] = bucket[k][i];
                    index++;
                }
            }
            // 第二轮处理后, 需要将每个bucketElementCount[k] = 0 !!!!!!
            bucketElementCounts[k] = 0;
        }
        System.out.println("第二轮,对十位的排序结果 arr = " + Arrays.toString(arr));

        //============================================================

        // 第三轮(针对每个元素的百位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素的个位的值  num / 100 % 10  ->百位元素
            int digitOfElement = arr[j] / 100 % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        // 按照这个桶的顺序(一维数组的下标一次取出数据,放入原来数组)
        index = 0;
        // 遍历每一个桶,并将桶中的数据,放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中有数据,我们才放入到原数组
            if (bucketElementCounts[k] != 0) {
                // 说明这个桶是有数据,然后循环该桶即第k个桶(即第k个一维数组),放入
                for (int i = 0; i < bucketElementCounts[k]; i++) {
                    // 取出元素放入到arr中
                    arr[index] = bucket[k][i];
                    index++;
                }
            }
            // 第三轮处理后, 需要将每个bucketElementCount[k] = 0 !!!!!!
            bucketElementCounts[k] = 0;
        }
        System.out.println("第三轮,对百位的排序结果 arr = " + Arrays.toString(arr));
    }
}
