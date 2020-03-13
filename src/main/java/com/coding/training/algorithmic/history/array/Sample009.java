package com.coding.training.algorithmic.history.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和等于0 中等难度
 * <p>
 * 给定一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，
 * 使得a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 1. 先排序
 * 2.
 */
public class Sample009 {
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);//排序
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < num.length - 2; ++i) {
            if (i > 0 && num[i] == num[i - 1]) continue; //避免重复三元组
            int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
            while (lo < hi) {//有序数组找特定和的两元素，双指针算法
                if (num[lo] + num[hi] == sum) {
                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
                    while (lo < hi && num[lo] == num[lo + 1]) lo++;//避免重复三元组
                    while (lo < hi && num[hi] == num[hi - 1]) hi--;//避免重复三元组
                    lo++;
                    hi--;
                } else if (num[lo] + num[hi] < sum) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return res;
    }
}
