package com.coding.training.algorithmic.history.array;

/**
 * 矩阵置零
 * <p>
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 */
public class Sample010 {
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        boolean firstRowIsZero = false, firstColIsZero = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i != 0 && j != 0 && matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                } else if (matrix[i][j] == 0) {
                    firstRowIsZero = i == 0 ? true : firstRowIsZero;
                    firstColIsZero = j == 0 ? true : firstColIsZero;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        //第一列含0
        if (firstColIsZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        //第一行含0
        if (firstRowIsZero) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
    }
}
