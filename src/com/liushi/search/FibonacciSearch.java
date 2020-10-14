package com.liushi.search;

import java.util.Arrays;

/**
 * @ClassName FibonacciSearch
 * @Description 斐波那契查找算法
 * @Author liushi
 * @Date 2020/10/14 14:53
 * @Version V1.0
 **/
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        // fib();
        int index = fibSearch(arr, 10);
        if (index != -1) {
            System.out.println("index = " + index);
        } else {
            System.out.println("没有找到!");
        }
    }

    //

    /**
     * 非递归方法得到一个斐波那契数列
     * 说明:因为后面我们mid=low + F(k-1) - 1,需要使用到斐波那契数列,因此我们需要先获取一个斐波那契数列
     *
     * @return 得到一个长度为20的斐波那契数列
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        // System.out.println(Arrays.toString(f));
        return f;
    }

    /**
     * 编写斐波那契查找算法
     * 使用非递归的方式编写算法
     * mid=low + F(k-1) - 1
     *
     * @param arr 数组
     * @param key 我们需要查找的关键码(值)
     * @return 返回对应的下标, 如果就返回-1
     */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        // 表示斐波那契分割数值的下标
        int k = 0;
        // 存放mid值
        int mid = 0;
        // 获取到斐波那契数列
        int[] f = fib();

        // 获取到斐波那契分割值得下标
        // 判断顺序表长度n是否等于F[k]-1,不等于将原数组查找为长度扩展为为F[k]-1
        while (high > f[k] - 1) {
            k++;
        }
        // 因为f[k]值可能大于 a 的长度,因此我们需要使用Arrays类,构造一个新的数组,并指向temp[]
        // 不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        // 举例:
        // temp = {1, 8, 10, 89, 1000, 1234, 0 ,0 } => {1, 8, 10, 89, 1000, 1234, 1234 ,1234}
        // 实际上需求使用a数组最后的数值填充到temp
        for (int i = high; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        // 使用while来循环处理,找到我们的数key
        // 只要这个条件满足,就可以继续找
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            // 说明我们应该继续向数组的前面查找[左边]
            if (key < temp[mid]) {
                high = mid - 1;
                /**
                 * 为什么是 k--
                 * 说明
                 * 1. 全部元素 = 前面的元素 + 后面的元素
                 * 2. f[k] = f[k-1] + f[k-2]
                 * 如果继续想数组的前面查找(左边)则应该是(f[k-1]-1)进行拆分
                 * 因为前面有f[k-1]个元素,所有可以继续拆分f[k-1] = f[k-2] + f[k-3]
                 * 即在f[k-1]-1 的前面继续查找 k--
                 * 即下次循环 mid = f[k-1-1]-1
                 */
                k--;
                // 说明我们应该继续向数组的后面查找[右边]
            } else if (key > temp[mid]) {
                low = mid + 1;
                /**
                 * 为什么是 k-=2
                 * 说明
                 * 1. 全部元素 = 前面的元素 + 后面的元素
                 * 2. f[k] = f[k-1] + f[k-2]
                 * 3. 因为后面我们有f[k-2] 所有可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                 * 4. 即在f[k-2] 的前面进行查找 k-=2
                 * 5. 即下次循环 mid = f[k-1-2] -1
                 */
                k -= 2;
            } else {
                // 找到,需要确认,返回的哪个下标 mid <= high 表示mid在原来那个没有变长的arr数组内,
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
