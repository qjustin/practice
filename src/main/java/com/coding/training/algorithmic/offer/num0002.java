package com.coding.training.algorithmic.offer;

/**
 * 面试题04. 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 * 限制：
 * 0 <= n <= 1000
 * 0 <= m <= 1000
 * 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 * 通过次数11,301提交次数27,635
 * 思路：
 * 由于：每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序
 * 当目标值 < 右上角值， 列--
 * 当目标值 > 右上角值， 行--
 */
public class num0002 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        System.out.println(findInMatrix(arr, 4, 4, 20));
    }

    public static boolean findInMatrix(int[][] arr, int rows, int columns, int target) {
        boolean result = false;
        int m = 0, n = columns;

        while (m <= rows && n >= 0) {
            int current = arr[m][n];
            if (target == current) {
                result = true;
                System.out.println(String.format("[%s, %s] = %s", m, n, current));
                break;
            } else if (current > target) {
                n--;
            } else {
                m++;
            }
        }

        return result;
    }
}
