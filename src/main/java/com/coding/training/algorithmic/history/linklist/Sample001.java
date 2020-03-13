package com.coding.training.algorithmic.history.linklist;

/**
 * 链表的倒数第K个结点
 * <p>
 * 问题描述：
 * 输入一个链表，输出该链表中倒数第k个结点。为了符合大多数人的习惯，本题从1开始计数，
 * 即链表的尾结点是倒数第1个结点。例如一个链表有6个结点，从头结点开始它们的值依次是
 * 1、2、3、4、5、6。这个链表的倒数第3个结点是值为4的结点,需要保证时间复杂度。
 * <p>
 * 算法思路：
 * 设置两个指针p1,p2，p1指针先出发k个节点，之后p2指针开始出发，当p1指针到达链表尾巴节点的时候，则第二个指针表示的位置就是链表的倒数第k个
 * 节点的位置。
 * <p>
 * https://www.cnblogs.com/tojian/p/10055036.html
 */
public class Sample001 {

    public static void main(String[] args) {
        Node head = new Node(0);

        Node next = head;
        for (int i = 1; i <= 9; i++) {
            next.setNext(new Node(i));
            next = next.getNext();
        }
        Node tmp = head;

        while (tmp != null) {
            System.out.println(tmp.getValue());
            tmp = tmp.getNext();
        }

        System.out.println(getNodeK(head, 3).getValue());
    }

    public static Node getNodeK(Node head, int k) {
        Node p1 = head;
        Node p2 = head;

        int i = 1;

        while (p1 != null && i++ <= k) {
            p1 = p1.getNext();
        }

        while (p1 != null) {
            p1 = p1.getNext();
            p2 = p2.getNext();
        }

        return p2;
    }
}
