//给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。 
//
// 示例 1: 
//
// 输入: 1->2->3->3->4->4->5
//输出: 1->2->5
// 
//
// 示例 2: 
//
// 输入: 1->1->1->2->3
//输出: 2->3 
// Related Topics 链表 
// 👍 397 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

import java.util.HashSet;
import java.util.Set;

//Java：删除排序链表中的重复元素 II
public class P82RemoveDuplicatesFromSortedListIi {

    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P82RemoveDuplicatesFromSortedListIi().new Solution();
        P82RemoveDuplicatesFromSortedListIi linkedList = new P82RemoveDuplicatesFromSortedListIi();
        linkedList.addNode(new ListNode(1));
        linkedList.addNode(new ListNode(1));
        linkedList.addNode(new ListNode(1));
        linkedList.addNode(new ListNode(2));
        // linkedList.addNode(new ListNode(2));
        linkedList.addNode(new ListNode(3));
        // linkedList.addNode(new ListNode(3));
        // linkedList.addNode(new ListNode(3));
        // linkedList.addNode(new ListNode(4));
        // ListNode listNode = solution.deleteNode(linkedList.head.next, 1);
        // listNode.printfNode();
        // linkedList.printLink();
        ListNode listNode = solution.deleteDuplicates(linkedList.head.next);
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

        /**
         * 输入: 1->2->3->3->4->4->5
         * 输出: 1->2->5
         *
         * @param head
         * @return
         */
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return null;
            }
            // Set<E>是一个不包含重复元素的collection,更确切地讲,set不包含满足e1.equals(e2)的元素对e1和e2,并且最多包含一个null元素
            // 常见的实现类有HashSet<E> 和 TreeSet<E>

            Set<Integer> set = new HashSet<>();
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null) {
                if (slow.val == fast.val) {
                    set.add(slow.val);
                    slow.next = fast.next;
                    fast = slow.next;
                } else {
                    slow = slow.next;
                    fast = fast.next;
                }
            }
            for (Integer integer : set) {
                head = deleteNode(head, integer);
            }

            return head;
        }

        /**
         * 根据传入的值删除结点
         *
         * @param head
         * @param val
         */
        public ListNode deleteNode(ListNode head, int val) {
            ListNode dummyHead = new ListNode(-1, head);
            ListNode pCur = dummyHead;
            while (pCur.next != null) {
                if (pCur.next.val == val) {
                    pCur.next = pCur.next.next;
                    break;
                }
                pCur = pCur.next;
            }
            return dummyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}