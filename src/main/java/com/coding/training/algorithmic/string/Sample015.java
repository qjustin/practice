package com.coding.training.algorithmic.string;

/**
 * Offer：递增的三元子序列
 * 给定一个未排序的数组，请判断这个数组中是否存在长度为3的递增的子序列。
 *
 * 正式的数学表达如下:
 *
 * 如果存在这样的 i, j, k, 且满足 0 ≤ i < j < k ≤ n-1，
 *
 * 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
 *
 * 要求算法时间复杂度为O(n)，空间复杂度为O(1) 。
 *
 * 示例:
 *
 * 输入 [1, 2, 3, 4, 5],
 *
 * 输出 true.
 *
 * 输入 [5, 4, 3, 2, 1],
 *
 * 输出 false.
 *
 * 分析
 * 思路有点类似动态规划的思想，维护一个二元组(first,second)，记录第i个元素之前的“最小”递增二元子序列（对后续元素的要求最低如[5,6,2,3,4]会更新[5,6]为[2,3]此时只要后续满足大于3就可以）
 *
 * 当nums[i]小于first时，更新first的值
 * 当nums[i]>first且nums[i]
 *
 * 正式的数学表达如下:
 * <p>
 * 如果存在这样的 *i, j, k, * 且满足 0 ≤ i < j < k ≤ n-1，
 * 使得 arr[i] < arr[j] < *arr[k] *，返回 true ; 否则返回 false 。
 */
public class Sample015 {
    public boolean increasingTriplet(int[] nums) {

        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        if (nums.length < 3) {
            return false;
        }

        for (int num : nums) {
            if (first > num) {
                first = num;
            } else if (first < num && second > num) {
                second = num;
            } else if (num > second) {
                return true;
            }
        }
        return false;
    }
}
