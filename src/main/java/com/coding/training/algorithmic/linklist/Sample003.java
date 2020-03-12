package com.coding.training.algorithmic.linklist;

/**
 * 3.如何判断一个链表有环
 * 问题描述：
 * 有一个单向链表，链表当中有可能出现“环”，如何用程序判断出这个链表是有环链表？
 * 不允许修改链表结构。时间复杂度O(n)，空间复杂度O(1)。
 *
 * 算法思路：
 * 方法一、穷举遍历
 * 首先从头节点开始，依次遍历单链表的每一个节点。每遍历到一个新节点，就从头节点重新遍历新节点之前的所有节点，
 * 用新节点ID和此节点之前所有节点ID依次作比较。如果发现新节点之前的所有节点当中存在相同节点ID，则说明该节点
 * 被遍历过两次，链表有环；如果之前的所有节点当中不存在相同的节点，就继续遍历下一个新节点，继续重复刚才的操作。
 *
 * 假设从链表头节点到入环点的距离是D，链表的环长是S。那么算法的时间复杂度是0+1+2+3+….+(D+S-1) = (D+S-1)(D+S)/2 ，
 * 可以简单地理解成 O(NN)。而此算法没有创建额外存储空间，空间复杂度可以简单地理解成为O(1)。
 *
 * 这种方法是暴力破解的方式，时间复杂度太高。
 *
 * 方法二、快慢指针
 * 首先创建两个指针1和2，同时指向这个链表的头节点。然后开始一个大循环，在循环体中，让指针1每次向下移动一个节点，
 * 让指针2每次向下移动两个节点，然后比较两个指针指向的节点是否相同。如果相同，则判断出链表有环，如果不同，则继续下一次循环。
 *
 *
 *
 * 说明 ：在循环的环里面，跑的快的指针一定会反复遇到跑的慢的指针 ，比如：在一个环形跑道上，两个运动员在同一地点起跑，
 * 一个运动员速度快，一个运动员速度慢。当两人跑了一段时间，速度快的运动员必然会从速度慢的运动员身后再次追上并超过，
 * 原因很简单，因为跑道是环形的。
 */
public class Sample003 {
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

        System.out.println(isCircularLinkList(head));
    }

    public static boolean isCircularLinkList(Node head) {
        Node lowPointer = head;
        Node fastPointer = head;

        //使用快慢指针，慢指针每次向前一步，快指针每次两步
        while(fastPointer != null && fastPointer.getNext() != null) {
            lowPointer = lowPointer.getNext();
            fastPointer = fastPointer.getNext().getNext();

            // 避免无环报空指针异常
            if (lowPointer == null || fastPointer == null) {
                return false;
            }

            //两指针相遇则有环
            if (lowPointer == fastPointer) {
                return true;
            }
        }

        return false;
    }
}
