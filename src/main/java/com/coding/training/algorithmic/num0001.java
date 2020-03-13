package com.coding.training.algorithmic;

import java.util.Arrays;

/**
 * 找出数组中重复的数字。
 * <p>
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 * <p>
 * 限制：
 * <p>
 * 2 <= n <= 100000
 * <p>
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 */
public class num0001 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3, 2};
        int[] records = new int[nums.length];
        Arrays.fill(records, 0);

        for (int i = 0; i < nums.length; i++) {
            records[nums[i]]++;
        }

        for (int i = 0; i < records.length; i++) {
            if (records[i] > 1) {
                System.out.println(String.format("%s, %s", i, records[i]));
            }
        }
    }
}
