package com.liushi.recursion;

/**
 * @ClassName Maze
 * @Description 迷宫问题
 * @Author liushi
 * @Date 2020/9/26 18:34
 * @Version V1.0
 **/
public class Maze {
    // 地图长度  [长度按照从上到下]
    public static int length = 8;
    // 地图宽度  [宽度按照从左到右]
    public static int width = 7;
    // 创建一个二维数组,模拟迷宫地图
    public static int[][] map = new int[length][width];

    public static void main(String[] args) {
        // 把创建地图封装为一个方法
        createMap();
        setWay(map, 1, 1);
        // 输出新的地图,小球走过,并表示过的地图情况

        System.out.println("小球走过,并标记过的地图的情况");
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void createMap() {
        // 使用1 表示墙
        // 先把上下全部置为1
        for (int i = 0; i < length - 1; i++) {
            map[0][i] = 1;
            map[length - 1][i] = 1;
        }
        // 在把左右全部置为1
        for (int i = 0; i < width; i++) {
            map[i][0] = 1;
            map[i][width - 1] = 1;
        }
        // 设置挡板,1 表示
        map[3][1] = 1;
        map[3][2] = 1;
        // 遍历测试一下
        System.out.println("地图的情况");
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * 使用递归回溯来给小球找路
     * 说明:
     * 1. i,j 表示从地图的哪个位置开始出发,一般从(1,1)出发
     * 2. 如果小球能到map[6][5]位置,则说明通路找到
     * 3. 约定: 当map[i][j]为0表示该点没有走过,1表示墙,2表示通路可以走,3表示该点已经走过,但是走不通
     * 4. 在走迷宫时,需要确定一个策略(方法), 下->右->上->左,如果该点走不通,再回溯
     *
     * @param map 地图
     * @param i   x坐标 从哪个位置开始
     * @param j   y坐标
     * @return 如果能找到通路, 就返回true, 否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        // 表示通路已经找到
        if (map[6][5] == 2) {
            return true;
        } else {
            // 如果当前这个点还没有走过
            if (map[i][j] == 0) {
                // 按照策略走,下->右->上->左 走
                map[i][j] = 2; // 假定该点是可以走通的
                // 向下走
                if (setWay(map, i + 1, j)) {
                    return true;
                    // 向右走
                } else if (setWay(map, i, j + 1)) {
                    return true;
                    // 向上走
                } else if (setWay(map, i - 1, j)) {
                    return true;
                    // 向左走
                } else if (setWay(map, i, j - 1)) {
                    return true;
                    // 说明该点是走不通的,是死路
                } else {
                    map[i][j] = 3;
                    return false;
                }
                // System.out.println();
            } else {
                // 如果map[i][j] != 0,可能是1, 2, 3
                return false;
            }
        }
    }
}
