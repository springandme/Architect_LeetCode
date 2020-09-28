package com.liushi.recursion;

/**
 * @ClassName EightQueens
 * @Description 八皇后问题
 * @Author liushi
 * @Date 2020/9/27 20:21
 * @Version V1.0
 **/
public class EightQueens {
    // 统计解法个数
    public static int count = 0;
    public static int flag = 0;
    // 定义一个max,表示共有多少个皇后
    public int max = 8;
    // 定义数组array,保存皇后放置位置的结果,比如arr= { 0 ,4 ,7 ,5 ,2 ,6 ,1 ,3}
    public int[] array = new int[max];

    public static void main(String[] args) {
        EightQueens queens = new EightQueens();
        queens.check(0);
        System.out.println("一共有" + count + "个解法");
        System.out.println("一共判断冲突的次数" + flag + "次"); // 1.5W次数
    }

    /**
     * 编写一个方法,放置第n个皇后
     * -->特别注意: check 是每一次递归时,进入到check中都有 for (int i = 0; i < max; i++),因此会有回溯
     *
     * @param n 表示第n个皇后 n的范围在0-7 n=0,表示为第一个皇后
     */
    private void check(int n) {
        // n = 8,其实8个皇后已然放好了
        if (n == max) {
            print();
            return;
        }

        // 依次放入判断,并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前这个皇后n, 放到该行的第一列
            array[n] = i;
            // 判断当放置第n个皇后到i列时,是否冲突
            if (judge(n)) {
                // 不冲突,接着放n+1个皇后,即可是递归
                check(n + 1);
            }
            // 如果冲突,就继续执行array[n] = i;即将第n个皇后,放置在本行的后移的一个位置
        }
    }


    /**
     * 查看当我们放置第n个皇后,就去检测该皇后是否和前面已经摆放的皇后冲突
     *
     * @param n 表示第n个皇后 n的范围在0-7 n=0,表示为第一个皇后
     * @return
     */
    private boolean judge(int n) {
        flag++;
        for (int i = 0; i < n; i++) {
            // 说明
            // 1. array[i] == array[n] 表示判断 第n个皇后与前面皇后处在同一列
            // 2. Math.abs(n - i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一直线上
            // n = 1 放置第2列   n=1 array[1] = 1表示第二个皇后放在第二行第二列,坐标(1,1)
            // Math.abs(1-0) == 1 Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
            // 3. 判断是否在同一行,没有必要,n 每次都在递增
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    // 写一个方法,可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
