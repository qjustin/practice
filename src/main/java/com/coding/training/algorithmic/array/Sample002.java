package com.coding.training.algorithmic.array;

/**
 * 122. 买卖股票的最佳时机 II（什么时机买与卖能获得最大利润）
 * <p>
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class Sample002 {

    public static void main(String[] str) {
        System.out.println(maxProfit(new int[]{7, 1, 3, 6, 2}));
    }

    public static int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;

        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
                System.out.println("i = " + prices[i] + ";i - 1 = " + prices[i - 1]);
        }
        return maxprofit;
    }
}
