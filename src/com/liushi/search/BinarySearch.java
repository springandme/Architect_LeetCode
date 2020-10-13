package com.liushi.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BinarySearch
 * @Description 二分查找[使用前提:该数据是有序的]
 * @Author liushi
 * @Date 2020/10/13 18:55
 * @Version V1.0
 **/
public class BinarySearch {


    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        // int index = searchRecursion(arr, 0, arr.length - 1, 1000);
        int index2 = searchNormal(arr, 1000);
        // if (index != -1) {
        //     System.out.println("找到,下标为= " + index);
        // } else {
        //     System.out.println("没有找到!");
        // }
        if (index2 != -1) {
            System.out.println("找到,下标为= " + index2);
        } else {
            System.out.println("没有找到!");
        }
    }

    /**
     * 二分查找算法
     *
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标, 如果没有找到, 就返回-1
     */
    public static int searchRecursion(int[] arr, int left, int right, int findVal) {
        // 当left > right 是,说明递归整个数组,但没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            // 向右递归
            return searchRecursion(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            // 向左递归
            return searchRecursion(arr, left, mid - 1, findVal);
        } else {
            // 找到了返回下标
            return mid;
        }
    }

    /**
     * 非递归写法
     *
     * @param arr     数组
     * @param findVal 需要查找的值
     * @return 如果找到就返回下标, 如果没有找到, 就返回-1
     */
    public static int searchNormal(int[] arr, int findVal) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (findVal > arr[mid]) {
                left = mid + 1;
            } else if (findVal < arr[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * 完成一个课后思考题:
     * 当有多个相同的数值是,如何将所有的数值都找到,比如这里的1000
     * {1, 8, 10, 89, 1000, 1000, 1000, 1234}
     * -
     * 思路分析:
     * 1. 在找到mid索引值,不要马上返回
     * 2. 向mid索引值的左边扫描,将所有满足1000的元素下标加入到集合List中
     * 3. 向mid索引值的右边扫描,将所有满足1000的元素下标加入到集合List中
     * 4. 将List返回
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */

    public static List<Integer> searchList(int[] arr, int left, int right, int findVal) {
        // 当left > right 是,说明递归整个数组,但没有找到
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            // 向右递归
            return searchList(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            // 向左递归
            return searchList(arr, left, mid - 1, findVal);
        } else {
            // 找到了返回下标
            List<Integer> resIndexList = new ArrayList<>();
            // 向mid索引值的左边扫描,将所有满足1000的元素下标加入到集合List中
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                // 否则,就temp放入到ResIndexList中
                resIndexList.add(temp);
                temp--;
            }

            // 向mid索引值的右边扫描,将所有满足1000的元素下标加入到集合List中
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                // 否则,就temp放入到ResIndexList中
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;

        }
    }
}
