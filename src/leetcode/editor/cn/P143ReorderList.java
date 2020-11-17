//给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 示例 1: 
//
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3. 
//
// 示例 2: 
//
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3. 
// Related Topics 链表 
// 👍 457 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

//Java：重排链表
public class P143ReorderList {

    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P143ReorderList().new Solution();
        P143ReorderList linkedList = new P143ReorderList();
        linkedList.addNode(new ListNode(1));
        linkedList.addNode(new ListNode(2));
        linkedList.addNode(new ListNode(3));
        linkedList.addNode(new ListNode(4));
        linkedList.addNode(new ListNode(5));
        linkedList.addNode(new ListNode(6));
        ListNode right = solution.getMidNode(linkedList.head.next);
        right.printfNode();
        System.out.println("-----------");
        linkedList.head.next.printfNode();
        // solution.reorderList2(linkedList.head.next);

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
         * 寻找链表中点 + 链表逆序 + 合并链表
         * ####
         * 时间复杂度：O(N)，其中 N 是链表中的节点数。
         * 空间复杂度：O(1)。
         * 1 -> 2 -> 3 -> 4 -> 5 -> 6
         * 第一步，将链表平均分成两半
         * 1 -> 2 -> 3
         * 4 -> 5 -> 6
         * <p>
         * 第二步，将第二个链表逆序
         * 1 -> 2 -> 3
         * 6 -> 5 -> 4
         * <p>
         * 第三步，依次连接两个链表
         * 1 -> 6 -> 2 -> 5 -> 3 -> 4
         *
         * @param head
         */
        public void reorderList(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) {
                return;
            }
            ListNode right = getMidNode(head);
            right = reverse(right);
            // 链表结点依次连接
            mergeList(head, right);
        }

        /**
         * 依次合并两个链表
         * l1:  1 -> 2 -> 3
         * l2:  6 -> 5 -> 4
         * 结果:1 -> 6 -> 2 -> 5 -> 3 -> 4
         *
         * @param l1
         * @param l2
         */
        public void mergeList(ListNode l1, ListNode l2) {
            ListNode l1_tmp;
            ListNode l2_tmp;
            while (l1 != null && l2 != null) {
                l1_tmp = l1.next;
                l2_tmp = l2.next;

                l1.next = l2;
                l1 = l1_tmp;

                l2.next = l1;
                l2 = l2_tmp;
            }
        }

        /**
         * 找中点,并且链表分成两个
         *
         * @param head
         * @return
         */
        public ListNode getMidNode(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode right = slow.next;
            slow.next = null;
            return right;
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

        /**
         * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
         * 将其重新排列后变为： L0 → Ln → L1 → Ln-1 → L2 → Ln-2 →…
         * // 示例 1:
         * //
         * // 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
         * //
         * // 示例 2:
         * //
         * // 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
         *
         * @param head
         */
        public void reorderList2(ListNode head) {
            if (head == null || head.next == null) {
                return;
            }
            int length = getLength(head);

            ListNode cur = head;
            for (int i = 0; i < length - 1; i++) {
                // 断链操作,根据长度length-1切割-->返回,切割最后一个元素
                ListNode split = split(head, length - 1);
                split.next = cur.next;
                cur.next = split;
                cur = cur.next.next;
                i++;
            }
        }

        /**
         * 断链操作
         * split(head,n) 即切掉链表head的前n个结点,并返回后部分的链表头
         * 比如原来链表时 dummy->1->2->4->3->5->null
         * split(head,2)的操作造成:
         * dummy->1->2->null
         * return    4->3->5->null
         * ###
         * 若dummy->1->2->null
         * split(head,2)的操作造成:
         * dummy->1->2->null
         * return    null
         *
         * @param head
         * @param step
         * @return
         */
        public ListNode split(ListNode head, int step) {
            if (head == null) {
                return null;
            }
            ListNode cur = head;
            // 注意这里cur.next!=null有可能出现,后半段还没到规定步长,但是走完情况下
            for (int i = 0; i < step - 1 && cur.next != null; i++) {
                cur = cur.next;
            }
            // right为后半段链表头
            ListNode right = cur.next;
            // 切断前半段
            cur.next = null;
            // 返回后半段链表头
            return right;
        }

        public int getLength(ListNode head) {
            int count = 0;
            ListNode temp = head;
            while (temp != null) {
                count++;
                temp = temp.next;
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}