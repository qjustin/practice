package com.coding.training.algorithmic.history.stack;

import java.util.LinkedList;

/**
 * 两个队列实现一个栈
 * <p>
 * 这两个队列中始终有一个是空的。另一个非空。push添加元素到非空队列中，
 * pop把非空队列中前面的元素都转移到另一个队列中，只剩最后一个元素，
 * 再把最后一个元素pop出来。这样这一个队列是空的，另一个队列又非空了。
 */
public class Sample003 {
    public static void main(String[] args) {
        TwoQueueStack<Integer> stack = new TwoQueueStack();

//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        stack.push(5);
//        stack.push(6);
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        stack.push(7);
//        System.out.println(stack.pop());

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

class TwoQueueStack<T> {
    LinkedList<T> q1 = new LinkedList<>();
    LinkedList<T> q2 = new LinkedList<>();
    LinkedList<T> fullQueue;
    LinkedList<T> emptyQueue;

    public void push(T data) {
        fullQueue = q1.isEmpty() ? q2 : q1;
        fullQueue.add(data);
    }

    public T pop() {
        emptyQueue = q1.isEmpty() ? q1 : q2;
        fullQueue = q1.isEmpty() ? q2 : q1;

        while (fullQueue.size() - 1 > 0) {
            emptyQueue.add(fullQueue.poll());
        }

        return fullQueue.poll();
    }
}






















/*
class TwoQueueStack {
    private ArrayDeque q1 = new ArrayDeque();
    private ArrayDeque q2 = new ArrayDeque();
    private ArrayDeque fullQ = null;
    private ArrayDeque emptyQ = null;

    public void push(Integer data) {
        fullQ = q1.isEmpty() ? q2 : q1;
        fullQ.add(data);
    }

    public Object pop() {
        emptyQ = q1.isEmpty() ? q1 : q2;
        fullQ = q1.isEmpty() ? q2 : q1;

        while (fullQ.size() - 1 > 0) {
            emptyQ.add(fullQ.poll());
        }
        return fullQ.poll();
    }
}
*/