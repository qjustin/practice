package com.coding.training.algorithmic.offer;

import java.util.Arrays;

/**
 * W
 * 面试题12. 矩阵中的路径
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，
 * 每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 * 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 * 提示：
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/
 * 通过次数6,787提交次数15,053
 * 思路：
 * 1. 先通过循环找到第一个元素currX，currY的坐标
 * 以currX，currY为圆心，上下左右找
 * 当有两个路径可选时，需要递归实现
 */
public class Num0010 {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'a', 'b', 'c', 'e'},
                {'s', 'f', 'c', 's'},
                {'a', 'd', 'e', 'e'}};
        System.out.println(exist(board, "asa"));
    }

    public static boolean exist(char[][] board, String word) {
        int maxRows = board.length - 1;
        int maxCols = board[0].length - 1;
        int index = 0;
        int firstChar = word.charAt(0);
        char[][] flags = new char[maxRows][maxCols];

        for (int i = 0; i <= maxRows; i++) {
            for (int j = 0; j <= maxCols; j++) {
                if (board[i][j] == firstChar) {
                    if (getTargetChar(board, maxRows, maxCols, i, j, word, index, flags)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean getTargetChar(char[][] board, int maxRows, int maxCols, int currX, int currY, String word, int index, char[][] flags) {
        if (currX < 0 || currX >= maxRows ||
                currY < 0 || currY >= maxCols ||
                board[currX][currY] != word.charAt(index) ||
                flags[currX][currY] == '1') {
            return false;
        }

        if (index == word.length() - 1) {
            return true;
        }

        flags[currX][currY] = '1';

        if (getTargetChar(board, maxRows, maxCols, currX - 1, currY, word, index + 1, flags)
                || getTargetChar(board, maxRows, maxCols, currX + 1, currY, word, index + 1, flags)
                || getTargetChar(board, maxRows, maxCols, currX, currY - 1, word, index + 1, flags)
                || getTargetChar(board, maxRows, maxCols, currX, currY + 1, word, index + 1, flags)
        ) {
            return true;
        }

        flags[currX][currY] = '0';
        return false;
    }

//    public static boolean getTargetChar(char[][] board, char target, int currX, int currY, int maxRows, int maxCols, String word, int index) {
//        System.out.println(target);
//        int x = currX;
//        int y = currY;
//        int left = currX - 1 >= 0 ? currX - 1 : 0;
//        int right = currX + 1 <= maxRows ? currX + 1 : maxRows;
//        int up = currY - 1 >= 0 ? currY - 1 : 0;
//        int down = currY + 1 <= maxCols ? currY + 1 : maxCols;
//        boolean result = false;
//
//        if (board[left][currY] != '1' && board[left][currY] == target) {
//            x = left;
//        }
//        if (board[right][currY] != '1' && board[right][currY] == target) {
//            x = right;
//        }
//        if (board[currX][up] != '1' && board[currX][up] == target) {
//            y = up;
//        }
//        if (board[currX][down] != '1' && board[currX][down] == target) {
//            y = down;
//        }
//
//        // 没有找到
//        if (currX == x && currY == y) {
//            return false;
//        } else if (index < word.length() - 1) {
//            board[currX][currY] = '1';
//            index++;
//            return getTargetChar(board, word.charAt(index), x, y, maxRows, maxCols, word, index);
//        } else {
//            return true;
//        }
//    }
}
