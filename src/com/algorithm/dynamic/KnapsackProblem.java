package com.algorithm.dynamic;

/**
 * @ClassName KnapsackProblem
 * @Description 01背包问题
 * @Author liushi
 * @Date 2020/11/14 16:51
 * @Version V1.0
 **/
public class KnapsackProblem {

    public static void main(String[] args) {
        // 物品的重量
        int[] w = {1, 1, 2, 4, 3};
        // 物品的价值,这里val[i],就是前面讲的v[i]
        int[] val = {1500, 900, 1700, 3000, 2000};
        int m = 4;          // 背包的容量
        int n = val.length; // 物品的个数

        // 创建二维数组,表示
        // v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        // 为了记录放入商品的情况,我们定一个二维数组
        int[][] path = new int[n + 1][m + 1];
        // 初始化为二维数组的第一行和第一列,可以不去处理,因为默认为0
        for (int i = 0; i < m + 1; i++) {
            // 将第一行设置为0
            v[0][i] = 0;
        }
        for (int i = 0; i < n + 1; i++) {
            // 将第一列设置为0
            v[i][0] = 0;
        }

        // 根据前面得到的公式来动态规划处理
        for (int i = 1; i < v.length; i++) {        //不处理第一行,i是从1开始的
            for (int j = 1; j < v[0].length; j++) { //不处理第一列,j是从1开始的
                // 因为我们程序i是从1开始的,因此公式中w[i] 修改[i-1]
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    // 说明:
                    // 因为我们的i 从1开始的,因此公式需要调整成
                    // v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);

                    // 为了记录商品存放背包的情况,我们不能直接的使用上面的公式,需要使用if-else来公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        // 把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        // 找出二维数组中的最大值
        int max = 0;
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                if (max < v[i][j]) {
                    max = v[i][j];

                }
            }
        }
        System.out.println("最大价值为: " + max);

        // 输出一下v,
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        // 输出最后我们是放入的那些商品
        // 遍历path,这样输出会把所有的访入情况都得到,其实我们只需要最后的放入
        int i = v.length - 1;    // 行的最大下标
        // int j = v[0].length - 1;   // j的值等于背包的容量   或者说列的最大下标
        int j = m;   // j的值等于背包的容量
        while (i > 0) {
            if (v[i][j] == v[i - 1][j]) {
                System.out.println("未选第" + i + "件物品");
            } else if (v[i][j] == v[i - 1][j - w[i - 1]] + val[i - 1]) {
                System.out.println("选了第" + i + "件物品");
                j -= w[i - 1];
            }
            i--;
        }

    }
}
