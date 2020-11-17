//给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。 
//
// 示例： 
//
// 给定一个链表: 1->2->3->4->5, 和 n = 2.
//
//当删除了倒数第二个节点后，链表变为 1->2->3->5.
// 
//
// 说明： 
//
// 给定的 n 保证是有效的。 
//
// 进阶： 
//
// 你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针 
// 👍 1104 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

import java.util.Stack;

//Java：删除链表的倒数第N个节点
public class P19RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new P19RemoveNthNodeFromEndOfList().new Solution();
        // TO TEST
    }


    /**
     * 思路没问题,但就是遇到特例了,需要特别处理 必须穿过来链表只有一个元素,且n=1
     * 可以优化代码
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode temp = head;
        int linkLength = this.getLinkLength(head);
        if (linkLength == 1 && n == 1) {
            return null;
        }
        int reallyDeleteIndex = linkLength - n + 1;
        int count = 1;
        while (temp != null) {
            if (reallyDeleteIndex == 1) {
                temp.val = temp.next.val;
                temp.next = temp.next.next;
                break;
            }
            if (reallyDeleteIndex - 1 == count) {
                temp.next = temp.next.next;
                break;
            }
            count++;
            temp = temp.next;
        }
        return head;
    }

    public int getLinkLength(ListNode head) {
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
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

        public int getLinkLength(ListNode head) {
            int count = 0;
            ListNode temp = head;
            while (temp != null) {
                count++;
                temp = temp.next;
            }
            return count;
        }


        /**
         * 方法一：计算链表长度 -->暴力求解  时间复杂度O(2n)~O(n)
         * 时间复杂度：O(L)，其中 L 是链表的长度。
         * 空间复杂度：O(1)
         * 在本题中，如果我们要删除节点 y，我们需要知道节点 y 的前驱节点 x，并将 x 的指针指向 y
         * 的后继节点。但由于头节点不存在前驱节点，因此我们需要在删除头节点时进行特殊判断。
         * 但如果我们添加了哑节点，那么头节点的前驱节点就是哑节点本身，此时我们就只需要考虑通用的情况即可
         *
         * @param head
         * @param n
         * @return
         */
        public ListNode removeNthFromEnd2(ListNode head, int n) {
            int linkLength = getLinkLength(head);
            int reallyIndex = linkLength - n + 1;
            // dummyNode称之为哑结点
            ListNode dummyNode = new ListNode(-1, head);
            ListNode cur = dummyNode;
            // 循环退出条件是 reallyIndex-1, 表示cur退出时候,指向的是需要删除结点y的前驱结点x
            for (int i = 0; i < reallyIndex - 1; i++) {
                cur = cur.next;
            }
            // 删除y的结点,就只要将前驱结点x的指针指向y的后继结点
            cur.next = cur.next.next;

            return dummyNode.next;
        }

        /**
         * 方法2:栈
         * 时间复杂度：O(L)，其中 L 是链表的长度。
         * 空间复杂度：O(L)，其中 L 是链表的长度。主要为栈的开销。
         * 思路与算法
         * 我们也可以在遍历链表的同时将所有节点依次入栈。根据栈「先进后出」的原则，
         * 我们弹出栈的第 n 个节点就是需要删除的节点，并且目前栈顶的节点就是待删除节点的前驱节点。
         * 这样一来，删除操作就变得十分方便了。
         *
         * @param head
         * @param n
         * @return
         */
        public ListNode removeNthFromEnd3(ListNode head, int n) {
            ListNode dummyNode = new ListNode(-1, head);
            Stack<ListNode> stack = new Stack<>();
            ListNode cur = dummyNode;
            while (cur != null) {
                stack.push(cur);
                cur = cur.next;
            }
            for (int i = 0; i < n; i++) {
                stack.pop();
            }
            // 循环结束后,栈顶元素就是需要删除y结点的前驱结点x
            ListNode prev = stack.pop();
            prev.next = prev.next.next;
            return dummyNode.next;
        }


        /**
         * 方法三：快慢指针 -->一次遍历
         * 由于我们需要找到倒数第 n 个节点，因此我们可以使用两个指针 first 和 second 同时对链表进行遍历
         * 并且 first 比 second 超前 n个节点。当 first 遍历到链表的末尾时，second 就恰好处于倒数第 n 个节点。
         *
         * @param head
         * @param n
         * @return
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummyNode = new ListNode(-1, head);
            ListNode second = dummyNode;
            ListNode first = dummyNode.next;
            for (int i = 0; i < n; i++) {
                first = first.next;
            }
            while (first != null) {
                first = first.next;
                second = second.next;
            }
            second.next = second.next.next;

            return dummyNode.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}