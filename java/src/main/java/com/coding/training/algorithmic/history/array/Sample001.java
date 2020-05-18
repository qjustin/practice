package com.coding.training.algorithmic.history.array;

/**
 * 买卖股票的最佳时机I
 * <p>
 * 假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。如果你最多只允许完成一次交易(例如,一次买卖股票),
 * 设计一个算法来找出最大利润。
 * 7, 1, 3, 6, 2
 * 4,4,6,1,1,4,2,5
 */
public class Sample001 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{4,4,6,1,1,4,2,5}));
    }

    public static int maxProfit(int[] stock) {
        if (stock.length < 2) return 0;

        int maxProfit = 0;
        int tmp = 0;

        for (int i = 0; i < stock.length - 1; i++) {
            for (int j = i + 1; j < stock.length; j++) {
                if (stock[i] < stock[j]) {
                    tmp = stock[j] - stock[i];

                    if (tmp > maxProfit) {
                        maxProfit = tmp;
                        System.out.println("i = " + i + ";j = " + j);
                    }
                }
            }
        }

        return maxProfit;
    }
}
