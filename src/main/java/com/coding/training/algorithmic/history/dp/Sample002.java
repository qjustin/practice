package com.coding.training.algorithmic.history.dp;

/**
 * 算法实现之爬楼梯
 * 原创handy周 最后发布于2019-03-01 13:21:19 阅读数 273  收藏
 * 展开
 * 概述
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 解决方案
 * 下这个问题如果没有思路，我们可以通过简单的归纳法在总结出规律
 *
 * 只有1个台阶的时候  1  总共有1种走法
 * 只有2个台阶的时候  2  总共有2种走法
 * 只有3个台阶的时候  3  总共有3种走法
 * 只有4个台阶的时候  4  总共有5种走法
 * 只有5个台阶的时候  5  总共有8种走法

 * 可以看到规律了吗？有没有很像我们数学中学习的斐波那契数列，基本规律就是
 *
 * f(1) = 1;
 * f(2) = 2;
 * f(n) = f(n-1) + f(n-2)  //(n>2)
 */
public class Sample002 {
    public int jumpFloor(int n) {
        if (n <= 0) {
            return -1;
        }
        if (n == 1 || n == 2) {
            return n;
        } else {
            int one = 1;
            int two = 2;
            int result = 0;
            for (int i = 2; i < n; i++) {
                result = one + two;
                one = two;
                two = result;
            }
            return result;
        }
    }
}
