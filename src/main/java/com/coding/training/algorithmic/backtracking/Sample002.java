package com.coding.training.algorithmic.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * <p>
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n =3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * 看到这个题，是合格的括号，有n个左括号就有n个右括号，那通过回溯法可以解决。
 */
public class Sample002 {
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            findgenerateParenthesis(n, n, "", res);
            return res;
        }

        //left和right分别表示左右括号的个数
        public void findgenerateParenthesis(int left, int right, String out, List<String> res) {
            if (left < 0 || right < 0 || left > right) {  //left > right是")("的情况
                return;
            }
            if (left == 0 && right == 0) {
                res.add(out);
                return;
            }
            findgenerateParenthesis(left - 1, right, out + "(", res);//回溯
            findgenerateParenthesis(left, right - 1, out + ")", res);
        }
    }
}
