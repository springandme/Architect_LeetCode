//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表 
// 👍 1360 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

//Java：合并两个有序链表
public class P21MergeTwoSortedLists {
    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P21MergeTwoSortedLists().new Solution();
        // TO TEST
        P21MergeTwoSortedLists link = new P21MergeTwoSortedLists();
        link.addNode(new ListNode(1));
        link.addNode(new ListNode(2));
        link.addNode(new ListNode(4));
        P21MergeTwoSortedLists link2 = new P21MergeTwoSortedLists();
        link2.addNode(new ListNode(1));
        link2.addNode(new ListNode(3));
        link2.addNode(new ListNode(4));
        link2.addNode(new ListNode(5));

        ListNode listNode = solution.mergeTwoLists(link.head.next, link2.head.next);
        listNode.printfNode();
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
         * 思路:当l1和l2都不是空链表时,判断l1和l2哪一个链表的头结点的值更小
         * -将较小值得结点添加到结果里.当一个结点被添加到结果里之后,将对应链表中的结点向后移一位.
         *
         * @param l1
         * @param l2
         * @return
         */
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            // 设置一个哨兵结点preHead,这样就比较容易返回合并后的链表
            ListNode preHead = new ListNode(-1);
            // 在定义一个辅助结点,因为哨兵结点是不能动的,维护一个prev指针,只需要调整它的next指针
            ListNode prev = preHead;
            // l1和l2中间有一个为null就退出循环
            while (l1 != null && l2 != null) {
                // 如果l1当前结点的值小于或者等于l2的值[注意:这里给出结点是没有头结点的]
                if (l1.val <= l2.val) {
                    // 就把l1当前的结点接在prev结点的后面,同时将l1指针后移一位
                    prev.next = l1;
                    l1 = l1.next;
                } else {
                    prev.next = l2;
                    l2 = l2.next;
                }
                // 不管我们将哪一个元素接在了后面,我们都需要把prev的指针后移一位
                prev = prev.next;
            }
            // 循环结束后,l1和l2中至多有1个是非空的.
            prev.next = l1 == null ? l2 : l1;
            return preHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}