package com.coding.training.algorithmic.history.linklist;

/**
 * 4.链表中环的大小
 * 问题描述
 * 有一个单向链表，链表当中有可能出现“环”，那么如何知道链表中环的长度呢？
 *
 * 算法思路
 * 如何判断一个链表有环可以知道，快慢指针可以找到链表是否有环存在，如果两个指针第一次相遇后，
 * 第二次相遇是什么时候呢？ 第一次相遇时返回该指针指向的对象A，从对象开始遍历并记录节点数，
 */
public class Sample004 {
    public static void main(String[] args) {
        Node head = new Node(0);
        Node next = head;
        Node cross = null;

        for(int i = 1; i < 10; i++) {
            next.setNext(new Node(i));
            next = next.getNext();

            if (i == 4) {
                cross = next;
            }
        }

        next.setNext(cross);

        System.out.println(getRingLength(head));
    }

    public static int getRingLength(Node head) {
        Node lowPointer = head;
        Node fastPointer = head;
        Boolean isExist = false;
        int count = 0;

        while(!isExist && fastPointer != null && fastPointer.getNext() != null) {
            lowPointer = lowPointer.getNext();
            fastPointer = fastPointer.getNext().getNext();

            // 两个指针相遇说明有环
            if (lowPointer == fastPointer) {

                // 开始计算环长度
                while(!isExist) {
                    fastPointer = fastPointer.getNext();
                    count++;
                    // 当再次相遇长度计算完毕
                    if (fastPointer == lowPointer) {
                        isExist = true;
                    }
                }
            }
        }

        return count;
    }

}
