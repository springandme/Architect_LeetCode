package com.liushi.util;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @ClassName ConstructTree
 * @Description 根据数组创建一个二叉树
 * @Author liushi
 * @Date 2020/10/24 10:54
 * @Version V1.0
 **/
public class ConstructTree {

    // public static TreeNode root;

    /**
     * 核心思想是在每一层,用一个队列queue来存储该层的所有节点,
     * 然后用父结点的数量的两倍遍历输入的数组(从上一层结束的地方开始)
     * 并从队列中取出(位于上一层的)对应父结点(此时已从队列中删去,所以用的是poll()而不是peek()[检索队列第一个元素,但不删除]),
     * 对于每一个值,创建相应的子结点链接父结点的同时,并把左右子结点加入到队列中,依次循环,直到遍历完整个数组
     *
     * @param arr
     * @return
     */
    public static TreeNode constructTree(Integer[] arr) {
        if (arr == null) {
            return null;
        }
        // 根据数组第一元素创建一个根节点
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        // 记录当前行结点个数量 (是上一行中非空结点的数量乘以2)
        int lineNodeNum = 2;
        // 记录当前行中数字在数组中的开始位置
        int startIndex = 1;
        // 记录数组中剩余的元素数量
        int restLength = arr.length - 1;
        TreeNode cur;

        while (restLength > 0) {

            for (int i = startIndex; i < startIndex + lineNodeNum; i = i + 2) {
                // 说明已经把arr中的数字用完,此时应当停止遍历,并直接返回root
                if (i == arr.length) {
                    return root;
                }
                // 这一步已经将变量cur和变量root同时指向一个对象堆内存空间
                cur = queue.poll();
                if (arr[i] != null) {
                    cur.left = new TreeNode(arr[i]);
                    queue.offer(cur.left);
                }
                // 同上,说明已经把arr中的数字用完,此时应当停止遍历,并直接返回root
                if (i + 1 == arr.length) {
                    return root;
                }
                if (arr[i + 1] != null) {
                    cur.right = new TreeNode(arr[i + 1]);
                    queue.offer(cur.right);
                }
            }
            startIndex += lineNodeNum;
            restLength -= lineNodeNum;
            lineNodeNum = queue.size() * 2;
        }

        return root;
    }

    /**
     * 根据数组创建一个二叉排序树
     *
     * @param arr 一个数组
     * @return 已经排好序二叉排序树
     */
    public static TreeNode createBinarySortTree(Integer[] arr) {
        int index = 0;
        TreeNode root = null;
        // 这个循环找到数组中第一个不为null的元素
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                root = new TreeNode(arr[i]);
                index = i;
                break;
            }
        }
        for (int i = index + 1; i < arr.length; i++) {
            if (arr[i] != null) {
                root.addNode(new TreeNode(arr[i]));
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] nums = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1};
        TreeNode root = ConstructTree.constructTree(nums);
        TreeOperation.show(root);
        // System.out.println(root);
        // 前序遍历
        System.out.println("这个树的前序遍历结果为");
        root.preOrderTraverse();

    }
}
