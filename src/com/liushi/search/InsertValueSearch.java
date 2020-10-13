package com.liushi.search;

/**
 * @ClassName InsertValueSearch
 * @Description 插值查找算法
 * @Author liushi
 * @Date 2020/10/13 20:42
 * @Version V1.0
 **/
public class InsertValueSearch {


    public static void main(String[] args) {
        // 定义一个[1--100]范围的数组
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        int indexRecursion = searchRecursion(arr, 0, arr.length - 1, 100);
        System.out.println(indexRecursion);
        int binaryIndexRecursion = binarySearchRecursion(arr, 0, arr.length - 1, 100);
        System.out.println(binaryIndexRecursion);
        int indexNormal = searchNormal(arr, 100);
        System.out.println(indexNormal);
        int binaryIndexNormal = binarySearchNormal(arr, 100);
        System.out.println(binaryIndexNormal);
        // System.out.println(Arrays.toString(arr));
    }

    /**
     * 插值查找算法的实现
     * 说明:算法要求数组是有序的
     *
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标, 如果没有找到, 就返回-1
     */
    public static int searchRecursion(int[] arr, int left, int right, int findVal) {
        System.out.println("递归插值查找次数~");
        // 没有找到元素的条件
        // 注意: findVal < arr[0] || findVal > arr[arr.length - 1]
        // 必须需要,否则我们得到的mid可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        // 求出中间值,自适应[一个数学公式]
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        if (findVal > arr[mid]) {
            // 说明应该向右边递归
            return searchRecursion(arr, left, mid + 1, findVal);
        } else if (findVal < arr[mid]) {
            // 说明应该向左边递归
            return searchRecursion(arr, mid - 1, right, findVal);
        } else {
            return mid;
        }
    }

    public static int searchNormal(int[] arr, int findVal) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (!(left > right || findVal < arr[0] || findVal > arr[arr.length - 1])) {
            System.out.println("非递归插值查找次数~");
            mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
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

    public static int binarySearchNormal(int[] arr, int findVal) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            System.out.println("非递归二分查找次数~");
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

    public static int binarySearchRecursion(int[] arr, int left, int right, int findVal) {
        System.out.println("递归二分查找次数~");
        // 当left > right 是,说明递归整个数组,但没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            // 向右递归
            return binarySearchRecursion(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            // 向左递归
            return binarySearchRecursion(arr, left, mid - 1, findVal);
        } else {
            // 找到了返回下标
            return mid;
        }
    }
}
