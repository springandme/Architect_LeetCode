package com.liushi.linkedlist;

/**
 * @ClassName DoubleLinkedListDemo
 * @Description TODO
 * @Author liushi
 * @Date 2020/10/23 8:52
 * @Version V1.0
 **/
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 node01 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 node02 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 node03 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 node04 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 node05 = new HeroNode2(7, "鲁智深", "花和尚");

        DoubleLinkedList list = new DoubleLinkedList();
        // list.add(node01);
        // list.add(node02);
        // list.add(node03);
        // list.add(node04);
        // list.add(node05);
        list.addByOrder(node01);
        list.addByOrder(node02);
        list.addByOrder(node03);
        list.addByOrder(node05);
        list.addByOrder(node04);

        System.out.println("双向链表测试~~");
        list.printLinkedList();
        HeroNode2 updateNode = new HeroNode2(7, "武松", "行者");
        list.update(updateNode);

        System.out.println("修改链表测试~~");
        list.printLinkedList();

        System.out.println("删除链表测试~~");
        list.delete(7);
        list.printLinkedList();
    }
}

/**
 * 创建一个双向链表的类
 */
class DoubleLinkedList {
    // 先初始化一个头结点,头结点不要动,不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    public void setHead(HeroNode2 head) {
        this.head = head;
    }

    public void printLinkedList() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 双向链表的添加到最后节点
     * 注意一下只要修改一次就行
     * ###
     * 注意一下:当temp指向的不是head结点,而是第一个数据结点,结束循环时候,temp==null
     * 所以,temp.next 当temp为null时,null.next就会报空指针异常
     * 因此在添加的时候,辅助数组需要指向头head结点,  HeroNode2 temp = head;
     *
     * @param heroNode2
     */
    public void add(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        // 当退出while循环是,temp就指向了链表的最后
        temp.next = heroNode2;
        heroNode2.pre = temp;
    }

    /**
     * 添加结点时候考虑编号,根据排名插入到指定位置
     * 如果有这个排名,则添加失败,并给出提示
     *
     * @param heroNode2
     */
    public void addByOrder(HeroNode2 heroNode2) {
        HeroNode2 temp = this.head;
        // 标志添加的编号是否存在,默认为false
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.no > heroNode2.no) {
                break;
            } else if (temp.next.no == heroNode2.no) {
                flag = true;
                break;
            }
            // 后移,遍历当前链表
            temp = temp.next;
        }
        if (flag) {
            // flag为假,表示不能添加,说明编号已经存在
            System.out.println("准备插入的英雄编号" + heroNode2.no + "已经存在了,不能添加了");
        } else {
            // 将之前temp指向的next结点转移到hero上面
            heroNode2.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = heroNode2;
            }
            // 然后temp指向hero
            temp.next = heroNode2;
            heroNode2.pre = temp;
        }
    }

    public void update(HeroNode2 heroNode2) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;

        boolean flag = false;
        while (temp != null) {
            if (temp.no == heroNode2.no) {
                flag = true;
                temp.name = heroNode2.name;
                temp.nickname = heroNode2.nickname;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("没有找到" + heroNode2.no + "编号的节点,不能修改");
        }
    }


    /**
     * 从双向链表中删除一个结点
     * 说明:
     * 1.对于双向链表,我们可以直接找到要删除的这个结点
     * 2.找到后,自我删除即可
     *
     * @param no
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;

        boolean flag = false;
        while (temp != null) {
            if (temp.no == no) {
                flag = true;
                temp.pre.next = temp.next;
                // 这里我们的代码有问题?
                // 如果是最后一个结点,就不需要执行下面这句话,否则出现空指针
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
                // heroNode2 = null;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("没有找到" + no + "编号的节点,不能删除");
        }
    }
}

/**
 * 定义HeroNode,每一个HeroNode对象就是一个结点
 */
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    // 指定下一个结点,默认为NUll
    public HeroNode2 next;
    // 指向前一个结点,默认为NUll
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    // 为了显示方法,我们重写toString()
    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
