package com.coding.training.algorithmic.history.dp;

/**
 * https://www.cnblogs.com/xuyiqing/p/8660927.html
 *
 * 最大子序列和的四种算法（Java）
 * 问题描述
 *
 * 给定（可能有负数）整数a(1)、a(2)、……a(n)，求 a(1)+a(2)+……+a(j)的最大值。为方便起见，若所有的整数为负数，则最大子序列和为0.
 *
 * 也就是：在一系列整数中，找出连续的若干个整数，这若干个整数之和 最大。
 * 这种方式很巧妙，不易想出，需要有很深编程技术的程序员才能想到
 */
public class Sample004 {
    private static int maxSubSum4(int[] a) {
        int maxSum = 0;
        int thisSum = 0;
        for (int i = 0; i < a.length; i++) {
            thisSum += a[i];
            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
            return maxSum;
        }
        return 0;
    }


//    private static int maxSubSum5(int[] a) {
//        int a[] = {3, 4, -2, -9, -10, 8, -1, 22};
//        int sum = 0;
//        for (int i = 0; i < a.length; i++) {
//            sum += a[i];
//            if (sum < 0) {
//                sum = 0;
//            }
//        }
//
//       return sum;
//    }
}
