//请判断一个链表是否为回文链表。 
//
// 示例 1: 
//
// 输入: 1->2
//输出: false 
//
// 示例 2: 
//
// 输入: 1->2->2->1
//输出: true
// 
//
// 进阶： 
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 链表 双指针 
// 👍 757 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Java：回文链表
public class P234PalindromeLinkedList {
    private ListNode head = new ListNode(-1);

    public static void main(String[] args) {
        Solution solution = new P234PalindromeLinkedList().new Solution();
        P234PalindromeLinkedList linkedList = new P234PalindromeLinkedList();
        linkedList.addNode(new ListNode(1));
        linkedList.addNode(new ListNode(1));
        linkedList.addNode(new ListNode(2));
        linkedList.addNode(new ListNode(1));
        // linkedList.addNode(new ListNode(2));
        // linkedList.addNode(new ListNode(3));
        // linkedList.addNode(new ListNode(3));
        // linkedList.addNode(new ListNode(2));
        // linkedList.addNode(new ListNode(1));
        boolean palindrome = solution.isPalindrome3(linkedList.head.next);
        System.out.println(palindrome);
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

        public int getLength(ListNode head) {
            ListNode dummy = head;
            int count = 0;
            while (dummy != null) {
                count++;
                dummy = dummy.next;
            }
            return count;
        }


        /**
         * 执行耗时:14 ms,击败了6.24% 的Java用户
         * 内存消耗:43 MB,击败了8.57% 的Java用户
         *
         * @param head
         * @return
         */
        public boolean isPalindrome2(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            int length = this.getLength(head);
            int midIndex = length / 2;
            for (int i = 0; i < midIndex; i++) {
                fast = fast.next;
            }

            List<Integer> slowList = new ArrayList<>();
            List<Integer> fastList = new ArrayList<>();
            // 处理奇数情况下
            if (length % 2 == 1) {
                fast = fast.next;
                for (int i = 0; i < midIndex; i++) {
                    slowList.add(slow.val);
                    fastList.add(fast.val);
                    slow = slow.next;
                    fast = fast.next;
                }
                // 处理偶数的情况下
            } else {
                for (int i = 0; i < midIndex; i++) {
                    slowList.add(slow.val);
                    fastList.add(fast.val);
                    slow = slow.next;
                    fast = fast.next;
                }
            }
            Collections.reverse(slowList);
            return slowList.toString().equals(fastList.toString());
        }

        /**
         * 方法一：将值复制到数组中后用双指针法
         * 确定数组列表是否回文很简单，我们可以使用双指针法来比较两端的元素，
         * 并向中间移动。一个指针从起点向中间移动，另一个指针从终点向中间移动。
         * 这需要 O(n) 的时间，因为访问每个元素的时间是 O(1),而有 n 个元素要访问。
         * ###
         * 一共为两个步骤：
         * 复制链表值到数组列表中。
         * 使用双指针法判断是否为回文。
         * ###
         * 复杂度分析
         * 时间复杂度：O(n)，其中 nn 指的是链表的元素个数。
         * 第一步：遍历链表并将值复制到数组中，O(n)。
         * 第二步：双指针判断是否为回文，执行了 O(n/2) 次的判断，即 O(n)。
         * 总的时间复杂度：O(2n) = O(n)
         * 空间复杂度：O(n)，其中 n 指的是链表的元素个数，我们使用了一个数组列表存放链表的元素值。
         *
         * @param head
         * @return
         */
        public boolean isPalindrome(ListNode head) {
            List<Integer> list = new ArrayList<>();

            // 将链表的值复制到数组中
            ListNode dummy = head;
            while (dummy != null) {
                list.add(dummy.val);
                dummy = dummy.next;
            }
            // List<Integer> 转Integer[] 方式
            // int size = list.size();
            // Integer[] integers = list.toArray(new Integer[size]);

            // 方式1: 把list转成了int[]数组
            // 执行耗时:9 ms,击败了6.24% 的Java用户
            // 内存消耗:42.6 MB,击败了17.32% 的Java用户
            // List<Integer> 转int[] 方式
            int[] array = list.stream().mapToInt(Integer::intValue).toArray();
            int slow = 0;
            int fast = array.length - 1;
            for (int i = 0; i < array.length / 2; i++) {
                if (array[slow] != array[fast]) {
                    return false;
                }
                slow++;
                fast--;
            }

            // 方式2: 没有转换list,直接在list上面进行操作, 精髓地方在于while (front < back)
            // 一个front向后移动,back向前移动,当数组长度奇数时,两个索引移动了中间位置[front==back]并且碰面了,然后结束循环
            // 当数组长度为偶数时,两个索引会出现front > back 情况,就是back移动了front的前面,然后直接结束循环
            // 执行耗时:4 ms,击败了31.07% 的Java用户
            // 内存消耗:42.3 MB,击败了27.26% 的Java用户
/*          int front = 0;
            int back = list.size() - 1;
            while (front < back) {
                // java内部有一个常量池，在-128~127之间的相同的值指向相同的地址。
                // 但是超过这个范围，虽然两个值相同，但是会重新给这两个相同的值分配不同内存，导致地址不同，
                // 所以==用来比较地址的时候，就会显示地址不一样，返回false
                if (!list.get(front).equals(list.get(back))) {
                    return false;
                }
                front++;
                back--;
            }*/
            return true;
        }

        /**
         * 哈希法
         *
         * @param head
         * @return
         */
        public boolean isPalindrome3(ListNode head) {
            ListNode t = head;
            int base = 11, mod = 1000000007;
            int left = 0, right = 0, mul = 1;
            while (t != null) {
                left = (int) (((long) left * base + t.val) % mod);
                right = (int) ((right + (long) mul * t.val) % mod);
                mul = (int) ((long) mul * base % mod);
                t = t.next;
            }
            return left == right;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}