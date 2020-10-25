package com.liushi.tree;

/**
 * @ClassName ThreadedBinaryTreeDemo
 * @Description TODO
 * @Author liushi
 * @Date 2020/10/25 10:16
 * @Version V1.0
 **/
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        // 测试一把中序线索二叉树的功能
        TreeNode root = new TreeNode(1);
        TreeNode node02 = new TreeNode(3);
        TreeNode node03 = new TreeNode(6);
        TreeNode node04 = new TreeNode(8);
        TreeNode node05 = new TreeNode(10);
        TreeNode node06 = new TreeNode(14);

        // 二叉树,后面我们要递归递归创建,现在简单创建使用手动创建
        root.setLeft(node02);
        root.setRight(node03);
        node02.setLeft(node04);
        node02.setRight(node05);
        node03.setLeft(node06);

        // 测试中序线索化
        ConstructTree binaryTree = new ConstructTree(root);
        binaryTree.threadedNodes();

        // 测试:以10号结点测试
        TreeNode leftNode = node05.getLeft();
        System.out.println("10号结点的前驱结点是 = " + leftNode);
        TreeNode rightNode = node05.getRight();
        System.out.println("10号结点的后继结点是 = " + rightNode);

        // 当线索化二叉树后,之前的前序/中序/后序遍历不能再使用了!!!
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        binaryTree.threadedList();
        //

    }
}

/**
 * 构造二叉树,实现了线索化功能的二叉树
 */
class ConstructTree {
    private TreeNode root;

    // 为了实现线索化,需要创建要给指向当前结点的前驱结点的指针
    // 在递归进行线索化时,pre总是保留前一个结点
    private TreeNode pre = null;


    public ConstructTree() {
    }

    public ConstructTree(TreeNode root) {
        this.root = root;
    }


    // 重装一把threadedNodes方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * 编写对二叉树进行中序线索化的方法
     *
     * @param node 就是当前需要线索化的结点
     */
    public void threadedNodes(TreeNode node) {
        // 如果node==null,不能线索化
        if (node == null) {
            return;
        }

        // 1.先线索左子树
        threadedNodes(node.getLeft());
        // 2.线索化当前结点[有难度]

        // 处理当前结点的前驱结点
        // 以8结点为例
        // 8结点的left = null ,leftType = 1
        if (node.getLeft() == null) {
            // 让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            // 修改当前结点的左指针类型,指向前驱结点
            node.setLeftType(1);
        }
        // node的下一次结点[当前node=8,下一次结点是3], node.val=3 处理上一次结点的后继结点
        if (pre != null && pre.getRight() == null) {
            // 让前驱结点的右指针指向当前结点
            pre.setRight(node);
            // 修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        // !!! 每处理一个结点后,让当前结点是下一个结点的前驱结点
        pre = node;

        // 3.右线索化右子树
        threadedNodes(node.getRight());
    }

    /**
     * 遍历中序线索化二叉树的方法
     */
    public void threadedList() {
        // 定义一个变量,存储当前遍历的结点,从root开始
        TreeNode node = this.root;
        while (node != null) {
            // 循环的找到leftType == 1的结点,第一个找到就是8结点
            // 后面随着遍历而变化,因为当leftType==1时,说明该结点是按照线索化处理后有效结点
            while (node.getLeftType() == 0) {
                // 中序二叉树的特点,第一个遍历的结点是树的最左边的子结点!!!!,所以一直循环左子树就能实现
                node = node.getLeft();
            }
            // 打印当前结点
            System.out.print(node.getVal() + " ");

            // 如果当前结点的右指针指向是后继结点,就一直输出
            while (node.getRightType() == 1) {
                // 获取到当前结点的后继结点
                node = node.getRight();
                System.out.print(node.getVal() + " ");
            }
            // 退出这个循环,说明碰到了node结点rightType == 0,替换这个
            node = node.getRight();
        }
    }


    public void infixOrderTraverse() {
        if (root != null) {
            root.infixOrderTraverse();
        } else {
            System.out.println("二叉树为空,无法遍历!");
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getPre() {
        return pre;
    }

    public void setPre(TreeNode pre) {
        this.pre = pre;
    }
}

class TreeNode {
    private int val;
    // 默认为空
    private TreeNode left;
    // 默认为空
    private TreeNode right;
    // 说明:
    // 1.如果leftType == 0  left指针表示指向的是左子树,如果1则表示指向前驱结点
    // 2.如果rightType == 0  right指针表示指向的是右子树,如果1则表示指向后继结点
    // 注意一下 int基本类型在java中有默认值0
    private int rightType;
    private int leftType;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    /**
     * 中序遍历
     */
    public void infixOrderTraverse() {
        if (this.left != null) {
            this.left.infixOrderTraverse();
        }
        System.out.println(this.val + " ");
        if (this.right != null) {
            this.right.infixOrderTraverse();
        }
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}


