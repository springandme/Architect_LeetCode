//给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。 
//
// 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。 
//
// 示例 1: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 1->3->5->2->4->NULL
// 
//
// 示例 2: 
//
// 输入: 2->1->3->5->6->4->7->NULL 
//输出: 2->3->6->7->1->5->4->NULL 
//
// 说明: 
//
// 
// 应当保持奇数节点和偶数节点的相对顺序。 
// 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。 
// 
// Related Topics 链表 
// 👍 339 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

//Java：奇偶链表
public class P328OddEvenLinkedList {

    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P328OddEvenLinkedList().new Solution();
        P328OddEvenLinkedList linkedList = new P328OddEvenLinkedList();
        linkedList.addNode(new ListNode(1));
        linkedList.addNode(new ListNode(2));
        linkedList.addNode(new ListNode(3));
        linkedList.addNode(new ListNode(4));
        linkedList.addNode(new ListNode(5));
        ListNode oddEvenList = solution.oddEvenList(linkedList.head.next);
        oddEvenList.printfNode();
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
         * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
         * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
         * 输入: 2->1->3->5->6->4->7->NULL
         * 输出: 2->3->6->7->1->5->4->NULL
         *
         * @param head
         * @return
         */
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode oddHead = new ListNode(-1);
            ListNode evenHead = new ListNode(-1);
            ListNode curOdd = oddHead;
            ListNode curEven = evenHead;
            ListNode cur = head;
            int count = 1;
            while (cur != null) {
                // 偶数
                if (count % 2 == 0) {
                    curEven.next = cur;
                    curEven = curEven.next;
                } else {
                    curOdd.next = cur;
                    curOdd = curOdd.next;
                }
                cur = cur.next;
                count++;
            }
            curEven.next = null;
            curOdd.next = evenHead.next;
            return oddHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}