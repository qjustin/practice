package com.coding.training.algorithmic.dp;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2:
 *
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 分析：
 * 这是一道动态规划的题目，这个过程的最大值用一个数组来保存。
 *
 * 1、当只有1间房的时候，我们没有选择，只能选择抢劫这间房；
 *
 * 2、当有两间房的时候，我们可以比较哪间房里面的钱多，抢劫钱多的房间就好了；
 *
 * 3、当有三间房的时候，有两种方案，抢1，3号房间/抢2号房间的，比较一下哪种方案多；
 *
 * 4、当有四间房的时候，因为我们已经知道了1，2，3间房时能获取到的最大金额，所以我们只需要将抢第四间房的金额数加上抢第二间房能获取到的最大金额数来和第三间房进行比较，取最大值就好了。
 *
 *
 * dp[i] = max(dp[i-1],dp[i-2] + nums[i]);
 */
public class Sample005 {

    public int rob(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }
        //上述判断是将最简单的几种情况先计算好，也是为了初始化momey[0] 和 money[1]

        // 定义一个数组，用来保存打劫 0 - (len-1) 家最大能打劫到的金额数
        int[] money = new int[len];
        money[0] = nums[0];
        money[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            money[i] = Math.max(money[i - 2] + nums[i], money[i - 1]);
        }
        return money[len - 1];
    }
}
