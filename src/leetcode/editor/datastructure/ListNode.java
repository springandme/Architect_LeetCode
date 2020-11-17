package leetcode.editor.datastructure;

/**
 * @ClassName ListNode
 * @Description TODO
 * @Author liushi
 * @Date 2020/11/6 11:56
 * @Version V1.0
 **/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void printfNode() {
        ListNode temp = this;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
