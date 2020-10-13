package com.liushi.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SeqSearch
 * @Description 线性查找
 * @Author liushi
 * @Date 2020/10/13 18:35
 * @Version V1.0
 **/
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89, 11};
        List<Integer> list = search(arr, 11);
        if (list.size() == 0) {
            System.out.println("没有查找到!");
        } else {
            System.out.println("找到,下标为=" + list);
        }
    }

    /**
     * 实现的线性查找[就是数组的顺序遍历],可以找到多个满足条件的值
     *
     * @param arr
     * @param value
     * @return 返回-1表示没有查找到
     */
    public static List<Integer> search(int[] arr, int value) {
        ArrayList<Integer> list = new ArrayList<>();
        // 线性查找是逐一比对,发现有相同值,就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (value == arr[i]) {
                // return i;
                list.add(i);
            }
        }
        return list;
    }
}
