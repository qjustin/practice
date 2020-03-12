package com.coding.training.algorithmic.linklist;

import java.util.Stack;

/**
 * 2.倒着打印链表（从尾到头打印链表）（递归和非递归）
 * 问题描述：
 * 输入一个单链表链表，从尾到头打印链表每个节点的值。
 * 输入描述：输入为链表的表头；输出描述：输出为需要打印的“新链表”的表头。
 * <p>
 * 算法思路：
 * 1. 首先我们想到从尾到头打印出来，由于单链表的查询只能从头到尾，所以可以想出栈的特性，先进后出。
 * 所以非递归可以把链表的点全部放入一个栈当中，然后依次取出栈顶的位置即可
 * <p>
 * 2. 使用递归打印
 */
public class Sample002 {
    public static void main(String[] args) {
        Node head = new Node(0);
        Node next = head;

        for (int i = 1; i <= 9; i++) {
            next.setNext(new Node(i));
            next = next.getNext();
        }

        recursionImplement(head);
//        recursionImplement(head);
    }

    public static void stackImplement(Node head) {
        Stack<Node> stack = new Stack<>();
        Node tmp = head;

        while (tmp != null) {
            stack.push(tmp);
            tmp = tmp.getNext();
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop().getValue());
        }
    }

    public static void recursionImplement(Node node) {
        if (node != null && node.getNext() != null) {
            recursionImplement(node.getNext());
        }

        if (node != null)
        System.out.println(node.getValue());
    }
}