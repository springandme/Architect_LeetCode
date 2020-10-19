package com.liushi.linkedlist;

import java.util.Stack;

import static com.liushi.linkedlist.SingleLinkedList.*;

/**
 * @ClassName SingleLinkedLIst
 * @Description TODO
 * @Author liushi
 * @Date 2020/10/18 20:54
 * @Version V1.0
 **/
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode node01 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node02 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node03 = new HeroNode(3, "吴用", "智多星");
        HeroNode node04 = new HeroNode(4, "林冲", "豹子头");
        HeroNode node05 = new HeroNode(7, "鲁智深", "花和尚");
        SingleLinkedList list = new SingleLinkedList();
        // list.add(node01);
        // list.add(node02);
        // list.add(node03);
        // list.add(node04);
        list.addByOrder(node01);
        list.addByOrder(node04);
        list.addByOrder(node03);
        list.addByOrder(node02);
        list.addByOrder(node05);

        System.out.println("单链表的长度为" + getLength(list.getHead()));
        System.out.println("获得list倒数第一个节点" + findLastIndexNode(list.getHead(), 1));
        System.out.println("------------------------------------------------------");

        System.out.println("原来的链表情况~~");
        list.printLinked2();
        System.out.println("------------------------------------------------------");
        System.out.println("反向遍历链表情况~~");
        list.reversePrint();
        System.out.println("------------------------------------------------------");

        System.out.println("反转的链表情况~~");
        myReverseList(list.getHead());
        list.printLinked2();
        System.out.println("------------------------------------------------------");
        /*// 测试修改的节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        list.update(newHeroNode);
        System.out.println("修改后的链表情况~~");
        list.printLinked();

        // 测试删除节点的代码
        list.delete(node05);
        System.out.println("删除后的链表情况~~");
        list.printLinked();*/
    }
}


/**
 * 定义SingleLinkedList管理我们的英雄
 */
