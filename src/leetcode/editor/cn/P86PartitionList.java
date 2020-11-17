//给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。 
//
// 你应当保留两个分区中每个节点的初始相对位置。 
//
// 
//
// 示例: 
//
// 输入: head = 1->4->3->2->5->2, x = 3
//输出: 1->2->2->4->3->5
// 
// Related Topics 链表 双指针 
// 👍 279 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

import java.util.ArrayList;
import java.util.List;

//Java：分隔链表
public class P86PartitionList {

                /*
           ___           ___                   ___           ___           ___
          /\__\         /\__\      ___        /\__\         /\  \         /\__\          ___
         /:/  /        /:/  /     /\  \      /:/  /        /::\  \       /:/  /         /\  \
        /:/__/        /:/  /      \:\  \    /:/  /        /:/\ \  \     /:/__/          \:\  \
       /::\  \ ___   /:/  /       /::\__\  /:/  /  ___   _\:\~\ \  \   /::\  \ ___      /::\__\
      /:/\:\  /\__\ /:/__/     __/:/\/__/ /:/__/  /\__\ /\ \:\ \ \__\ /:/\:\  /\__\  __/:/\/__/
      \/__\:\/:/  / \:\  \    /\/:/  /    \:\  \ /:/  / \:\ \:\ \/__/ \/__\:\/:/  / /\/:/  /
           \::/  /   \:\  \   \::/__/      \:\  /:/  /   \:\ \:\__\        \::/  /  \::/__/
           /:/  /     \:\  \   \:\__\       \:\/:/  /     \:\/:/  /        /:/  /    \:\__\
          /:/  /       \:\__\   \/__/        \::/  /       \::/  /        /:/  /      \/__/
          \/__/         \/__/                 \/__/         \/__/         \/__/

             */

    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P86PartitionList().new Solution();
        P86PartitionList linkedList = new P86PartitionList();
        linkedList.addNode(new ListNode(1));
        // linkedList.addNode(new ListNode(4));
        // linkedList.addNode(new ListNode(3));
        // linkedList.addNode(new ListNode(2));
        // linkedList.addNode(new ListNode(5));
        // linkedList.addNode(new ListNode(2));
        ListNode partition = solution.partition2(linkedList.head.next, 0);
        partition.printfNode();
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
         * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
         * 你应当保留两个分区中每个节点的初始相对位置。
         * 输入: head = 1->4->3->2->5->2, x = 3
         * 输出: 1->2->2->4->3->5
         *
         * @param head
         * @param x
         * @return
         */
        public ListNode partition2(ListNode head, int x) {
            if (head == null) {
                return null;
            }
            ListNode cur = head;
            List<ListNode> lessList = new ArrayList<>();
            List<ListNode> moreList = new ArrayList<>();
            while (cur != null) {
                if (cur.val >= x) {
                    moreList.add(cur);
                } else {
                    lessList.add(cur);
                }
                cur = cur.next;
            }
            ListNode temp = new ListNode(-1);
            cur = temp;
            for (ListNode node : lessList) {
                cur.next = node;
                cur = cur.next;
            }
            ListNode temp2 = new ListNode(-1);
            ListNode cur2 = temp2;
            for (ListNode node : moreList) {
                cur2.next = node;
                cur2 = cur2.next;
            }
            cur2.next = null;
            cur.next = temp2.next;

            return temp.next;
        }

        public ListNode partition(ListNode head, int x) {
            // 记录小值链表的头
            ListNode lessLink = new ListNode(-1);
            // 对小表操作用的指针
            ListNode lessP = lessLink;

            // 记录大值链表的头
            ListNode moreLink = new ListNode(-1);
            ListNode moreP = moreLink;

            while (head != null) {
                // 找到小的值
                if (head.val < x) {
                    // 放入lessLink中,操作指针后移一位
                    lessP.next = head;
                    lessP = head;
                } else {
                    // 放入moreLink中,操作指针后移一位
                    moreP.next = head;
                    moreP = head;
                }
                head = head.next;
            }
            // 遍历完成后记得最后一段链表的最后结点指向null
            moreP.next = null;
            // 两段拼接
            lessP.next = moreLink.next;
            return lessLink.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}