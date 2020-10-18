package com.liushi.linkedlist;

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

        list.printLinked();
        // 测试修改的节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        list.update(newHeroNode);
        System.out.println("修改后的链表情况~~");
        list.printLinked();

        // 测试删除节点的代码
        list.delete(node05);
        System.out.println("删除后的链表情况~~");
        list.printLinked();
    }
}


/**
 * 定义SingleLinkedList管理我们的英雄
 */
class SingleLinkedList {
    // 先初始化一个头结点,头结点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");


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
            // flag为真,表示不能天机,说明编号已经存在
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

