package com.algorithm.dac;

/**
 * @ClassName HanoiTower
 * @Description 汉诺塔
 * @Author liushi
 * @Date 2020/11/8 19:56
 * @Version V1.0
 **/
public class HanoiTower {

    private static int count;

    public static void main(String[] args) {
        hanoiTower(4, 'A', 'B', 'C');
        System.out.println("转移的次数为: " + count);
    }


    /**
     * 汉诺塔的移动方法
     * 思路:
     * ###
     * 如果是有一个盘,直接A->C
     * 如果我们有n>=2情况,我们总是看做是两个盘 1.最下边的盘,2.除了最下边,以上的所有盘
     * 1) 先把最上面的盘A->B
     * 2) 把最下边的盘A-C
     * 3) 把B塔的所有盘从B-C
     *
     * @param num
     * @param a
     * @param b
     * @param c
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        count++;
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            // 如果我们有n>=2情况,我们总是看做是两个盘 1.最下边的盘,2.上面的所有盘
            // 1) 先把最上面的盘A->B,移动过程会使用c
            hanoiTower(num - 1, a, c, b);
            // 2.把最下边的盘A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            // 3.把B它的所有盘从B-C,移动过程中使用到a塔

            hanoiTower(num - 1, b, a, c);
        }


    }
}
