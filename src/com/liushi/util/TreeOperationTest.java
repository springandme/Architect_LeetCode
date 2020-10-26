package com.liushi.util;

/**
 * @ClassName TreeOperationTest
 * @Description TODO
 * @Author liushi
 * @Date 2020/10/24 10:56
 * @Version V1.0
 **/
public class TreeOperationTest {
    public static void main(String[] args) {
        // 根据给定的数组创建一棵树
        // 2,3,3,4,1,1,7,8,9,10,11,2,2,2,2
        // TreeNode root = ConstructTree.constructTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        // TreeNode root = ConstructTree.constructTree(new Integer[]{2, 3, 3, 4, 1, 1, 7, 8, 9, 10, 11, 2, 2, 2, 2});
        TreeNode root = ConstructTree.constructTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1});
        // 将刚刚创建的树打印出来
        TreeOperation.show(root);
        root.preOrderTraverse();
    }
}
