package com.coding.training.algorithmic.string;

/**
 * 151 反转字符串里的单词
 */
public class Sample016 {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        if (s == null || "".equals(s.trim())) {
            return "";
        }

        String[] str = s.trim().split(" +");
        for (int i = str.length - 1; i >= 0; i--) {
            if (i == 0) {
                sb.append(str[i]);
            } else {
                sb.append(str[i] + " ");
            }
        }

        return sb.toString();
    }
}
