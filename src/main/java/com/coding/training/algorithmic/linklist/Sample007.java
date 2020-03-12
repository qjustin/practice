package com.coding.training.algorithmic.linklist;
/**
 * 7.两个链表的第一个公共结点
 * 问题描述
 * 输入两个单链表，找出他们的第一个公共结点。
 *
 * 算法思路
 * 我们了解到单链表的指针是指向下一个节点的，如果两个单链表的第一个
 * 公共节点就说明他们后面的节点都是在一起的。类似下图，由于两个链表
 * 的长度可能是不一致的，所以首先比较两个链表的长度m，n，然后用两个
 * 指针分别指向两个链表的头节点，让较长的链表的指针先走|m-n|个长度，
 * 如果他们下面的节点是一样的，就说明出现了第一个公共节点。
 *
 * 算法思路 2
 * 这道题表面上看起来有两个链表，不过如果做一个辅助线，比如把B链表首尾相连，
 * 也就是让链表的末节点指向B链表的表头，那么这道题就变成了求单链表的环的起点。
 *
 */
public class Sample007 {
    public static void main(String[] args) {
        Node head1 = new Node(0);
        Node head2 = new Node(5);
        Node head3 = new Node(10);
        Node next1 = head1;
        Node next2 = head2;
        Node next3 = head3;

        for(int i = 1; i <= 8; i++) {
            if (i < 5) {
                next1.setNext(new Node(i));
                next1 = next1.getNext();
            } else {
                next2.setNext(new Node(i+1));
                next2 = next2.getNext();
            }
        }

        for(int i = 11; i <= 14; i++) {
            next3.setNext(new Node(i));
            next3 = next3.getNext();
        }

        next1.setNext(head3);
        next2.setNext(head3);

        next1 = head1;
        next2 = head2;

      do {
          System.out.print("," + next1.getValue());
          next1 = next1.getNext();
      } while(next1.getNext() != null);
        System.out.println();
        do {
            System.out.print("," + next2.getValue());
            next2 = next2.getNext();
        } while(next2.getNext() != null);
        System.out.println();
        System.out.println(getCrossNode(head1, head2).getValue());
    }

    public static Node getCrossNode(Node head1, Node head2) {
        Node tailPointer = head2;
        Node lowPointer = head1;
        Node fastPointer = head2;

        while(tailPointer.getNext() != null) {
            tailPointer = tailPointer.getNext();
        }
        tailPointer.setNext(head2);

        while(fastPointer != null && fastPointer.getNext() != null) {
            lowPointer = lowPointer.getNext();
            fastPointer = fastPointer.getNext().getNext();

            if (lowPointer == fastPointer) {
                fastPointer = head1;
                while(fastPointer != lowPointer) {
                    lowPointer = lowPointer.getNext();
                    fastPointer = fastPointer.getNext();
                }
                return  fastPointer;
            }
        }

        return null;
    }
}
