package com.coding.training.algorithmic.history.linklist;

/**
 * 234. 回文链表
 * 要实现 O(n) 的时间复杂度和 O(1) 的空间复杂度，需要翻转后半部分
 *
 * 1 —> 2 -> 3 -> 2 -> 1 -> null    true
 * 1 —> 2 -> 2 -> 1 -> null    true
 * 1 —> 2 -> 3 -> 4 -> 2 -> 1 -> null    false
 *
 * 解决方案
 * 1、采用头插法将链表逆置，将整个链表反转过来，判断是否与原链表一样即可
 * 2、升级版：既然它要是回文结构，我们只需要将链表的一半逆置即可，采用双指针遍历的方式，一个从前往后，一个从后往前，同时遍历并比较链表元素是否相等即可。
 */
public class Sample011 {

    public boolean isPalindrome(Node head) {
        if(head == null || head.getNext() == null){
            return true;
        }
        //使用快慢指针找到中间节点
        Node fast = head.getNext();
        Node slow = head;
        while(fast != null && fast.getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        Node secondhead = null;
        //记录后半部分的头节点
        Node second = slow.getNext();
        slow.setNext(null);
        while(second != null){
            //临时变量，记录下一个节点
            Node tmp = second.getNext();
            second.setNext(secondhead);
            secondhead = second;
            second = tmp;
        }

        //链表的后半部分逆置，开始比较节点
        while(secondhead != null && head.getValue().equals(secondhead.getValue())){
            head = head.getNext();
            secondhead = secondhead.getNext();
        }
        if(secondhead == null){
            return true;
        }else{
            return false;
        }
    }
}
