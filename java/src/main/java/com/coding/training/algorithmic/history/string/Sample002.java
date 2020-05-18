package com.coding.training.algorithmic.history.string;

import java.util.Stack;

/**
 * 字符串解码：
 * 3[a]2[bc], 返回aaabcbc
 * 3[a2[c]], 返回accaccacc
 * 2[abc]3[cd]ef, 返回abcabccdcdcdef
 */
public class Sample002 {
    public static void main(String[] args){
        System.out.println(decodeString("3[a2[c]]"));
    }

    public static String decodeString(String s) {
        String res = "";
        Stack<String> strstk = new Stack<>();
        Stack<Integer> coustk = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int count = 0;
                while (Character.isDigit(s.charAt(i))) {
                    count = count * 10 + s.charAt(i) - '0';
                    i++;
                }
                i--;
                coustk.push(count);
            } else if (c == '[') {   /* 进入到更内一层, 将外层 String 入栈, res = "", 用于记录当前层  */
                strstk.push(res);
                res = "";
            } else if (c == ']') {   /* 遇到 ']',将外层 String 取出, 并将当前层 res 累加 count次 */
                int count = coustk.pop();
                StringBuilder strsb = new StringBuilder(strstk.pop());
                while (count != 0) {
                    strsb.append(res);
                    count--;
                }
                res = strsb.toString();
            } else res += c;
        }
        return res;
    }
}
