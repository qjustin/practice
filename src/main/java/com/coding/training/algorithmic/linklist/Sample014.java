package com.coding.training.algorithmic.linklist;

/**
 * 给一个链表和整数k，将后k个节点移到链表头。
 * 输入： 1->2->3->4->5->NULL , k =2,输出： 4->5->1->2->3->NULL.
 *
 * 思路：已经把链表首尾相接的连上了，这个是算法的关键，找到应该在何处断开（length - k % length）
 */
public class Sample014 {

    public Node rotateRight(Node head, int k) {
        if (k == 0)
            return head;
        if (head == null)
            return head;
        int length = 1;
        Node cur = head;
        while (cur.getNext() != null) {
            cur = cur.getNext();
            length++;
        }
        cur.setNext(head);//已经把链表首尾相接的连上了
        int m = length - k % length;//这个是算法的关键，找到应该在何处断开
        for (int i = 0; i < m; i++) {
            cur = cur.getNext();
        }
        Node newhead = cur.getNext();//获得新的链表头节点
        cur.setNext(null);//断开链表环
        return newhead;
    }
}
