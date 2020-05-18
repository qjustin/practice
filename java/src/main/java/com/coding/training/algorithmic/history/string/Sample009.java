package com.coding.training.algorithmic.history.string;

/**
 * 实现strStr()
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * <p>
 * 解题思路
 * 取两个字符串的长度，l1,l2
 * 判断前者不小于后者，且后者不等于0，
 * 取长度的差，循环遍历，
 * 在haystack中取l2长度的字符，判断是否等于needle
 */
public class Sample009 {
    public int strStr(String haystack, String needle) {
        int l1 = haystack.length(), l2 = needle.length();
        if (l1 < l2) {
            return -1;
        } else if (l2 == 0) {
            return 0;
        }
        int threshold = l1 - l2;
        for (int i = 0; i <= threshold; i++) {
            if (haystack.substring(i, i + l2).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
