//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1324 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

//Java：反转链表
public class P206ReverseLinkedList {
    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P206ReverseLinkedList().new Solution();

        P206ReverseLinkedList link = new P206ReverseLinkedList();
        link.addNode(new ListNode(1));
        link.addNode(new ListNode(2));
        link.addNode(new ListNode(3));
        link.addNode(new ListNode(4));
        link.addNode(new ListNode(5));
        System.out.println("反转之前");
        link.head.next.printfNode();
        System.out.println("反转之后~");
        ListNode listNode = solution.reverseList(link.head.next);
        listNode.printfNode();

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
        public ListNode reverseList(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode dummyHead = new ListNode(-1);
            dummyHead.next = head;
            ListNode prev = head;
            ListNode pCur = prev.next;
            while (pCur != null) {
                prev.next = pCur.next;
                pCur.next = dummyHead.next;
                dummyHead.next = pCur;
                pCur = prev.next;
            }
            return dummyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}