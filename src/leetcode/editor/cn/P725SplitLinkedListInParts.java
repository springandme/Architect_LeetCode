//给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。 
//
// 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。 
//
// 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。 
//
// 返回一个符合上述规则的链表的列表。 
//
// 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ] 
//
// 示例 1： 
//
// 
//输入: 
//root = [1, 2, 3], k = 5
//输出: [[1],[2],[3],[],[]]
//解释:
//输入输出各部分都应该是链表，而不是数组。
//例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.ne
//xt.next.next = null。
//第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
//最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
// 
//
// 示例 2： 
//
// 
//输入: 
//root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
//输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
//解释:
//输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
// 
//
// 
//
// 提示: 
//
// 
// root 的长度范围： [0, 1000]. 
// 输入的每个节点的大小范围：[0, 999]. 
// k 的取值范围： [1, 50]. 
// 
//
// 
// Related Topics 链表 
// 👍 105 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

//Java：分隔链表
public class P725SplitLinkedListInParts {

    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P725SplitLinkedListInParts().new Solution();
        P725SplitLinkedListInParts linkedList = new P725SplitLinkedListInParts();
        linkedList.addNode(new ListNode(1));
        linkedList.addNode(new ListNode(2));
        linkedList.addNode(new ListNode(3));
        linkedList.addNode(new ListNode(4));
        linkedList.addNode(new ListNode(5));
        // linkedList.addNode(new ListNode(6));
        // linkedList.addNode(new ListNode(7));
        // linkedList.addNode(new ListNode(8));
        // linkedList.addNode(new ListNode(9));
        // linkedList.addNode(new ListNode(10));
        ListNode[] listNodes = solution.splitListToParts(linkedList.head.next, 3);
        for (ListNode listNode : listNodes) {
            listNode.printfNode();
        }

        // TO TEST
    }

    public void addNode(ListNode node) {
        ListNode temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {

        public int getLength(ListNode head) {
            int count = 0;
            ListNode temp = head;
            while (temp != null) {
                count++;
                temp = temp.next;
            }
            return count;
        }

        public ListNode split(ListNode head, int step) {
            if (head == null) {
                return null;
            }
            ListNode cur = head;
            // 注意这里cur.next!=null有可能出现,后半段还没到规定步长,但是走完情况下
            for (int i = 0; i < step - 1 && cur.next != null; i++) {
                cur = cur.next;
            }
            // right为后半段链表头
            ListNode right = cur.next;
            // 切断前半段
            cur.next = null;
            // 返回后半段链表头
            return right;
        }

        /**
         * 思路
         * 链表会被划分为k部分
         * 每部分存放length > k ? length / k : 1;个节点
         * 因为可能不会平均分隔,所以会有length > k ? length % k : 0;个部分多出一个节点
         * 先存放多出出一个节点的部分再存放其他部分(注意部分间要断开链表)
         * ###
         * 1.遍历一遍求长度
         * 2.先存放多一个节点的部分再存放其他部分
         * 3.注意每次到部分的最后一个节点要断开链表(即指向null)
         * 4.一旦当前节点为null即可退出循环
         *
         * @param root
         * @param k
         * @return
         */
        public ListNode[] splitListToParts(ListNode root, int k) {
            ListNode[] listNodes = new ListNode[k];
            int length = getLength(root);
            // 每个部分存放几个结点
            int part = length > k ? length / k : 1;
            // 多余部分
            int remainder = length > k ? length % k : 0;
            ListNode cur = root;
            int newPart;

            for (int i = 0, count = 0; i < length; i = i + newPart, count++) {
                ListNode dummy = cur;

                // part + (i < remainder ? 1 : 0)
                newPart = part + (count < remainder ? 1 : 0);
                // 按照步长切割链表,返回切割的后半段
                cur = split(cur, newPart);
                listNodes[count] = dummy;
            }
            return listNodes;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}