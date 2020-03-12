package com.coding.training.algorithmic.array;

/**
 * 买卖股票的最佳时机III
 * 假设你有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
 * 设计一个算法来找到最大的利润。你最多可以完成两笔交易。
 */
public class Sample003 {
    public static void main(String[] args) {
        maxProfit(new int[]{9, 2, 3, 4, 1, 6, 8, 2});
    }

    public static void maxProfit(int[] stock) {
        if (stock.length < 2) return;

        int maxProfit = 0;
        int secondProfit = 0;
        int tmp = 0;

        for (int i = 0; i < stock.length - 1; i++) {
            for (int j = i + 1; j < stock.length; j++) {

                if (stock[j] > stock[i]) {
                    tmp = stock[j] - stock[i];

                    if (maxProfit <= tmp) {
                        if (maxProfit != 0) {
                            secondProfit = maxProfit;
                        }

                        maxProfit = tmp;
                    }
                }
            }
        }

        System.out.println("maxProfit=" + maxProfit + ";secondProfit=" + secondProfit);
    }
}
