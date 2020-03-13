package com.coding.training.algorithmic.history.linklist;

/**
 * 8.合并两个排序的链表
 * 问题描述
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * <p>
 * 算法思路
 * 这道题比较简单，合并两个有序的链表，就可以设置两个指针进行操作即可，同时比较大小，但是也需要注意两个链表的长度进行比较。
 */

public class Sample008 {
    public static void main(String[] args) {
        Node head1 = new Node(0);
        Node head2 = new Node(1);
        Node next1 = head1;
        Node next2 = head2;


        for (int i = 0; i < 5; i++) {
            next1.setNext(new Node(0));
            next1 = next1.getNext();
        }

        for (int i = 0; i < 5; i++) {
            next2.setNext(new Node(1));
            next2 = next2.getNext();
        }

        for (int i = 2; i < 20; i += 2) {
            next1.setNext(new Node(i));
            next1 = next1.getNext();
        }

        for (int i = 3; i < 20; i += 2) {
            next2.setNext(new Node(i));
            next2 = next2.getNext();
        }


        for (int i = 0; i < 5; i++) {
            next1.setNext(new Node(20));
            next1 = next1.getNext();
        }

        for (int i = 0; i < 5; i++) {
            next2.setNext(new Node(21));
            next2 = next2.getNext();
        }


        Node mergeHead = merge(head1, head2);

        while (mergeHead != null) {
            System.out.print("," + mergeHead.getValue());
            mergeHead = mergeHead.getNext();
        }
    }

    public static Node merge(Node head1, Node head2) {
        Node mergeHead = new Node(-1);
        Node currentPointer = mergeHead;

        while (head1 != null && head2 != null) {
            if (head1.getValue() <= head2.getValue()) {
                currentPointer.setNext(head1);
                head1 = head1.getNext();
            } else {
                currentPointer.setNext(head2);
                head2 = head2.getNext();
            }

            currentPointer = currentPointer.getNext();
        }

        if (head1 == null) {
            currentPointer.setNext(head2);
        } else {
            currentPointer.setNext(head1);
        }

        return mergeHead.getNext();
    }
}
