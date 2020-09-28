package com.liushi.recursion;

/**
 * @ClassName MyEightQueens
 * @Description 博客地址 https://www.cnblogs.com/kangxinxin/p/9968524.html
 * @Author liushi
 * @Date 2020/9/28 8:27
 * @Version V1.0
 **/
public class MyEightQueens {

    //定义棋盘的规模
    private static final int N = 8;
    //判断冲突的次数哦
    public static int flag = 0;
    //用来纪录解的数量
    private static int count = 0;

    public static void main(String[] args) {

        int[][] chess = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                chess[i][j] = 0;
            }
        }

        putQueenAtRow(chess, 0);
        System.out.println("一共判断冲突的次数" + flag + "次"); // 1.5W次数
    }

    private static void putQueenAtRow(int[][] chess, int row) {
        /**
         * 如果循环到了最后一行   将结果输出
         *
         */
        if (row == N) {
            //总解法的数量+1
            count++;
            System.out.println("第" + count + "种解法:");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(chess[i][j] + " ");
                }
                System.out.println();
            }
            return;
        }

        //如果不是终止情况，创建一个新的临时棋盘，用来遍历子情况
        int[][] chessTemp = chess.clone();

        //遍历当前行的所有子情况

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                chessTemp[row][j] = 0;
            }

            chess[row][i] = 1;

            //如果当前位置可以放置皇后   就判断改行下的所有子程序
            if (isSafety(chess, row, i)) {
                putQueenAtRow(chessTemp, row + 1);
            }
        }
    }

    /**
     * 判断当前位置是否能放置皇后
     *
     * @param chess 棋盘
     * @param row
     * @param col
     * @return
     */
    private static boolean isSafety(int[][] chess, int row, int col) {
        flag++;
        //需要判断的位置是  中上  左上  右上
        int step = 1;
        while (row - step >= 0) {
            //判断中上
            if (chess[row - step][col] == 1)
                return false;
            //判断左上   考虑边界问题
            if (col - step >= 0 && chess[row - step][col - step] == 1)
                return false;
            //判断右上  考虑由边界问题
            if (col + step < N && chess[row - step][col + step] == 1)
                return false;
            step++;
        }
        return true;
    }
}