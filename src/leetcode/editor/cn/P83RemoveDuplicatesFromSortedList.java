//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表 
// 👍 423 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

//Java：删除排序链表中的重复元素
public class P83RemoveDuplicatesFromSortedList {
    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P83RemoveDuplicatesFromSortedList().new Solution();

        P83RemoveDuplicatesFromSortedList link = new P83RemoveDuplicatesFromSortedList();
        link.addNode(new ListNode(1));
        link.addNode(new ListNode(1));
        link.addNode(new ListNode(2));
        link.addNode(new ListNode(2));
        link.addNode(new ListNode(3));
        link.addNode(new ListNode(3));
        link.addNode(new ListNode(3));
        // link.addNode(new ListNode(2));
        // link.addNode(new ListNode(2));
        // link.addNode(new ListNode(2));
        // link.addNode(new ListNode(3));
        // link.addNode(new ListNode(3));
        // link.addNode(new ListNode(3));
        // link.addNode(new ListNode(4));
        ListNode listNode = solution.deleteDuplicates(link.head.next);
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
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode temp = head;
            ListNode pCur = temp.next;
            while (pCur != null) {
                // 逻辑思维在这一步:
                // 一开始temp指向的第一个元素,如果temp.val == pCur.val,那么改变temp.next的指针域,指向为pCur.next
                // 然后在pCur指向temp.next, 但是不改变temp的指针,仍让它指向原来的那个元素,只是改变了它的next域
                if (temp.val == pCur.val) {
                    temp.next = pCur.next;
                    pCur = temp.next;
                } else {
                    // 如果两者不相等,就都把两者后移一位
                    temp = temp.next;
                    pCur = pCur.next;
                }
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}