class SingleLinkedList {
    // 先初始化一个头结点,头结点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 方法:获取到单链表的结点个数(如果是带头节点的链表,需求不统计头节点
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            // System.out.println("链表为空");
            return 0;
        }
        int length = 0;
        // 定义一个辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 查询单链表中的倒数第k个节点[新浪面试题]
     * 思路:
     * 1.编写一个方法,接收head节点,同时接收一个lastIndex
     * 2.lastIndex表示是倒数第lastIndex个节点.
     * 3.先把链表从头到尾遍历,得到链表的总的长度[调用getLength]
     * 4.得到length后.我们从链表的第一个开始遍历(length-lastIndex)个,就可以得到
     *
     * @param head      头节点
     * @param lastIndex 倒数索引
     * @return 找到了, 就返回该节点, 否则返回null
     */
    public static HeroNode findLastIndexNode(HeroNode head, int lastIndex) {
        if (head.next == null) {
            return null;
        }
        // 第一次遍历得到链表的长度(节点个数)
        int length = getLength(head);
        // 先做一个lastIndex校验
        if (lastIndex > length || lastIndex <= 0) {
            return null;
        }
        int index = length - lastIndex;

        // 定义一个辅助变量,for循环定位到倒数的lastIndex
        HeroNode temp = head.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp;
    }

    /**
     * 单链表的反转[腾讯面试题]
     */
    public static void reverseList(HeroNode head) {
        // 如果当前链表为空,或者只有一个结点,就无须反转,直接return
        if (head.next == null || head.next.next == null) {
            return;
        }

        // 定义一个辅助变量[指针],帮助我们遍历链表
        HeroNode temp = head.next;
        // 指向当前节点[temp]的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        // 遍历原来的链表,并从头遍历一个节点,就将其取出,并放在新的链表reverseHead的最前端
        while (temp != null) {
            // 先暂时保存当前节点的下一个结点,因为后面需要使用
            next = temp.next;
            // 将temp的下一个节点指向新的链表的最前端
            temp.next = reverseHead.next;
            // 将temp连接到新的链表上
            reverseHead.next = temp;
            // 让temp后移
            temp = next;
        }
        // 将head.next 指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * head -> 1 -> 2 -> 3 -> 4 -> 5
     * head -> 2 -> 1 -> 3 -> 4 -> 5
     * head -> 3 -> 2 -> 1 -> 4 -> 5
     * head -> 4 -> 3 -> 2 -> 1 -> 5
     * head -> 5 -> 4 -> 3 -> 2 -> 1
     * <p>
     * pCur是需要反转的节点
     * 思路:
     * 1.prev连接下次需要反转的节点
     * 2.反转节点PCur
     * 3.纠正头结点head的指向
     * 4.pCur指向下一次要反转的节点
     * 注意:其中prev的指向一直都没有发生变化,一直指向的值 1 即是[head.next]
     * --
     *
     * @param head 头结点
     */
    public static void myReverseList(HeroNode head) {
        // 如果当前链表为空,或者只有一个结点,就无须反转,直接return
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode prev = head.next;
        //
        HeroNode pCur = prev.next;
        while (pCur != null) {
            prev.next = pCur.next;
            pCur.next = head.next;
            head.next = pCur;
            pCur = prev.next;
        }
    }


    /**
     * 反向遍历
     */
    public void reversePrint() {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            HeroNode node = stack.pop();
            System.out.println(node);
        }
    }

    /**
     * 合并两个有序的单链表，合并之后的链表依然有序【课后练习.】
     */
    public void mergeLinked(HeroNode head, HeroNode head2) {

    }

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    /**
     * 添加结点到单向链表
     * 思路:不考虑编号的顺序时
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next指向新的节点
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        // 因为head节点不能动,因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        // 遍历链表,找到最后
        while (temp.next != null) {
            // 将temp后移
            temp = temp.next;
        }
        // 循环结束后 temp就指向了链表的最后 temp.next = null
        temp.next = heroNode;
    }

    /**
     * 添加结点时候考虑编号,根据排名插入到指定位置
     * 如果有这个排名,则添加失败,并给出提示
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        // 因为head节点不能动,因此我们仍然需要一个辅助指针[变量]temp,来找到添加的位置
        // 以为单链表,因为我们找的temp是位于添加位置的前一个结点,否则插入不进去
        HeroNode temp = head;
        // 标志添加的编号是否存在,默认为false
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                // 说明希望添加的heroNode的编号已经存在了
                flag = true;
                break;
            }
            // 后移,遍历当前链表
            temp = temp.next;
        }
        // 判断flag的值,
        if (flag) {
            // flag为真,表示不能添加,说明编号已经存在
            System.out.println("准备插入的英雄编号" + heroNode.no + "已经存在了,不能添加了");
            // return;
        } else {
            // 1.新的节点.next=temp.next
            heroNode.next = temp.next;
            // 2.将temp.next=新的节点
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点的信息,根据no编号来修改,即no编号不能改
     *
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 先进行遍历
        // 表示是否找到该节点
        boolean flag = false;
        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.no == newHeroNode.no) {
                temp.name = newHeroNode.name;
                temp.nickname = newHeroNode.nickname;
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("没有找到" + newHeroNode.no + "编号的节点,不能修改");
        }
    }

    /**
     * 删除节点
     *
     * @param heroNode
     */
    public void delete(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        boolean flag = false;
        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next == heroNode) {
                flag = true;
                temp.next = heroNode.next;
                heroNode = null;
                break;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("没有找到" + heroNode.no + "编号的节点,不能删除");
        }
    }

    /**
     * 遍历链表
     */
    public void printLinked() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头结点不能动,因此我们需要一个辅助变量来遍历
        HeroNode temp = head;
        // 判断是否到链表最后
        while (temp.next != null) {
            // 输出信息
            System.out.println(temp.next);
            // 将temp后移
            temp = temp.next;
        }
    }

    public void printLinked2() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头结点不能动,因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        // 判断是否到链表最后
        while (temp != null) {
            // 输出信息
            System.out.println(temp);
            // 将temp后移
            temp = temp.next;
        }
    }


}


/**
 * 定义HeroNode,每一个HeroNode对象就是一个结点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    // 指定下一个结点
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    // 为了显示方法,我们重写toString()
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

