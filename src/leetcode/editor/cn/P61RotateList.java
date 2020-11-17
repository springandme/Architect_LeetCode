//给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: 1->2->3->4->5->NULL, k = 2
//输出: 4->5->1->2->3->NULL
//解释:
//向右旋转 1 步: 5->1->2->3->4->NULL
//向右旋转 2 步: 4->5->1->2->3->NULL
// 
//
// 示例 2: 
//
// 输入: 0->1->2->NULL, k = 4
//输出: 2->0->1->NULL
//解释:
//向右旋转 1 步: 2->0->1->NULL
//向右旋转 2 步: 1->2->0->NULL
//向右旋转 3 步: 0->1->2->NULL
//向右旋转 4 步: 2->0->1->NULL 
// Related Topics 链表 双指针 
// 👍 367 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

//Java：旋转链表
public class P61RotateList {
    public static void main(String[] args) {
        Solution solution = new P61RotateList().new Solution();
        // TO TEST
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
         * 获得链表长度
         *
         * @param head
         * @return
         */
        public int getLength(ListNode head) {
            ListNode dummyHead = head;
            int count = 0;
            while (dummyHead != null) {
                count++;
                dummyHead = dummyHead.next;
            }
            return count;
        }

        /**
         * 得到最后一个结点,并把这个结点设置为第一个结点位置
         *
         * @param head
         * @return
         */
        public ListNode getLastNode(ListNode head, int length) {
            ListNode dummyHead = new ListNode(-1, head);
            ListNode cur = dummyHead;
            ListNode lastPrev = null;
            for (int i = 0; i < length - 1; i++) {
                cur = cur.next;
            }
            lastPrev = cur.next;
            cur.next = null;

            lastPrev.next = dummyHead.next;
            dummyHead.next = lastPrev;

            return dummyHead.next;
        }

        /**
         * 双指针方式!--把末尾那个加到头部
         * 输入: 1->2->3->4->5->NULL, k = 2
         * 输出: 4->5->1->2->3->NULL
         * 解释:
         * 向右旋转 1 步: 5->1->2->3->4->NULL
         * 向右旋转 2 步: 4->5->1->2->3->NULL
         *
         * @param head
         * @param k
         * @return
         */
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            int length = this.getLength(head);
            int count = k % length;
            for (int i = 0; i < count; i++) {
                head = getLastNode(head, length);
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}