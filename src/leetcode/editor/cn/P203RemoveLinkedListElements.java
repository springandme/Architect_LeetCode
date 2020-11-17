//删除链表中等于给定值 val 的所有节点。 
//
// 示例: 
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
// 
// Related Topics 链表 
// 👍 479 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

//Java：移除链表元素
public class P203RemoveLinkedListElements {
    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P203RemoveLinkedListElements().new Solution();

        P203RemoveLinkedListElements linkedList = new P203RemoveLinkedListElements();
        linkedList.addNode(new ListNode(1));
        linkedList.addNode(new ListNode(1));
        // linkedList.addNode(new ListNode(2));
        // linkedList.addNode(new ListNode(6));
        // linkedList.addNode(new ListNode(3));
        // linkedList.addNode(new ListNode(4));
        // linkedList.addNode(new ListNode(5));
        // linkedList.addNode(new ListNode(6));
        ListNode listNode = solution.removeElements(linkedList.head.next, 1);

        listNode.printfNode();
        // TO TEST
    }

    public void printLink() {
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        ListNode temp = head.next;
        while (temp != null) {
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        System.out.println();
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
        public ListNode removeElements(ListNode head, int val) {
            if (head == null) {
                return null;
            }
            ListNode dummyHead = new ListNode(-1, head);
            ListNode cur = dummyHead;
            while (cur.next != null) {
                if (cur.next.val == val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            return dummyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}