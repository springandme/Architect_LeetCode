package com.algorithm.dynamic;

/**
 * @ClassName MyKnapsack
 * @Description TODO
 * @Author liushi
 * @Date 2020/11/15 9:01
 * @Version V1.0
 **/
public class MyKnapsack {
    /**
     * 说明:
     * weight[i]: 第i个物品的重量
     * value[i]:  第i个物品的价值
     * v[i][j]:   前i个物品放入容量为j包的最大价值
     * v[i-1][j]:   前i-1个物品放入容量为j包的最大价值
     * v[i-1][j-w[i]]: 前i-1个物品放入容量为j-w[i]包的最大价值
     * ####
     * 状态转移方程
     * v[i][m]=max{v[i - 1][m], value[i] + v[i - 1][m - weight[i]]}
     */


    public static int capacity;         // 背包容量
    public static int n;                // 物品个数
    public static int[] value;          // 物品价值数组
    public static int[] weight;         // 物品重量数组

    public static void main(String[] args) {
        // 初始化赋值操作
        capacity = 4;
        n = 5;
        // 为"weight[i]: 第i个物品的重量" 与数组下标对应,所以使用了数组第一为补0操作
        value = new int[]{0, 1500, 900, 1700, 3000, 2000};
        weight = new int[]{0, 1, 1, 2, 4, 3};

        // 构造最优解的网格:
        int[][] v = new int[n + 1][capacity + 1];
        for (int i = 0; i < v.length - 1; i++) {
            for (int j = 0; j < v[0].length - 1; j++) {
                v[i][j] = 0;
            }
        }

        // 填充网格
        // i下标表示商品索引
        for (int i = 1; i < v.length; i++) {
            // j表示背包当前容量索引
            for (int j = 1; j < v[0].length; j++) {
                if (weight[i] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    v[i][j] = Math.max(v[i - 1][j], value[i] + v[i - 1][j - weight[i]]);
                }
            }
        }

        // 打印背包
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + "    ");
            }
            System.out.println();
        }

        // 找出那些物品被装入了背包
        int i = v.length - 1;
        int j = v[0].length - 1;
        while (i > 0) {
            if (v[i][j] == v[i - 1][j]) {
                System.out.println("未选第" + i + "件物品");
            } else if (v[i][j] == value[i] + v[i - 1][j - weight[i]]) {
                System.out.println("选了第" + i + "件物品");
                j -= weight[i];
            }
            i--;
        }
    }
}
