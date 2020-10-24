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
        HeroNode node06 = new HeroNode(7, "武松");

        // 说明,我们先手动创建该二叉树,后面我们学习递归的方式创建二叉树
        binaryTree.setRoot(root);
        root.setLeft(node02);
        root.setRight(node03);
        node02.setRight(node05);
        node03.setLeft(node04);
        node03.setRight(node06);

        // 测试,
        System.out.println("前序遍历");// 1 3 4 5 6 7
        binaryTree.preOrder();
        System.out.println("中序遍历");// 3 4 1 6 5 7
        binaryTree.infixOrder();
        System.out.println("后序遍历");// 4 3 6 7 5 1
        binaryTree.postOrder();

        // 前序/后序/后序查找的次数根据其他元素遍历顺序的有关,比如查找node04结点 no=6
        // no=6在前序遍历顺序排在5,所以查找次数5次
        System.out.println("前序遍历查找~~~");
        HeroNode resNode = binaryTree.preOrderSearch(6);
        // 前序遍历的次数:5
        if (resNode != null) {
            System.out.printf("找到了,信息为 no=%d name=%s\n", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到no = %d 的英雄\n", 6);
        }

        System.out.println("中序遍历查找~~~");
        HeroNode resNode2 = binaryTree.infixOrderSearch(6);
        // 中序遍历的次数:4
        if (resNode2 != null) {
            System.out.printf("找到了,信息为 no=%d name=%s\n", resNode2.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到no = %d 的英雄\n", 6);
        }

        System.out.println("后序遍历查找~~~");
        HeroNode resNode3 = binaryTree.postOrderSearch(6);
        // 后序遍历的次数:3
        if (resNode3 != null) {
            System.out.printf("找到了,信息为 no=%d name=%s\n", resNode3.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到no = %d 的英雄\n", 6);
        }

        System.out.println("-------------------------------------");
        System.out.println("测试删除子结点");
        binaryTree.delNode(4);
        binaryTree.preOrder();
        System.out.println("测试删除非子结点");
        binaryTree.delNode(5);
        binaryTree.preOrder();
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

    public void delNode(int no) {
        // 首先判断这个树是否为空树
        if (root != null) {
            // 不为空树,且只有一个root结点,这里立即判断root是不是就是要删除结点
            if (root.getNo() == no) {
                root = null;
            } else {
                // 递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树,不能删除!");
        }
    }

    // 前序遍历 DLR
    public void preOrder() {
        if (this.root != null) {
            root.preOrderTraverse();
        } else {
            System.out.println("二叉树为空,无法遍历!");
        }
    }

    // 中序遍历 LDR
    public void infixOrder() {
        if (this.root != null) {
            root.infixOrderTraverse();
        } else {
            System.out.println("二叉树为空,无法遍历!");
        }
    }

    // 后序遍历 LRD
    public void postOrder() {
        if (this.root != null) {
            root.postOrderTraverse();
        } else {
            System.out.println("二叉树为空,无法遍历!");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
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

    /**
     * 前序遍历查找
     *
     * @param no 查找no
     * @return 如果找到就返回该Node, 如果没有找到返回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序查找~~");
        // 比较当前结点是不是要找的那个
        if (this.no == no) {
            return this;
        }
        // 1.则判断当前结点的左子节点是否为空,如果不为空,则递归前序查找
        // 2.如果左递归前序查找,找到结点,则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        // 说明我们左子树找到
        if (resNode != null) {
            return resNode;
        }
        // 1.左递归前序查找,找到结点,则返回,否则继续判断
        // 2.当前的结点的右子结点是否为空,如果不空,则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     *
     * @param no 查找no
     * @return 如果找到就返回该Node, 如果没有找到返回null
     */
    public HeroNode infixOrderSearch(int no) {

        // 1.先判断当前结点的左子节点是否为空,如果不为空,则递归中序查找
        // 2.如果左递归中序查找,找到结点,则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        // 说明我们左子树找到
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序查找~~");
        // 比较当前结点是不是要找的那个
        if (this.no == no) {
            return this;
        }
        // 否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历查找
     *
     * @param no 查找no
     * @return 如果找到就返回该Node, 如果没有找到返回null
     */
    public HeroNode postOrderSearch(int no) {
        // 1.先判断当前结点的左子节点是否为空,如果不为空,则递归后序查找
        // 2.如果左递归后序查找,找到结点,则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        // 说明我们左子树找到
        if (resNode != null) {
            return resNode;
        }
        // 否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序查找~~");
        // 如果左右子树都没有找到,就比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    /**
     * 要求:
     * 如果删除的节点是叶子节点,则删除叶子节点
     * 如果删除的节点是非叶子节点,则删除该子树
     * ###
     * 思路:
     * 首先先处理:
     * 考虑如果树是空树root,如果只有一个root节点,则等价将二叉树置空
     * 1.因为我们的二叉树是单向的,所有我们是判断当前结点的子结点是否是需要删除的结点,而不是去判断当前这个结点是不是需要删除结点
     * 2.如果当前的结点的左子结点不为空,并且左子结点就是要删除结点,就将this.left=null;并且就返回(结束递归删除)
     * 3.如果当前的结点的右子结点不为空,并且右子结点就是要删除结点,就将this.right=null;并且就返回(结束递归删除)
     * 4.如果第2步和第3步没有删除结点,那么就需要向左子树进行递归删除
     * 5.如果第4步也没有删除结点,则应当向右子树进行递归删除
     *
     * @param no
     */
    public void delNode(int no) {

        // 2.如果当前的结点的左子结点不为空,并且左子结点就是要删除结点,就将this.left=null;并且就返回(结束递归删除)
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        // 3.如果当前的结点的右子结点不为空,并且右子结点就是要删除结点,就将this.right=null;并且就返回(结束递归删除)
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 4.我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        // 5.我们就需要向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    /**
     * 课后作业:
     * 规定:删除非叶子节点,现在我们不希望将该非叶节点为根节点的子树删除
     * 如果非叶子节点A只有个子节点B,则子节点B代替A
     * 如果该非叶子节点A有左子节点B和右子结点C,则用左子节点B代替节点A
     */
    public void delNode2() {

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
