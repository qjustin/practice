package com.coding.training.algorithmic.linklist;

/**
 * 10.反转链表
 * 问题描述
 * Offer：定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。如图：
 * 1 -> 2 -> 3 -> null
 * 3 -> 2 -> 1 -> null
 *
 * 算法思路
 * 为了正确地反转一个链表，需要调整链表中指针的方向。为了将复杂的过程说清楚，这里借助于下面的这张图片。
 *
 *
 * 上面的图中所示的链表中，h、i和j是3个相邻的结点。假设经过若干操作，我们已经把h结点之前的指针调整完毕，
 * 这个结点的m_pNext都指向前面的一个结点。接下来我们把i的m_pNext指向h，此时结构如上图所示。
 *
 * 从上图注意到，由于结点i的m_pNext都指向了它的前一个结点，导致我们无法在链表中遍历到结点j。为了避免链
 * 表在i处断裂，我们需要在调整结点i的m_pNext之前，把结点j保存下来。
 *
 * 即在调整结点i的m_pNext指针时，除了需要知道结点i本身之外，还需要i的前一个结点h，因为我们需要把结点i的
 * m_pNext指向结点h。同时，还需要实现保存i的一个结点j，以防止链表断开。故我们需要定义3个指针，分别指向当
 * 前遍历到的结点、它的前一个结点及后一个结点。故反转结束后，新链表的头的结点就是原来链表的尾部结点。尾部
 * 结点为m_pNext为null的结点。
 *
 * 注意：少于三个节点的情况
 *      原来的head.next要置空
 */
public class Sample010 {
    public static void main(String[] args) {
        Node head = new Node(0);
        Node next = head;

//        for(int i = 1; i <= 1; i++) {
//            next.setNext(new Node(i));
//            next = next.getNext();
//        }

        next = inversionLinkList(head);

        while(next != null) {
            System.out.println(next.getValue());
            next = next.getNext();
        }
    }

    public static Node inversionLinkList(Node head) {

        if (head == null) { return null; }
        if (head.getNext() == null) { return head; }
        if (head.getNext().getNext() == null) {
            Node tmp = head.getNext();
            head.getNext().setNext(head);
            head.setNext(null);
            return tmp;
        }

        Node headP = head.getNext().getNext();
        Node middleP = head.getNext();
        Node tailP = head;
        tailP.setNext(null);

        while(headP.getNext() != null) {
            middleP.setNext(tailP);
            tailP = middleP;
            middleP = headP;
            headP = headP.getNext();
        }

        middleP.setNext(tailP);
        headP.setNext(middleP);

        return headP;
    }
}
