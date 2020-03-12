package com.coding.training.algorithmic.sort;

import java.util.*;

/**
 * 347-前K个高频元素
 *
 * 题目描述
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 解题思路
 * 采用桶排序。所谓桶排序就是设置若干个带下标的桶，每个桶的下标就是桶内元素出现的次数。
 *
 * 想要找到 topk，只需要从后往前数 k 个元素出来就可以了。
 */
public class Sample001 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // 统计元素的频率
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 遍历map，用最小堆保存频率最大的k个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });

        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }

        // 取出最小堆中的元素
        List<Integer> ret = new ArrayList<>();
        while (!pq.isEmpty()) {
            ret.add(pq.remove());
        }

        return ret;
    }
}
