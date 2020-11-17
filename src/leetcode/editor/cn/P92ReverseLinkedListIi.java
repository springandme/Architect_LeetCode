//反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。 
//
// 说明: 
//1 ≤ m ≤ n ≤ 链表长度。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL, m = 2, n = 4
//输出: 1->4->3->2->5->NULL 
// Related Topics 链表 
// 👍 575 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

//Java：反转链表 II
public class P92ReverseLinkedListIi {

    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P92ReverseLinkedListIi().new Solution();
        P92ReverseLinkedListIi linkedList = new P92ReverseLinkedListIi();
        linkedList.addNode(new ListNode(1));
        linkedList.addNode(new ListNode(2));
        linkedList.addNode(new ListNode(3));
        linkedList.addNode(new ListNode(4));
        linkedList.addNode(new ListNode(5));
        // ListNode reverse = solution.reverse(linkedList.head.next);
        ListNode reverseBetween = solution.reverseBetween(linkedList.head.next, 1, 1);
        reverseBetween.printfNode();
        // TO TEST
    }

    public void addNode(ListNode node) {
        ListNode temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(-1, head);
        ListNode start = dummyHead;
        for (int i = 0; i < m; i++) {
            start = start.next;
        }
        ListNode end = start;
        for (int i = 0; i < n - m; i++) {
            end = end.next;
        }
        end.next = null;
        ListNode reverse = reverse(start);
        ListNode start2 = dummyHead;
        for (int i = 0; i < m - 1; i++) {
            start2 = start2.next;
        }
        ListNode end2 = start2;
        for (int i = 0; i < n - m + 1; i++) {
            end2 = end2.next;
        }
        reverse.next = end2;
        start2.next = reverse;

        return dummyHead.next;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1, head);
        ListNode prev = dummyHead.next;
        ListNode pCur = prev.next;
        while (pCur != null) {
            prev.next = pCur.next;
            pCur.next = dummyHead.next;
            dummyHead.next = pCur;
            pCur = prev.next;
        }
        return dummyHead.next;
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

        /**
         * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
         * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
         * 输出: 1->4->3->2->5->NULL
         * 说明:1 ≤ m ≤ n ≤ 链表长度。
         *
         * @param head
         * @param m
         * @param n
         * @return
         */
        public ListNode reverseBetween(ListNode head, int m, int n) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummyHead = new ListNode(-1, head);
            Deque<ListNode> stack = new ArrayDeque<>();
            ListNode start = dummyHead;
            for (int i = 0; i < m - 1; i++) {
                start = start.next;
            }
            ListNode end = start.next;
            for (int i = 0; i < n - m + 1; i++) {
                stack.push(end);
                end = end.next;
            }
            while (!stack.isEmpty()) {
                start.next = stack.pop();
                start = start.next;
            }
            start.next = end;


            return dummyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}