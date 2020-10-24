package com.liushi.tree;

import com.liushi.util.ConstructTree;
import com.liushi.util.TreeNode;
import com.liushi.util.TreeOperation;

/**
 * @ClassName ArrBinaryTreeDemo
 * @Description TODO
 * @Author liushi
 * @Date 2020/10/24 15:22
 * @Version V1.0
 **/
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        // 创建一个ArrBinaryTree
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        TreeNode constructTree = ConstructTree.constructTree(arr);
        TreeOperation.show(constructTree);
        System.out.println("顺序二叉树的前序遍历");
        tree.preOrder();
        System.out.println("\n顺序二叉树的中序遍历");
        tree.infixOrder(0);
        System.out.println("\n顺序二叉树的后序遍历");
        tree.postOrder(0);
    }
}

/**
 * 编写一个ArrayBinaryTree,实现顺序存储二叉树遍历
 */
class ArrBinaryTree {
    // 存储数据结点的数组
    private Integer[] arr;

    public ArrBinaryTree(Integer[] arr) {
        this.arr = arr;
    }

    // 重载preOrder方法
    public void preOrder() {
        preOrder(0);
    }

    /**
     * 编写一个方法,完成顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        // 如果数组为空,或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照二叉树的前序遍历");
            return;
        }
        // 输出当前这个元素
        System.out.print(arr[index] + " ");

        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    /**
     * 编写一个方法,完成顺序存储二叉树的中序遍历
     *
     * @param index 数组的下标
     */
    public void infixOrder(int index) {
        // 如果数组为空,或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照二叉树的前序遍历");
            return;
        }

        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            infixOrder(index * 2 + 1);
        }
        // 输出当前这个元素
        System.out.print(arr[index] + " ");
        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    /**
     * 编写一个方法,完成顺序存储二叉树的后序遍历
     *
     * @param index 数组的下标
     */
    public void postOrder(int index) {
        // 如果数组为空,或者arr.length=0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照二叉树的前序遍历");
            return;
        }

        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        // 输出当前这个元素
        System.out.print(arr[index] + " ");
    }
}
