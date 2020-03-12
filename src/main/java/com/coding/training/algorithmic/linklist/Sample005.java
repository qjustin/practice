package com.coding.training.algorithmic.linklist;

/**
 * 5. 链表中环的入口结点
 * 问题描述
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 *
 * 算法思路
 * 如果链表存在环，那么计算出环的长度n，然后准备两个指针pSlow，pFast，pFast先走n步，
 * 然后pSlow和pFase一块走，当两者相遇时，即为环的入口处；
 * 所以解决三个问题：如何判断一个链表有环；如何判断链表中环的大小；链表中环的入口结点。
 * 实际上最后的判断就如同链表的倒数第k个节点。
 *
 * 最中重要的一点是： 当快慢指针第一次相遇后，慢指针到环的起点的距离和链表头指针到环起点距离相等。
 */
public class Sample005 {
    public static void main(String[] args) {
        Node head = new Node(0);
        Node next = head;
        Node cross = null;

        for(int i = 1; i < 10; i++) {
            next.setNext(new Node(i));
            next = next.getNext();

            if (i == 7) {
                cross = next;
            }
        }

        next.setNext(cross);

        System.out.println(getRingEntry(head).getValue());
    }

    public static Node getRingEntry(Node head) {
        Node lowPointer = head;
        Node firstPointer = head;

        while(firstPointer != null && firstPointer.getNext() != null) {
            lowPointer = lowPointer.getNext();
            firstPointer = firstPointer.getNext().getNext();
            // 当快慢指针第一次相遇后，慢指针到环的起点的距离和链表头指针到环起点距离相等。
            if (lowPointer == firstPointer) {
                firstPointer = head;
                while(firstPointer != lowPointer) {
                    firstPointer = firstPointer.getNext();
                    lowPointer = lowPointer.getNext();
                }

                return firstPointer;
            }
        }

        return null;
    }
}
