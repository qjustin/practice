package com.coding.training.algorithmic.offer;

import com.coding.training.algorithmic.entity.Node;

/**
 * 面试题06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * 限制：
 * 0 <= 链表长度 <= 10000
 * 通过次数12,039提交次数15,661
 */
public class Num0004 {
    public static void main(String[] args) {
        Node head = new Node();
        Node node = head;
        for (int i = 1; i < 10; i++) {
            node.next = new Node(i);
            node = node.next;
        }

        printLinkedListFromTail(head);
    }

    public static void printLinkedListFromTail(Node node) {
        if (node != null) {
            printLinkedListFromTail(node.next);
        }

        if (node != null) {
            System.out.println(node.value);
        }
    }
}
