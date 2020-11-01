package com.liushi.binarysorttree;

import com.liushi.util.ConstructTree;
import com.liushi.util.TreeNode;
import com.liushi.util.TreeOperation;

/**
 * @ClassName BinarySortTreeDemo
 * @Description TODO
 * @Author liushi
 * @Date 2020/11/1 9:52
 * @Version V1.0
 **/
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        Integer[] integers = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1};
        Integer[] integers2 = {7, 3, 10, 12, 5, 1, 9, 2};
        TreeNode root = ConstructTree.createBinarySortTree(integers2);

        TreeOperation.show(root);
        System.out.println();
        root.infixOrderTraverse();


    }
}

class BinarySortTree {
    private Node root;

    /**
     * 添加结点方法
     *
     * @param node
     */
    public void add(Node node) {
        if (root == null) {
            // 如果root为空则直接让root指向node
            root = node;
        } else {
            root.addNode(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrderTraverse();
        } else {
            System.out.println("二叉排序树为空,不能遍历");
        }
    }

    /**
     * 查找结点
     *
     * @param val
     * @return
     */
    public Node search(int val) {
        if (root == null) {
            return null;
        } else {
            return root.search(val);
        }
    }

    /**
     * 查找父节点
     *
     * @param val
     * @return
     */
    public Node searchParent(int val) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(val);
        }
    }

    /**
     * 删除结点
     *
     * @param val
     */
    public void delNode(int val) {
        if (root == null) {
            return;
        } else {
            //
            Node targetNode = search(val);
        }
    }
}


class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int val) {
        this.val = val;
    }


    public void infixOrderTraverse() {
        if (this.left != null) {
            this.left.infixOrderTraverse();
        }
        System.out.print(this.val + " ");
        if (this.right != null) {
            this.right.infixOrderTraverse();
        }
    }

    /**
     * 添加结点的方法
     * 递归的形式添加结点,注意需要添加二叉排序树要求
     *
     * @param node
     */
    public void addNode(Node node) {
        if (node == null) {
            return;
        }

        // 判断传入的结点的值,和当前子树的跟结点的值关系
        if (this.val > node.val) {
            // 如果当前结点左子树结点为null
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归的向左子树添加
                this.left.addNode(node);
            }
        } else {
            // 添加的结点的值大于或等于当前结点的值,就放在当前结点的右边
            if (this.right == null) {
                // 如果当前结点右子树结点为null
                this.right = node;
            } else {
                // 否则递归的向右子树添加
                this.right.addNode(node);
            }
        }
    }

    /**
     * 查找要删除的结点
     *
     * @param val 希望删除的结点的值
     * @return 如果找到返回该结点, 否则返回null
     */
    public Node search(int val) {
        // 如果找到就是该节点
        if (val == this.val) {
            return this;
            // 如果查找的值小于当前结点,向左子树递归查找
        } else if (val < this.val) {
            // 如果左子节点为空,那么直接返回null
            if (this.left == null) {
                return null;
            }
            return this.left.search(val);
            // 如果查找的值大于或等于当前结点,向左子树递归查找
        } else {
            // 如果右子节点为空,那么直接返回null
            if (this.right == null) {
                return null;
            }
            return this.right.search(val);
        }
    }

    /**
     * 查找要删除结点的父结点
     *
     * @param val 要找到结点的值
     * @return 返回的是要删除的结点的父结点, 如果没有就返回null
     */
    public Node searchParent(int val) {
        // 如果当前结点就是要删除的结点的父结点,就返回
        if ((this.left != null && this.left.val == val)
                || (this.right != null && this.right.val == val)) {
            return this;
        } else {
            // 如果查找的值小于当前结点的值,并且当前结点的左子节点不为空
            if (val < this.val && this.left != null) {
                // 向左子树查找
                return this.left.searchParent(val);
                // 如果查找的值大于或等于当前结点的值,并且当前结点的左子节点不为空
            } else if (val >= this.val && this.right != null) {
                // 向右子树查找
                return this.right.searchParent(val);
            } else {
                // 没有找到父结点,比如删除根节点root,它就是没有父节点的
                return null;
            }
        }
    }
}
