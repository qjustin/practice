package com.coding.training.algorithmic.history.dp;

/**
 * https://blog.csdn.net/acecandy/article/details/83864763
 *
 * 斐波那契数列
 * 1 1 2 3 5 8 13
 */
public class Sample001 {

    public static long f5(int n) {
        if (n <= 0) {
            throw new RuntimeException("输入参数小于1");
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        long a = 1;
        long b = 1;
        long c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }


    /**
     * 数组缓存+顺序递推
     * 然而在f3方法中，我输入了n=10000试试，然后惊讶的发现——报错了，报了一个StackOverflowError栈溢出的错误。
     * 我们知道，当一个函数被Java程序调用的时候，就会在调用栈上分配栈帧。栈帧包含被调用函数的参数、局部变量和返回地址。
     * 返回地址指示了当函数执行完毕之后下一步该执行哪里。如果创建栈帧时没有内存空间，JVM就会抛出StackOverflowError。
     * 而递归就是无限制的调用自身函数，然后就栈溢出了。那我们能不能避免呢，因为我们是从后往前推的所以需要不停的递归，
     * 那如果我们从前往后推呢?

     * @param n
     * @return
     */
    public static long f4(int n) {
        if (n <= 0) {
            throw new RuntimeException("输入参数小于1");
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        long temp[] = new long[n + 1];
        temp[0] = 0;
        temp[1] = 1;
        temp[2] = 1;
        for (int i = 3; i <= n; ++i) {
            temp[i] = temp[i - 1] + temp[i - 2];
        }
        return temp[n];
    }

    /**
     * 公式解
     * @param n
     * @return
     */
    public static long f6(int n) {
        double result = 0;
        double temp = Math.sqrt(5.0);
        result = (Math.pow((1 + temp) / 2, n) - Math.pow((1 - temp) / 2, n)) / temp;
        return (long) result;
    }
}
