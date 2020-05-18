package com.coding.training.algorithmic.history.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码的字母组合
 *
 *题目描述
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意1不对应任何字母。
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class Sample001 {
    public List<String> letterCombinations(String digits) {
        // 数字与字母的对应关系
        String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new ArrayList<>();
        if(digits.isEmpty())
            return res;
        // 图中树的根节点
        res.add("");

        // 遍历输入的数字
        for(char c : digits.toCharArray()){
            res = combine(map[c-'0'], res);
        }

        return res;
    }

    // 根据数字组合字母
    private List<String> combine(String digits, List<String> list){
        List<String> res = new ArrayList<>();

        for(char c : digits.toCharArray()){
            for(String s : list){
                res.add(s+c);
            }
        }

        return res;
    }
}
