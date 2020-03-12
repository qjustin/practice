package com.coding.training.algorithmic.string;

/**
 * 整数反转
 * Example 1:
 * Input: 123
 * Output: 321
 * <p>
 * Example 2:
 * Input: -123
 * Output: -321
 * <p>
 * Example 3:
 * Input: 120
 * Output: 21
 *
 * 关键是溢出检查
 */
public class Sample004 {
    public static void main(String[] args) {
        System.out.println(reverse(-990820170));
    }

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
