package com.coding.training.algorithmic.stack;

import java.util.Stack;

/**
 * 包含min函数的栈
 * 题目：
 * <p>
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的min函数。在该栈中，
 * 调用min、push及pop的时间复杂度都是O（1）。
 * <p>
 * 思路：
 * <p>
 * 要使时间复杂度是O（1），需要每次压入一个新元素进栈时，将栈里的所有元素排序，让
 * 最小的元素位于栈顶。但是这种想法不能保证最后压入栈的元素能够最先出栈，因为这个
 * 数据结构已经不是栈了。
 * <p>
 * 于是借助于一个辅助的成员变量来存放最小的元素。每次压入一个新元素进栈的时候，如果
 * 该元素比当前最小的元素还要小，则更新最小元素，但是如果当前做小的元素被弹出栈了，
 * 怎么得到下一个最小元素是一个问题。分析到这里我们发现仅仅添加一个成员变量存放最
 * 小元素是不够的，也就是说当最小元素被弹出栈的时候，我们希望能够得到次小的元素。
 * 因此在压入最小元素之前，我们要把次小元素保存起来。故可以借助一个辅助栈把每次的
 * 最小元素都保存起来。
 * <p>
 * 设置两个栈，数据栈data_stack与最小值栈min_stack，这两个栈对于添加元素push与弹出栈顶元素pop都是同步进行的:
 * 1.push(x) : 将元素x直接压入数据栈data_stack中，若x小于最小值栈栈顶，则将x压入最小值栈中，
 * 否则将最小值栈栈顶压入最小值栈。
 * 2.pop() : 同时弹出(移除)数据栈栈顶与最小值栈顶元素。
 * 3.top() : 返回数据栈data_stack栈顶元素。
 * 4.getMin() : 返回最小值栈min_stack栈顶元素
 * <p>
 * 关键：定义一个min变量。判断当前插入的数据 data 是否小于 min
 * 是则 min=data ，然后插入min，
 * 否则 min保持不变，然后插入min，
 * minStack 出栈 需要判断栈顶元素是否相等
 */

public class Sample001 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(3);
        minStack.push(4);
        minStack.push(2);
        minStack.push(1);
        minStack.push(2);
        System.out.print(minStack.min() + ",");
        System.out.print(minStack.pop());
        System.out.println();
        System.out.print(minStack.min() + ",");
        System.out.print(minStack.pop());
        System.out.println();
        minStack.push(0);
        System.out.print(minStack.min() + ",");
        System.out.print(minStack.pop());
        System.out.println();

        System.out.print(minStack.min() + ",");
        System.out.print(minStack.pop());
    }
}

class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(Integer data) {
        stack.push(data);

        if (minStack != null || data <= minStack.peek()) {
            minStack.push(data);
        }
    }

    public Integer pop() {
        if (stack.peek() == minStack.peek()) {
            minStack.pop();
        }

        return stack.pop();
    }

    public Integer min() {
        return minStack.peek();
    }
}










/**
 * class MinStack {
 * Stack<Integer> dataStack = new Stack<>();
 * Stack<Integer> minStack = new Stack();
 * Integer min = null;
 * <p>
 * public void push(Integer data) {
 * dataStack.push(data);
 * <p>
 * if (minStack.isEmpty() || data <= minStack.peek()) {
 * minStack.push(min);
 * }
 * }
 * <p>
 * public Integer pop() {
 * if (dataStack.peek() == minStack.peek()) {
 * minStack.pop();
 * }
 * return dataStack.pop();
 * }
 * <p>
 * public Integer top() {
 * return dataStack.peek();
 * }
 * <p>
 * public Integer min() {
 * return minStack.peek();
 * }
 * <p>
 * public boolean isEmpty() {
 * return dataStack.isEmpty();
 * }
 * }
 */


