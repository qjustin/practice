package com.coding.training.algorithmic.history.linklist;

/**
 * 链表相加求和
 * offer： 假设链表中每一个节点的值都在 0-9 之间，那么链表整体可以代表一个整数。
 * 例如： 9->3->7 可以代表 937
 * 给定两个这样的链表，头节点为 head1 head2 生成链表相加的新链表。
 * 如 9->3->7 和 6 -> 3 生成的新链表应为 1 -> 0 -> 0 -> 0
 */
public class Sample016 {
    public Node addTwoNumbers(Node l1, Node l2) {
        Node Node = new Node(0);
        Node p = new Node(0);
        p = Node;
        int sum = 0;

        while (l1 != null || l2 != null || sum != 0) {
            if (l1 != null) {
                sum += l1.getValue();
                l1 = l1.getNext();
            }
            if (l2 != null) {
                sum += l2.getValue();
                l2 = l2.getNext();
            }
            p.setNext(new Node(sum % 10));
            sum = sum / 10;
            p = p.getNext();
        }
        return Node.getNext();
    }
}
