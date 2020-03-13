package com.coding.training.algorithmic.history.search;

/**
 * leetCode(寻找峰值)-二分查找
 * offer：
 * 峰值元素是指其值大于左右相邻值的元素。
 *
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 *
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 *
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 *
 * 思路:
 *
 * 1.如果数组是严格递增或者严格递减，峰值在len(nums)-1或者0处；如果nums[0]比nums[1]大，nums[0]即是峰值;如果nums[len(nums)-1]比nums[len(nums)-2]大，nums[len(nums)-1]即是峰值
 * 2.排除1的情况，数组中间一定存在一个峰值，采用二分法判断峰值在那一边一定存在
 * 3.如果mid对应的数值比mid-1和mid+1处的数值都大，mid即使要找的结果
 * 4.如果mid对应的数值比mid-1小，那么区间[l,mid]一定有峰值
 * 5.如果mid对应的数值比mid+1小，那么区间[mid,r]一定有峰值
 *
 *
 * 这题要求局部最大值，又要用二分法
 *
 */
public class Sample001 {

    int findPeakElement(int[] nums) {
        int n = nums.length;
        if(n == 1) {
            return 0;
        }
        int left = 0, right = n - 1,mid = 0;
        while(left <= right) {
            mid = left + (right - left) / 2;
            if((mid == 0 || nums[mid] >= nums[mid - 1]) && (mid == n - 1 || nums[mid] >= nums[mid + 1])) {
                return mid;
            } else if(mid > 0 && nums[mid - 1] > nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return mid;
    }
}
