package com.coding.training.algorithmic.stack;

import java.util.*;

/**
 * 有效括号
 * 算法
 * <p>
 * 初始化栈 S。
 * 一次处理表达式的每个括号。
 * 如果遇到开括号，我们只需将其推到栈上即可。这意味着我们将稍后处理它，
 * 让我们简单地转到前面的 子表达式。
 * 如果我们遇到一个闭括号，那么我们检查栈顶的元素。如果栈顶的元素是一个
 * 相同类型的 左括号，那么我们将它从栈中弹出并继续处理。否则，这意味着表达式无效。
 * 如果到最后我们剩下的栈中仍然有元素，那么这意味着表达式无效。
 */
public class Sample004 {

    public Map<Character, Character> map = null;

    public Sample004() {
        map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
    }

    public boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (map.containsKey(c)) {
                Character topElement = stack.isEmpty() ?  '#' : stack.pop();

                if (topElement != map.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
    public static void main(String[] args) {
        System.out.println(new Sample004().isValid("{([]])}"));
    }
}
