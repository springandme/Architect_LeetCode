package com.liushi.tree;

/**
 * @ClassName BinaryTreeDemo
 * @Description TODO
 * @Author liushi
 * @Date 2020/10/15 9:24
 * @Version V1.0
 **/
public class BinaryTreeDemo {

    public static void main(String[] args) {
        // 先需要创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node02 = new HeroNode(3, "吴用");
        HeroNode node03 = new HeroNode(5, "卢俊义");
        HeroNode node04 = new HeroNode(6, "林冲");
        HeroNode node05 = new HeroNode(4, "鲁智深");

        // 说明,我们先手动创建该二叉树,后面我们学习递归的方式创建二叉树
        binaryTree.setRoot(root);
        root.setLeft(node02);
        root.setRight(node03);
        node02.setRight(node05);
        node03.setRight(node04);

        // 测试,
        System.out.println("前序遍历");// 1 3 4 5 6
        binaryTree.preOrder();
        System.out.println("中序遍历");// 3 4 1 5 6
        binaryTree.infixOrder();
        System.out.println("后序遍历");// 4 3 6 5 1
        binaryTree.postOrder();
    }


}

/**
 * 定义BinaryTree二叉树
 */
class BinaryTree {
    private HeroNode root;

    public BinaryTree() {
    }

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    // 前序遍历

    public void preOrder() {
        if (this.root != null) {
            root.preOrderTraverse();
        } else {
            System.out.println("二叉树为空,无法遍历!");
        }
    }
    // 中序遍历

    public void infixOrder() {
        if (this.root != null) {
            root.infixOrderTraverse();
        } else {
            System.out.println("二叉树为空,无法遍历!");
        }
    }
    // 后序遍历

    public void postOrder() {
        if (this.root != null) {
            root.postOrderTraverse();
        } else {
            System.out.println("二叉树为空,无法遍历!");
        }
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }
}

/**
 * 先创建HeroNode结点
 */
class HeroNode {

    private int no;
    private String name;
    // 默认为空
    private HeroNode left;
    // 默认为空
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }


    /**
     * 编写前序[先序]遍历的方法
     * 注意:前序最先访问的是root结点[根结点]
     */
    public void preOrderTraverse() {
        // 先输出父结点
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrderTraverse();
        }
        // 递归向右子树遍历
        if (this.right != null) {
            this.right.preOrderTraverse();
        }
    }

    /**
     * 编写中序遍历的方法
     * 注意:中序最先访问的是 最左边那个节点
     */
    public void infixOrderTraverse() {
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.infixOrderTraverse();
        }
        // 输出父结点
        System.out.println(this);
        // 递归向右子树遍历
        if (this.right != null) {
            this.right.infixOrderTraverse();
        }
    }

    /**
     * 编写后序遍历的方法
     * 注意:后序最后访问的是 是root结点
     */
    public void postOrderTraverse() {
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.postOrderTraverse();
        }
        // 递归向右子树遍历
        if (this.right != null) {
            this.right.postOrderTraverse();
        }
        // 输出父结点
        System.out.println(this);
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
