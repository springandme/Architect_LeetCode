package com.liushi.linkedlist;

/**
 * @ClassName Josephus
 * @Description 约瑟夫问题
 * @Author liushi
 * @Date 2020/10/23 10:36
 * @Version V1.0
 **/
public class Josephus {

    // public static CircleSingleLinkedList list = new CircleSingleLinkedList();

    /**
     * Josephus  问题为：设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，数到m
     * 的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
     * ###
     * n = 5 , 即有5个人
     * k = 1, 从第一个人开始报数
     * m = 2, 数2下
     * ###
     * 出队列的顺序
     * 2->4->1->5->3
     *
     * @param args
     */
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(5);
        list.printCircleLinkedList();
        System.out.println("环形链表中结点个数为:" + list.getCount());
        list.countBoy(1, 2, 5);
    }


}


/**
 * 创建一个环形的单向链表
 */
class CircleSingleLinkedList {
    // 创建一个first结点,但当前没有编号
    private Boy first = null;

    /**
     * 添加小孩结点,构建一个环形的链表
     */
    public void addBoy(int nums) {
        // nums 需要做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        // 辅助指针,帮助构建环形链表
        Boy curBoy = null;

        // 使用for循环来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号,创建小孩结点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                first = boy;
                // 构成环状,只是这个环状只有一个元素
                first.setNext(first);
                // 让curBoy指向第一个小孩
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                // 在调整curBoy的指向
                curBoy = boy;
            }
        }
    }

    public void printCircleLinkedList() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("环形链表为空,没有任何小孩~~");
            return;
        }
        // 因为first不能动,因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;

        while (true) {
            System.out.println("小孩的编号" + curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
        // 上面那个循环等于下面这个do-while循环,先输出,再判断条件
        /*do {
            System.out.println("小孩的编号" + curBoy.getNo());
            curBoy = curBoy.getNext();
        } while (curBoy.getNext() != first);*/
    }

    /**
     * 获得环形链表结点的个数
     *
     * @return
     */
    public int getCount() {
        // 判断链表是否为空
        if (first == null) {
            return 0;
        }
        int count = 0;
        Boy curBoy = first;
        while (true) {
            count++;
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
        return count;
    }

    /**
     * 根据用户的输入,计算出小孩出圈的顺序
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误,请重新输入!");
            return;
        }

        // 创建一个辅助指针(变量),帮助完成小孩出圈,事先应该指向环形链表饿最后这个结点
        Boy helper = first;
        // 这里就是将helper指向环形链表的最后一个结点
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        // 调整从第几个小孩开始数数
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            // 说明圈中只有一个结点,退出循环
            // if (helper == first) {
            //     break;
            // }
            // 或则使用list内部方法得到环形链表结点个数是否==1
            if (this.getCount() == 1) {
                break;
            }
            // 小孩报数时,让first和helper指针同时移动countNum-1次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这个时候first指向的节点,就是出圈的小孩结点,打印出圈小孩的编号
            // stringBuilder.append(first.getNo()).append("->");
            System.out.print(first.getNo() + "->");
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println(first.getNo());
    }
}


/**
 * 创建一个Boy类,表示一个结点
 */
class Boy {
    // 编号
    private int no;
    // 指向下一个结点,默认为null
    private Boy next;

    public Boy() {
    }

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
