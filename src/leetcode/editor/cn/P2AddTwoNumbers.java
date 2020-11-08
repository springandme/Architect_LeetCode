//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。 
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学 
// 👍 5204 👎 0

package leetcode.editor.cn;

import leetcode.editor.datastructure.ListNode;

//Java：两数相加

/**
 * 思路:链表长度不能确定,第一元素是个位数,第二元素十位数,其次......
 * 依次把每个链表遍历出来,并计算出这个链表的数目
 * 然后在相加,返回一个新的链表
 */
public class P2AddTwoNumbers {
    private ListNode head = new ListNode(0);

    public static void main(String[] args) {
        Solution solution = new P2AddTwoNumbers().new Solution();
        // TO TEST
        P2AddTwoNumbers listLink = new P2AddTwoNumbers();
        listLink.addNode(new ListNode(1));
        // listLink.addNode(new ListNode(4));
        // listLink.addNode(new ListNode(3));
        listLink.printLink();
        P2AddTwoNumbers listLink2 = new P2AddTwoNumbers();
        listLink2.addNode(new ListNode(9));
        listLink2.addNode(new ListNode(9));
        // listLink2.addNode(new ListNode(4));
        // listLink2.addNode(new ListNode(9));

        int result = solution.traverseLink(listLink.head.next);
        int result2 = solution.traverseLink(listLink2.head.next);
        System.out.println(result + "   " + result2);
        System.out.println("--------------------------------");
        ListNode listNode = solution.addTwoNumbers(listLink.head.next, listLink2.head.next);
        P2AddTwoNumbers twoNumbers = new P2AddTwoNumbers();
        twoNumbers.addNode(listNode);
        twoNumbers.printLink();
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
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public int traverseLink(ListNode listNode) {
            if (listNode.next == null) {
                System.out.println("链表为空!");
                return -1;
            }
            int sum = 0;
            int count = 0;
            ListNode temp = listNode;
            while (temp != null) {
                sum += temp.val * (int) Math.pow(10, count);
                count++;
                temp = temp.next;

            }
            return sum;
        }

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            // ListNode listNode = new ListNode(-1);
            // ListNode temp = listNode;
            // int sum1 = this.traverseLink(l1);
            // int sum2 = this.traverseLink(l2);
            // int result = sum1 + sum2;
            // // 依次取出result的计算结果的位数
            // int[] ints = new int[String.valueOf(result).length()];
            // for (int i = 0; i < ints.length; i++) {
            //     ints[i] = (int) (result / Math.pow(10, i) % 10);
            //     temp.next = new ListNode(ints[i]);
            //     temp = temp.next;
            // }
            // return listNode.next;
            ListNode head = new ListNode(0);
            ListNode cur = head;
            // 表示进位
            int carry = 0;
            // 里面carry>0 ,就是要考虑还有进位的情况,比如1+99=100
            // 即最后和的位数比原来两个数最大位还多一位的情况.所以需要一个carry>0判断
            // 当前遍历完两个链表后,但仍有carry>0,表示还有进位,就需要继续创建结点
            while (l1 != null || l2 != null || carry > 0) {
                int v1Val = l1 != null ? l1.val : 0;
                int v2Val = l2 != null ? l2.val : 0;
                int sum = v1Val + v2Val + carry;
                carry = sum / 10;
                cur.next = new ListNode(sum % 10);
                cur = cur.next;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            return head.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}