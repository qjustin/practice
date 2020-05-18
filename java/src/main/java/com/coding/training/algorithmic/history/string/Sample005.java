package com.coding.training.algorithmic.history.string;

import java.util.Arrays;

/**
 * 字符串中的第一个唯一字符
 * <p>
 * s = "leetcode"
 * return 0.
 * <p>
 * s = "loveleetcode",
 * return 2.
 */
public class Sample005 {
    public static void main(String[] args) {
        System.out.println(singleChar("hhello"));
    }

    public static int singleChar(String str) {
        int[] records = new int[256];
        Arrays.fill(records, 0);

        for (int i = 0; i < str.length(); i++) {
            records[str.charAt(i)]++;
        }

        for (int i = 0; i < str.length(); i++) {
            if (records[str.charAt(i)] == 1) return i;
        }

        return -1;
    }
}
