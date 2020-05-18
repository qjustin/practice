package com.coding.training.algorithmic.history.string;

/**
 * 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * 1
 * 2
 * 3
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class Sample014 {
    public String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return s;
        }
        String res = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String k = s.substring(i, j);
                String rk = new StringBuffer(k).reverse().toString();
                if (k.equals(rk) && k.length() > res.length()) {
                    res = k;
                }
            }

        }
        return res;
    }
}
