package com.coding.training.algorithmic;

/**
 * 面试题05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 * 限制：
 * 0 <= s 的长度 <= 10000
 * 通过次数12,040提交次数15,437
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 */
public class num0003 {
    public static void main(String[] args) {
        System.out.println(replaceSpace("We are happy."));
    }

    public static String replaceSpace(String str) {
        char[] arr = str.toCharArray();
        int spaceCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                spaceCount++;
            }
        }
        char[] result = new char[str.length() + spaceCount * 2 + 1];
        for (int i = 0, j = 0; i < arr.length; i++, j++) {
            if (arr[i] == ' ') {
                result[j] = '%';
                result[j + 1] = '2';
                result[j + 2] = '0';
                j += 2;
            } else {
                result[j] = arr[i];
            }
        }

        return new String(result);
    }
}
