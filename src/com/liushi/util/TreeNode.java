package com.liushi.util;

/**
 * @ClassName TreeNode
 * @Description LeetCode上所用的树的结构
 * @Author liushi
 * @Date 2020/10/24 10:52
 * @Version V1.0
 **/
public class TreeNode implements Comparable<TreeNode> {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int x) {
        val = x;
    }

    public void preOrderTraverse() {
        System.out.print(this.val + " ");
        if (this.left != null) {
            this.left.preOrderTraverse();
        }
        if (this.right != null) {
            this.right.preOrderTraverse();
        }
    }

    @Override
    public int compareTo(TreeNode o) {
        // 表示从小到大排序
        return this.val - o.val;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}
