//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 
//
// 示例： 
//
// 给你这个链表：1->2->3->4->5 
//
// 当 k = 2 时，应当返回: 2->1->4->3->5 
//
// 当 k = 3 时，应当返回: 3->2->1->4->5 
//
// 
//
// 说明： 
//
// 
// 你的算法只能使用常数的额外空间。 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
// Related Topics 链表 
// 👍 783 👎 0

package leetcode.editor.cn;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

//Java：K 个一组翻转链表
public class P25ReverseNodesInKGroup {

    private ListNode head = new ListNode(0);

    public static void main(String[] args) {
        Solution solution = new P25ReverseNodesInKGroup().new Solution();
        P25ReverseNodesInKGroup nodes = new P25ReverseNodesInKGroup();
        nodes.addNode(new ListNode(1));
        nodes.addNode(new ListNode(2));
        nodes.addNode(new ListNode(3));
        nodes.addNode(new ListNode(4));
        nodes.addNode(new ListNode(5));
        nodes.printLink();
        System.out.println("\n----------------------------------------");
        ListNode listNode = solution.reverseKGroup(nodes.head.next, 2);
        P25ReverseNodesInKGroup nodes2 = new P25ReverseNodesInKGroup();
        System.out.println("局部反转后的结果为:");
        nodes2.addNode(listNode);
        nodes2.printLink();
        // nodes.getLength();
        // TO TEST


    }

    @Test
    public void test() {
        int sum = 807;
        int count = 0;
        int[] ints = new int[String.valueOf(sum).length()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (sum / Math.pow(10, i) % 10);
        }
        System.out.println(Arrays.toString(ints) + " ->");
    }


    /*public void test() {
        int k = 3;
        int xh1 = 0;
        //int xh2 = 0;
        P25ReverseNodesInKGroup temp = new P25ReverseNodesInKGroup();
        xh1 = this.getLength() / k;

        for (int i = 0; i < xh1; i++) {
            for (int j = 0; j < k - 1; j++) {

            }
        }
    }*/


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
    }

    public int getLength() {
        int count1 = 0;

        if (head.next == null) {
            return 0;
        }
        ListNode temp = head.next;
        while (temp != null) {
            count1++;
            temp = temp.next;
        }
        return count1;
    }

    public void addNode(ListNode node) {
        ListNode temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }


    static class ListNode {
        public int val;

        // 指定下一个结点
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }
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
     * ## 思路:
     * **思路一:**
     * 用栈,我们把k个数压入栈中,然后弹出来的顺序就是翻转的!
     * 这里要注意几个问题
     * 第一,剩下的链表个数够不够k个(因为不够k个不用翻转);
     * 第二,已经翻转的部分要与剩下链表连接起来
     */
    class Solution {

        public ListNode reverseKGroup(ListNode head, int k) {
            Stack<ListNode> stack = new Stack<>();
            // dummy 假 ?
            ListNode dummy = new ListNode(0);
            ListNode p = dummy;
            while (true) {
                int count = 0;
                ListNode temp = head;
                while (temp != null && count < k) {
                    stack.add(temp);
                    temp = temp.next;
                    count++;
                }
                if (count != k) {
                    p.next = head;
                    break;
                }
                while (!stack.isEmpty()) {
                    p.next = stack.pop();
                    p = p.next;
                }
                p.next = temp;
                head = temp;
            }
            return dummy.next;
        }

        public ListNode reverseKGroup2(ListNode head, int k) {
            ListNode dummy = new ListNode(0);
            ListNode prev = dummy;
            ListNode curr = head;
            ListNode next;
            dummy.next = head;
            int length = 0;
            // 计算链表的长度
            while (head != null) {
                length++;
                head = head.next;
            }
            // 又将head指向一个dummy[它是一个拥有一个头结点]
            head = dummy.next;
            for (int i = 0; i < length / k; i++) {
                for (int j = 0; j < k - 1; j++) {
                    next = curr.next;
                    curr.next = next.next;
                    next.next = prev.next;
                    prev.next = next;
                }
                prev = curr;
                curr = prev.next;
            }
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}