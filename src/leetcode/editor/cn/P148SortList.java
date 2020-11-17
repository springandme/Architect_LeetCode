//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 进阶： 
//
// 
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 104] 内 
// -105 <= Node.val <= 105 
// 
// Related Topics 排序 链表 
// 👍 818 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Java：排序链表
public class P148SortList {

    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P148SortList().new Solution();
        // TO TEST
        P148SortList linkedList = new P148SortList();
        linkedList.addNode(new ListNode(4));
        linkedList.addNode(new ListNode(1));
        linkedList.addNode(new ListNode(7));
        linkedList.addNode(new ListNode(3));
        linkedList.addNode(new ListNode(6));
        linkedList.addNode(new ListNode(2));
        linkedList.addNode(new ListNode(5));
        linkedList.addNode(new ListNode(8));
        ListNode sortList = solution.sortList(linkedList.head.next);
        System.out.println("迭代排序好的链表结果");
        sortList.printfNode();
        // solution.getMidNode(linkedList.head.next);
        // ListNode split = solution.split(linkedList.head.next, 3);
        // System.out.println("断链操作前半部分");
        // linkedList.head.next.printfNode();
        // System.out.println("断链操作后半部分");
        // split.printfNode();

        P148SortList link = new P148SortList();
        link.addNode(new ListNode(1));
        link.addNode(new ListNode(2));
        link.addNode(new ListNode(4));
        P148SortList link2 = new P148SortList();
        link2.addNode(new ListNode(1));
        link2.addNode(new ListNode(3));
        link2.addNode(new ListNode(4));
        link2.addNode(new ListNode(5));
        ListNode mergeResult = solution.merge(link.head.next, link2.head.next);
        System.out.println("合并两个有序链表测试");
        mergeResult.printfNode();
    }

    public void addNode(ListNode node) {
        ListNode temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * 技巧一：通过快慢指针找到链表中点
     * 可以通过快慢指针的方法。快指针每次走两步，慢指针每次走一步。
     * 遍历完链表时，慢指针停留的位置就在链表的中点。
     *
     * @param head
     * @return
     */
    public void getMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            // 慢指针走一步
            slow = slow.next;
            // 快指针走两步
            fast = fast.next.next;
        }

        // 右边序列是,链表第二部分的头结点
        ListNode rightHead = slow.next;
        // cut切分链表,得到左边序列
        slow.next = null;
        System.out.println("右边序列遍历结果为:");
        rightHead.printfNode();
        System.out.println("左边序列遍历结果为:");
        head.printfNode();
    }

    /**
     * 第一种方式使用一个list集合,装入链表中所有元素,然后对其排序
     * 执行耗时:15 ms,击败了13.48% 的Java用户
     * 内存消耗:48.1 MB,击败了5.01% 的Java用户
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        Collections.sort(list);
        ListNode dummyHead = new ListNode(-1);
        cur = dummyHead;
        for (Integer integer : list) {
            cur.next = new ListNode(integer);
            cur = cur.next;
        }
        return dummyHead.next;
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
         * 递归写法
         *
         * @param head
         * @return
         */
        public ListNode sortList2(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            // 1.通过快慢指针找到链表中点
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            // 右边序列是,链表第二部分的头结点
            ListNode rightHead = slow.next;
            // cut切分链表,得到左边序列
            slow.next = null;

            // 递归排序左边链表
            ListNode left = sortList2(head);
            // 递归排序右边链表
            ListNode right = sortList2(rightHead);

            return merge(left, right);
        }

        /**
         * 迭代写法
         * 使用了归并排序思想:
         * ###
         * 对链表做归并排序可以通过修改引用来更改结点位置,因此不需要想数组一样开辟额外的O(n)空间,
         * 但是只要是递归就需要消耗log(n)的空间复杂度,值达到O(1)空间复杂度的目标,需要使用迭代法!
         * ###
         * 1.分割 cut 环节： 找到当前链表中点，并从中点将链表断开
         * 2.合并 merge 环节： 将两个排序链表合并，转化为一个排序链表。
         *
         * @param head
         * @return
         */
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            int length = getLength(head);
            ListNode dummyHead = new ListNode(-1, head);

            // 依次将链表分成1块,2块,4块....
            for (int step = 1; step < length; step *= 2) {
                // 每次变换步长,pre指针和cur指针都初始化在链表头
                ListNode pre = dummyHead;
                ListNode cur = dummyHead.next;
                while (cur != null) {
                    // 第一部分头 (第二次循环之后,cur为剩余部分头,不断让后把链表按照步长step分成一块一块...)
                    ListNode l1 = cur;
                    // 第二部分头
                    ListNode l2 = split(l1, step);
                    // 剩余部分的头
                    cur = split(l2, step);
                    // 将一二部分排序合并
                    ListNode temp = merge(l1, l2);
                    // 将前面的部分与排序好的部分连接
                    pre.next = temp;
                    while (pre.next != null) {
                        // 把pre指针移动到排序好的部分的末尾
                        pre = pre.next;
                    }
                }
            }
            return dummyHead.next;
        }

        public int getLength(ListNode head) {
            //获取链表长度
            int count = 0;
            while (head != null) {
                count++;
                head = head.next;
            }
            return count;
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


        /**
         * 合并两个有序链表--P21题
         *
         * @param l1
         * @param l2
         * @return
         */
        public ListNode merge(ListNode l1, ListNode l2) {
            // 创建一个伪头结点
            ListNode dummyHead = new ListNode(-1);
            ListNode cur = dummyHead;
            while (l1 != null && l2 != null) {
                if (l1.val > l2.val) {
                    cur.next = l2;
                    l2 = l2.next;
                } else {
                    cur.next = l1;
                    l1 = l1.next;
                }
                cur = cur.next;
            }
            // 判断哪个链表有剩余部分,直接把cur指针指向它
            if (l1 != null) {
                cur.next = l1;
            } else if (l2 != null) {
                cur.next = l2;
            }
            // 返回排序好的表结点
            return dummyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}