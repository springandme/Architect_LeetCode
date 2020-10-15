package com.liushi.hashtab;

import java.util.Scanner;

/**
 * @ClassName HashTabDemo
 * @Description TODO
 * @Author liushi
 * @Date 2020/10/14 17:01
 * @Version V1.0
 **/
public class HashTabDemo {

    public static void main(String[] args) {
        // 创建哈希表
        HashTab hashTab = new HashTab(7);

        // 写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();

            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    // 创建雇员
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

/**
 * 表示一个雇员
 */
class Emp {
    public int id;
    public String name;
    // 默认为null
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

/**
 * 创建HashTab 管理多条链表
 */
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    // 表示有多少条链表
    private int size;

    // 构造器
    public HashTab(int size) {
        this.size = size;
        // 初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        // ? empLinkedListArray能不能用?
        // 这时不要忘记,分别初始化每一个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加雇员
    public void add(Emp emp) {
        // 根据员工的id,得到该员工应当添加到链表
        int empLinkedListNum = hashFun(emp.id);
        // 将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNum].add(emp);
    }

    // 遍历所有的链表,遍历我们的Hash
    public void list() {
        if (empLinkedListArray == null) {
            System.out.println("Hash为空");
            return;
        }
        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    /**
     * 根据输入的id,查找雇员
     *
     * @param id 输入的id
     */
    public void findEmpById(int id) {
        // 使用散列函数确认到哪条链表查询
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到雇员id = %d!\n", empLinkedListNO + 1, id);
        } else {
            System.out.println("在哈希表中,没有找到该雇员!");
        }
    }

    // 编写一个散列函数,使用取模法
    public int hashFun(int id) {
        return id % size;
    }

}


/**
 * 创建EmpLinkList,表示链表
 */
class EmpLinkedList {
    // 头指针[默认为null],指向第一个Emp,因此我们这个链表的head,是直接指向第一个Emp,
    private Emp head;

    /**
     * 添加雇员到链表
     * 说明:
     * 1.假定,当添加雇员是,id是自增长,即id的分配总是从小到大
     * 因此我们将雇员直接加入到本链表的最后即可
     */
    public void add(Emp emp) {
        // 如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是第一个雇员,则使用第一个辅助的指针,帮助定位到最后
        Emp curEmp = head;
        while (true) {
            // 说明到链表最后
            if (curEmp.next == null) {
                break;
            }
            // 后移
            curEmp = curEmp.next;
        }
        // 退出时,直接将emp加入链表
        curEmp.next = emp;
    }

    /**
     * 遍历链表的雇员信息
     */
    public void list(int no) {
        // 说明链表为空
        if (head == null) {
            System.out.println("第 " + (no + 1) + " 链表为空 !");
            return;
        }
        System.out.print("第 " + (no + 1) + " 链表的信息为");
        // 辅助指针
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            // 说明curEmp已经是最后节点
            if (curEmp.next == null) {
                break;
            }
            // 后移,遍历
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    /**
     * 根据id查找雇员
     * 如果查找到,就返回Emp,如果没有找到,就返回null
     */
    public Emp findEmpById(int id) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        // 辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                // 说明找到,这是curEmp就指向要查找的雇员
                break;
            }
            // 退出,说明遍历当前链表没有找到该雇员
            if (curEmp.next == null) {
                curEmp = null;
                // bug 点在这
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

}
