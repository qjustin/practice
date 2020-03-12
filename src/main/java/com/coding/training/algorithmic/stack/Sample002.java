package com.coding.training.algorithmic.stack;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 用两个栈实现队列
 *
 * 将队列中的元素“abcd”压入stack1中，此时stack2为空；
 *
 * 将stack1中的元素pop进stack2中，此时pop一下stack2中的元素，就可以达到和队列删除数据一样的顺序了；
 *
 * 可能有些人很疑惑，就像图3，当stack2只pop了一个元素a时，satck1中可能还会插入元素e,这时如果将stack1
 * 中的元素e插入stack2中，在a之后出栈的元素就是e了，显然，这样想是不对的，我们必须规定当stack2中的元素pop完之后，
 * 也就是satck2为空时，再插入stack1中的元素。
 *
 *
 * 注意: 只有当stack2为空时才能再次从stack1取元素
 */
public class Sample002 {

    public static void main(String[] args) {
        TwoStackQueue queue = new TwoStackQueue();

        queue.add("a");
        queue.add("b");
        queue.add("c");

        while(!queue.isEmpty())  {
            System.out.println(queue.take());
        }

        queue.add("d");
        while(!queue.isEmpty())  {
            System.out.println(queue.take());
        }

        queue.add("e");
        queue.add("f");
        queue.add("g");

        while(!queue.isEmpty())  {
            System.out.println(queue.take());
        }

    }
}

class TwoStackQueue {
    Stack inStack = new Stack();
    Stack outStack = new Stack();

    public void add(Object obj) {
        inStack.push(obj);
    }

    public Object take() {
        if (outStack.isEmpty()) {
            while(!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }

        return outStack.pop();
    }

    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
