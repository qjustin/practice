package com.coding.training.algorithmic.dp;

/**
 * 跳跃游戏
 *
 * offer
 * 给定一个非负整数数组，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个位置。
 *
 * 示例1
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * 1
 * 2
 * 3
 * 示例2
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ，
 * 所以你永远不可能到达最后一个位置
 */
public class Sample006 {
    // 贪心算法
    public boolean canJump(int[] nums) {
        int curMaxStep = nums[0];
        int step = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > curMaxStep)
                return false;
            curMaxStep = Math.max(curMaxStep, i + nums[i]);
            if (curMaxStep >= nums.length - 1)
                return true;
        }
        return curMaxStep >= nums.length - 1;
    }

    // 贪婪算法
    public boolean canJump1(int[] nums) {
        int end = 0;
        for(int i = 0;i < nums.length - 1;i++){
            if(i <= end && i+nums[i] > end){
                end = i + nums[i];
            }
        }
        // 如果大于则说明可以到达最后一步
        return end >= nums.length-1;
    }
}
