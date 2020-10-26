package com.liushi.tree;

import com.liushi.util.TreeNode;
import com.liushi.util.TreeOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName HuffmanTree
 * @Description 赫夫曼树(最优二叉树)
 * @Author liushi
 * @Date 2020/10/26 16:31
 * @Version V1.0
 **/
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        TreeNode huffmanTree = createHuffmanTree(arr);
        TreeOperation.show(huffmanTree);
        System.out.println("--------------------------");
        huffmanTree.preOrderTraverse();
    }


    /**
     * 创建哈夫曼树
     *
     * @param arr 需要常见成赫夫曼树的数组
     * @return 创建好后的赫夫曼树的root结点
     */
    public static TreeNode createHuffmanTree(int[] arr) {
        // 第一步为了操作方便
        // 1.遍历arr数组
        // 2.将arr的每个元素构建成一个TreeNode
        // 3.将TreeNode放入到ArrayList中
        List<TreeNode> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new TreeNode(i));
        }

        // list不停的合并两个结点,所以最终list中个size()==1 表示退出循环
        while (nodes.size() > 1) {
            // 排序 从小到大排序
            Collections.sort(nodes);
            // 取出根节点权值最小的两颗二叉树
            // (1) 取出权值最小的结点
            TreeNode leftNode = nodes.get(0);
            // (2) 取出权值第二小的结点
            TreeNode rightNode = nodes.get(1);

            // (3) 构建一颗新的二叉树
            TreeNode parent = new TreeNode(leftNode.val + rightNode.val);
            parent.left = leftNode;
            parent.right = rightNode;

            // (4) 从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // (5) 将parent加入nodes中
            nodes.add(parent);
        }

        // 返回赫夫曼树的root结点即可
        return nodes.get(0);
    }
}


