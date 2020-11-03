package com.liushi.avl;

/**
 * @ClassName AvlTree
 * @Description AVLTree是BST, 所有节点必须是可比较
 * @Author liushi
 * @Date 2020/11/3 14:46
 * @Version V1.0
 **/
public class AvlTree<E extends Comparable<E>> {

    private Node root;
    private int size;

    public static void main(String[] args) {
        AvlTree<Integer> avlTree = new AvlTree<>();
        Integer[] integers = {3, 2, 1, 4, 5, 6, 7, 10, 9, 8};

        for (Integer integer : integers) {
            System.out.println("当前插入的结点是:= " + integer);
            avlTree.add(integer);
            // System.out.println("此时这个树是否为avl树" + avlTree.isBalances());
            avlTree.show();
        }
    }

    /**
     * 获取某一节点的高度
     *
     * @return 高度
     */
    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断树是否为平衡二叉树
     *
     * @param node
     * @return 返回的是左子树高度减去右子树的高度
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 判断树是否为平衡二叉树
     *
     * @return
     */
    public boolean isBalances() {
        return isBalances(root);
    }

    public boolean isBalances(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = Math.abs(this.getBalanceFactor(node));
        if (balanceFactor > 1) {
            return false;
        }
        return isBalances(node.left) && isBalances(node.right);
    }

    /**
     * 右旋[左边树高度比右边高]
     *
     * @param y
     * @return
     */
    public Node rightRotate(Node y) {
        Node x = y.left;
        Node t3 = x.right;
        x.right = y;
        y.left = t3;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 左旋[右边树高度比左边高]
     *
     * @param y
     * @return
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node t3 = x.left;
        x.left = y;
        y.right = t3;
        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 向二分搜索树中添加新的元素{key,value}
     *
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根的二分搜索树中插入元素(key,value),递归算法
     *
     * @param node 根结点
     * @param e    返回插入新结点后二分搜索树的跟
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        // 说明添加元素e的值小于根结点,放在根结点的左边
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
            // 说明添加元素e的值大于或等于根结点,放在根结点的左边
        } else if (e.compareTo(node.e) >= 0) {
            node.right = add(node.right, e);
        }
        // 更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        // balanceFactor > 1 表示左边树高度大于右边树高度 --> 右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) > 0) {
            // 右旋
            return rightRotate(node);
        }
        if (balanceFactor < -1 && getBalanceFactor(node.right) < 0) {
            // 左旋
            return leftRotate(node);
        }
        // L-R
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            // 先左旋,在右旋
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // R-L
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            // 先右旋,再左旋
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 用于获得树的层数
    public int getTreeDepth(Node root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    private void writeArray(Node currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.e);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    public void show() {
        show(this.root);
    }

    public void show(Node root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * 使用内部类方式定义一个Node
     */
    private class Node {
        public E e;
        public Node left;
        public Node right;
        public int height;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }
}
