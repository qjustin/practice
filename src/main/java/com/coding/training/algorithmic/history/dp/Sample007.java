package com.coding.training.algorithmic.history.dp;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 * 示例 1:
 *
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 * 示例 2:
 *
 * 输入: m = 7, n = 3
 * 输出: 28
 */
public class Sample007 {
    // 时间复杂度为O(m*n） 空间复杂度为O(m*n）
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 1;
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    // 时间复杂度不变O(m*n），空间复杂度变小O(n)。
    public int uniquePaths1(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j =0 ; j < n; j++) {
                if(i == 0 ){
                    dp[j] += 1;
                }
                if(i != 0 && j != 0){
                    dp[j] += dp[j-1];
                }
            }
        }
        return dp[dp.length-1];
    }
}
