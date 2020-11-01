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
    public void addNode(TreeNode node) {
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
    public TreeNode search(int val) {
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
    public TreeNode searchParent(int val) {
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

    /**
     * 删除结点
     *
     * @param val
     */
    public void delNode(int val) {
        TreeNode root = this.getRoot();
        if (getRoot() == null) {
            return;
        } else {
            // 1.需要先找到需要删除的节点 targetNode
            TreeNode targetNode = search(val);
            // 如果没有找到要删除的结点
            if (targetNode == null) {
                return;
            }
            // 如果我们发现当前这颗二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 去找targetNode的父结点
            TreeNode parent = searchParent(val);

            // 第一种情况：如果要删除的结点是叶子结点
            if (targetNode.right == null && targetNode.left == null) {
                // 判断targetNode是父结点的左子结点，还是右子结点
                // 说明是左子结点
                if (parent.left != null && parent.left.val == targetNode.val) {
                    parent.left = null;
                    // 说明是右子结点
                } else if (parent.right != null && parent.right.val == targetNode.val) {
                    parent.right = null;
                }
                // 第三种情况：删除的结点是有两颗子树的结点
            } else if (targetNode.left != null && targetNode.right != null) {
                // 思路1:从target的右子树找到最小值
//                int minVal = delRightTreeMin(targetNode);
//                targetNode.val = minVal;
                // 思路2:从target的左子树找到最大值
                int maxVal = delLeftTreeMax(targetNode);
                targetNode.val = maxVal;

                // 剩余下面的判断条件就是 就是第二种情况：
                // 如果删除的结点只有一颗子树的结点
            } else {
                // 如果要删除的targetNode结点有左结点
                if (targetNode.left != null) {
                    // 如果targetNode是parent的左子结点
                    if (targetNode.val == parent.left.val) {
                        parent.left = targetNode.left;
                    } else {  // 如果targetNode是parent的右子结点
                        parent.right = targetNode.left;
                    }
                } else { // 如果删除的targetNode结点有右结点
                    // 如果targetNode是parent的左子结点
                    if (targetNode.val == parent.left.val) {
                        parent.left = targetNode.right;
                    } else { // 如果targetNode是parent的右子结点
                        parent.right = targetNode.right;
                    }
                }
            }

            // 自己的写法
            // 第二种情况，如果删除的结点只有一颗子树的结点
            // targetNode是parent的左子树
            /*if (parent.left != null && parent.left.val == targetNode.val) {
                // 说明是左子结点
                if (targetNode.left != null && targetNode.right == null) {
                    parent.left = targetNode.left;
                } else if (targetNode.right != null && targetNode.left == null) {
                    parent.left = targetNode.right;
                }
                // targetNode是parent的右子树
            } else if (parent.right != null && parent.right.val == targetNode.val) {
                // 说明是右子结点
                if (targetNode.left != null && targetNode.right == null) {
                    parent.right = targetNode.left;
                } else if (targetNode.right != null && targetNode.left == null) {
                    parent.right = targetNode.right;
                }
            }*/
        }
    }

    /**
     * 思路：从target的右子树找到最小值
     * 1.返回的以node为根结点的二叉排序树的最小结点的值
     * 2.删除node为根结点的二叉排序树的最小结点
     *
     * @param node 传入的结点（当做二叉排序树的根节点）
     * @return 返回的以node为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(TreeNode node) {
        TreeNode target = node.right;

        // 循环的查找左子结点，就会找到最小值，直到结点为空
        while (target.left != null) {
            target = target.left;
        }
        // 循环结束后，这时候target就只指向最小结点，而且这个结点还是一个叶子结点
        // 删除这个最小结点[它是一个叶子结点，可以直接删除]
        delNode(target.val);
        // 返回最小结点的值
        return target.val;
    }

    /**
     * 思路：从target的左子树找到最大值
     * 1.返回的以node为根结点的二叉排序树的最大结点的值
     * 2.删除node为根结点的二叉排序树的最大结点
     *
     * @param node 传入的结点（当做二叉排序树的根节点）
     * @return 返回的以node为根结点的二叉排序树的最大结点的值
     */
    public int delLeftTreeMax(TreeNode node) {
        TreeNode target = node.left;

        // 循环的查找右子结点，就会找到最大值，直到结点为空
        while (target.right != null) {
            target = target.right;
        }
        // 循环结束后，这时候target就指向最大结点，而且这个结点还是一个叶子结点
        // 删除这个最大结点[它是一个叶子结点，可以直接删除]
        delNode(target.val);
        // 返回最大结点的值
        return target.val;
    }

    public TreeNode getRoot() {
        return this;
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
