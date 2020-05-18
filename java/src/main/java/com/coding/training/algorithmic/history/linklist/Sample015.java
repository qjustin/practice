package com.coding.training.algorithmic.history.linklist;

/**
 * 划分链表
 * offer： 给定一个链表和一个值x，将链表划分成两部分，使得划分后小于x的结点在前，
 * 大于等于x的结点在后，在这两部分中要保持原链表中的结点的出现顺序；
 * 如： 给定链表1->2->4->3->2->5->2->6 和 x=3，返回1->2->2->2->4->3->5->6；
 * <p>
 * 思路如下： 首先构造两个空的链表 head_1和 head_2，定义两个指针 pre_1 和 pre_2 分别指向这两个空链表的头结点，
 * 循环遍历给定的链表head，取出给定链表的头结点的next的value值与x进行比较，若是小于x则添加到链表head_1的尾部，
 * 若是大于等于x则添加到链表head_2的尾部，然后链表head_1的尾部链接到链表head_2的头部；整体算法的时间复杂度为O（N），
 * 空间复杂度为O（1）；
 */
public class Sample015 {

    public static void divideLink(Node head, int num) {
        Node head_1 = new Node(0);  // 定义链表head_1
        Node pre_1 = head_1;       // 链表head_1的指针变量
        Node head_2 = new Node(0);  // 定义链表head_2
        Node pre_2 = head_2;       // 链表head_2的指针变量
        Node temp = head.getNext();     // 临时存储取出的待划分链表的结点
        while (temp != null) {     // 循环遍历待划分链表
            if (temp.getValue() < num) { // 如果temp的结点值小于num，则添加到head_1的尾部
                pre_1.setNext(temp);
                pre_1 = temp;
            } else {                // 如果temp的结点值大于等于num，则添加到head_2的尾部
                pre_2.setNext(temp);
                pre_2 = temp;
            }
            temp = temp.getNext();
        }
        pre_1.setNext(head_2.getNext());   // 链表head_1的next域指向链表head_2的next
        pre_2.setNext(null);          // head_2的next为null，否则出错
        head.setNext(head_1.getNext());    // 链表head的next域指向链表head_1的next域
    }
}
