//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。 
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 进阶： 
//
// 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。 
//
// 
//
// 示例： 
//
// 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 8 -> 0 -> 7
// 
// Related Topics 链表 
// 👍 300 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

//Java：两数相加 II
public class P445AddTwoNumbersIi {

    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P445AddTwoNumbersIi().new Solution();
        P445AddTwoNumbersIi linkedList = new P445AddTwoNumbersIi();
        linkedList.addNode(new ListNode(1));
        linkedList.addNode(new ListNode(2));
        linkedList.addNode(new ListNode(3));
        linkedList.addNode(new ListNode(4));
        ListNode reverse = solution.reverse(linkedList.head.next);
        reverse.printfNode();
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

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            l1 = reverse(l1);
            l2 = reverse(l2);

            // 表示进位
            int carry = 0;
            ListNode dummyHead = new ListNode(-1);
            ListNode cur = dummyHead;
            while (l1 != null || l2 != null || carry > 0) {
                int val1 = l1 != null ? l1.val : 0;
                int val2 = l2 != null ? l2.val : 0;
                int sum = val1 + val2 + carry;
                carry = sum / 10;
                cur.next = new ListNode(sum % 10);
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
                cur = cur.next;
            }

            return reverse(dummyHead.next);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}