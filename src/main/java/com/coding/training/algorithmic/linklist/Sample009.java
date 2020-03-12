package com.coding.training.algorithmic.linklist;

/**
 * https://www.cnblogs.com/tojian/p/10055036.html
 * <p>
 * 9.复杂的链表复制
 * 问题描述
 * Offer：请实现函数ComplexListNode Clone(ComplexListNode head)，复制一个复杂链表。
 * 在复杂链表中，每个结点除了有一个Next指针指向下一个结点外，还有一个Sibling指向链表中的任意结点或者NULL。
 * <p>
 * 下图是一个含有5个结点的复杂链表。图中实线箭头表示m_pNext指针，虚线箭头表示m_pSibling指针。
 * 为简单起见，指向NULL的指针没有画出。
 * <p>
 * <p>
 * 算法思路
 * 第一种：O(n2)的普通解法
 * 　　第一步是复制原始链表上的每一个结点，并用Next节点链接起来；
 * 　　第二步是设置每个结点的Sibling节点指针。
 * <p>
 * 第二种 ：借助辅助空间的O(n)解法
 * 　　第一步仍然是复制原始链表上的每个结点N创建N'，然后把这些创建出来的结点用Next链接起来。同时我们把<N,N'>的配对信息放到一个哈希表中。
 * 　　第二步还是设置复制链表上每个结点的m_pSibling。由于有了哈希表，我们可以用O(1)的时间根据S找到S'。
 * <p>
 * 第三种：不借助辅助空间的O(n)解法
 * 　　第一步仍然是根据原始链表的每个结点N创建对应的N'。（把N'链接在N的后面）
 * <p>
 * 　　第二步设置复制出来的结点的Sibling。（把N'的Sibling指向N的Sibling）
 * <p>
 * 　　第三步把这个长链表拆分成两个链表：把奇数位置的结点用Next链接起来就是原始链表，偶数数值的则是复制链表。
 * <p>
 * 注意：1. 处理wild指针要判断wild为空的情况
 *      2. 注意一个节点的情况
 *      3. 拆分链表要小心断链, 用一个next记录复制的节点
 *      4. 拆分是判断链表是否结束的条件 是 curr.next.next == null
 *      5. 拆分后，旧链表最后一个节点的next置成null
 */
public class Sample009 {
    public static void main(String[] args) {
        Node head = new Node(0);
        Node next = head;

        for (int i = 1; i < 10; i++) {
            next.setNext(new Node(i));
            next = next.getNext();
        }

        // 0 -> 8
        next = head;
        next.setWild(head.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext());

        // 2 -> 7
        next = head.getNext().getNext();
        next.setWild(head.getNext().getNext().getNext().getNext().getNext().getNext().getNext());

        // 7 -> 4
        next = head.getNext().getNext().getNext().getNext().getNext().getNext().getNext();
        next.setWild(next = head.getNext().getNext().getNext().getNext());

        // 4 -> 1
        next = head.getNext().getNext().getNext().getNext();
        next.setWild(head.getNext());

        // 5 -> 6
        next = head.getNext().getNext().getNext().getNext().getNext();
        next.setWild(head.getNext().getNext().getNext().getNext().getNext().getNext());

        // 6 -> 3
        next = head.getNext().getNext().getNext().getNext().getNext().getNext();
        next.setWild(head.getNext().getNext().getNext());

        // 1 -> 9
        next = head.getNext();
        next.setWild(head.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext());

        // 9 -> 5
        next = head.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext();
        next.setWild(head.getNext().getNext().getNext().getNext().getNext());

//        // debug begin
//        next = head;
//        while (next != null) {
//            System.out.print("," + next.getValue());
//            if (next.getWild() != null) {
//                System.out.print("," + next.getWild().getValue());
//            } else {
//                System.out.print(",null");
//            }
//            System.out.println();
//            next = next.getNext();
//        }
//        System.out.println();
//        // debug end

        Node newHead = copy(head);
        while (newHead != null) {
            System.out.println(newHead.getValue());
            newHead = newHead.getNext();
        }
    }

    public static Node copy(Node head) {
        Node curr = head;
        Node tmp = null;

        /**
         * 复制链表
         * 1 -> 2 -> 3 -> null
         * 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> null
         */
        while (curr != null) {
            // 复制当前结点
            tmp = new Node(curr.getValue());
            tmp.setNext(curr.getNext());

            // 把复制的节点加入到链表中（当前结点的前面）
            curr.setNext(tmp);

            // 当前指针迁移
            curr = tmp;
            curr = curr.getNext();
        }

        // debug begin
        System.out.println("-------------复制链表-------------");
        tmp = head;
        while (tmp != null) {
            System.out.print("->" + tmp.getValue());
            tmp = tmp.getNext();
        }
        System.out.println();
        // debug end


        // 复制 wild 指针
        curr = head;
        while (curr != null) {
            if (curr.getWild() != null) {
                curr.getNext().setWild(curr.getWild().getNext());
            }
            curr = curr.getNext().getNext();
        }

        // debug begin
        System.out.println("-------------复制wild指针--------------");
        tmp = head;
        while (tmp != null) {
            System.out.print("," + tmp.getValue());
            if (tmp.getWild() != null) {
                System.out.print("," + tmp.getWild().getValue());
            } else {
                System.out.print(",null");
            }
            System.out.println();
            tmp = tmp.getNext();
        }
        System.out.println();
        // debug end

        // 将链表按照单双数拆分成两个
        curr = head;
        tmp = head.getNext();

        Node next = null;
        while (curr != null && curr.getNext().getNext() != null) {
            next = curr.getNext();
            curr.setNext(next.getNext());
            next.setNext(next.getNext().getNext());

            curr = curr.getNext();
        }
        curr.setNext(null);

        System.out.println("-------------将链表按照单双数拆分成两个--------------");
        Node p1 = head;
        Node p2 = tmp;
        while (p1 != null) {
            if (p1.getWild() != null) {
                System.out.print(p1.getValue() + " -> " + p1.getWild().getValue());
                System.out.print(" | " + p2.getValue() + " -> " + p2.getWild().getValue());
            } else {
                System.out.print(p1.getValue() + " -> null");
                System.out.print(" | " + p2.getValue() + " -> null");
            }

            p1 = p1.getNext();
            p2 = p2.getNext();
            System.out.println();
        }

        return tmp;
    }
}
