//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
// Related Topics 链表 
// 👍 735 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

//Java：两两交换链表中的节点
public class P24SwapNodesInPairs {

    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P24SwapNodesInPairs().new Solution();
        P24SwapNodesInPairs linkedList = new P24SwapNodesInPairs();
        linkedList.addNode(new ListNode(1));
        linkedList.addNode(new ListNode(2));
        linkedList.addNode(new ListNode(3));
        linkedList.addNode(new ListNode(4));
        ListNode listNode = solution.swapPairs2(linkedList.head.next);
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
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {

        /**
         * 两两交换其中相邻的节点，并返回交换后的链表。
         * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
         * 输入：head = [1,2,3,4]
         * 输出：[2,1,4,3]
         *
         * @param head
         * @return
         */
        public ListNode swapPairs2(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode dummyHead = new ListNode(-1, head);
            ListNode cur = dummyHead;
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && slow != null) {
                cur.next = fast;
                slow.next = fast.next;
                fast.next = slow;

                cur = slow;
                slow = slow.next;
                if (slow != null) {
                    fast = slow.next;
                }
            }

            return dummyHead.next;
        }

        /**
         * 利用一个stack,然后不断迭代链表,每次取出两个节点放入stack中,再从stack中拿出两个节点
         * 借助stack先进后出的特点,放进去的时候是1,2. 拿出来的时候就是2,1两个结点了
         * 再把这两个结点串联起来,重复这个逻辑遍历整个链表,就是可以做到两两反转的效果了
         *
         * @param head
         * @return
         */
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            // 用stack保存每次迭代的两个结点
            Deque<ListNode> stack = new ArrayDeque<>();
            ListNode dummyHead = new ListNode(-1, head);
            ListNode cur = dummyHead.next;
            // head指向新的p结点,函数结束时返回head.next即可
            head = dummyHead;
            while (cur != null && cur.next != null) {
                // 将两个结点放入stack中
                stack.push(cur);
                stack.push(cur.next);
                // 当前结点往前走两步
                cur = cur.next.next;
                // 从stack中弹出两个结点,然后用p结点指向新弹出的两个结点
                head.next = stack.pop();
                head = head.next;
                head.next = stack.pop();
                head = head.next;
            }
            // 注意边界条件,当链表长度是奇数时,cur就不为空
            if (cur != null) {
                head.next = cur;
            } else {
                head.next = null;
            }
            return dummyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}