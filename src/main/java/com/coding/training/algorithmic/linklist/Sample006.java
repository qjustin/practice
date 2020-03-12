package com.coding.training.algorithmic.linklist;

/**
 * 6.单链表在时间复杂度为O(1)删除链表结点
 * 问题描述
 * 给定单链表的头指针和一个结点指针，定一个函数在时间复杂度为O(1)删除链表结点
 *
 * 算法思路
 * 根据了解的条件，如果只有一个单链表的头指针，链表的删除操作其实正常的是O（n）的时间复杂度。
 * 因为首先想到的是从头开始顺序遍历单链表，然后找到节点，再进行删除。但是这样的方式达到的时间
 * 复杂度并不是O（1）；实际上纯粹的删除节点操作，链表的删除操作是O（1）。
 * 前提是需要找到删除指定节点的前一个结点就可以。
 *
 * 那么是不是必须找到删除指定节点的前一个结点呢？如果我们删除的节点是A，那么我们把A下一个
 * 节点B和A的data进行交换，然后我们删除节点B，是不是也可以达到同样的效果。
 *
 * 答案是肯定的。
 * 既然不能在O(1)得到删除节点的前一个元素，但我们可以轻松得到后一个元素，这样，
 * 我们何不把后一个元素赋值给待删除节点，这样也就相当于是删除了当前元素。可见，
 * 该方法可行，但如果待删除节点为最后一个节点，则不能按照以上思路，没有办法，
 * 只能按照常规方法遍历，时间复杂度为O(n)，是不是不符合题目要求呢？可能很多人
 * 在这就会怀疑自己的思考，从而放弃这种思路，最后可能放弃这道题，
 * 这就是这道面试题有意思的地方，虽看简单，但是考察了大家的分析判断能力，
 * 是否拥有强大的心理，充分自信。其实我们分析一下，仍然是满足题目要求的，如果删除
 * 节点为前面的n-1个节点，则时间复杂度为O(1)，只有删除节点为最后一个时，时间复杂度才为O(n)，
 * 所以平均的时间复杂度为：（O(1) * (n-1) + O(n)）/n = O(1);仍然为O(1).
 *
 * 关键在于：如果我们删除的节点是A，那么我们把A下一个节点B和A的data进行交换，然后我们删除节点B，是不是也可以达到同样的效果。
 *          如果是尾节点 就 不能是使用这种方式。必须遍历整个链表找到前驱节点
 */
public class Sample006 {
    public static void main(String[] args) {
        Node head = new Node(0);
        Node next = head;
        Node delNode = null;
        for(int i = 1; i < 10; i++) {
            next.setNext(new Node(i));
            next = next.getNext();
            if (i == 8) {
                delNode = next;
            }
        }

        delNode(head, delNode);

        while(head.getNext() != null) {
            System.out.println(head.getValue());
            head = head.getNext();
        }
    }

    public static void delNode(Node head, Node delNode) {
        if (delNode.getNext() != null) {
            Node nextNode = delNode.getNext();
            delNode.setValue(nextNode.getValue());
            delNode.setNext(nextNode.getNext());
        } else {
            while(head.getNext().getNext() != null) {
                head = head.getNext();
            }

            head.setNext(null);
        }

    }
}
