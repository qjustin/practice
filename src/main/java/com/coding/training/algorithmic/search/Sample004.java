package com.coding.training.algorithmic.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间
 * 参考：
 * <p>
 * https://leetcode-cn.com/problems/merge-intervals/solution/pai-xu-by-powcai
 * <p>
 * 这道题很好理解，但有些操作我第一次见，所以这里直接copy了参考里的代码。
 * <p>
 * 思路：
 * <p>
 * 1. 首先将数组按照每个内层[ , ]的第一个元素升序排列。
 * <p>
 *   Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
 * <p>
 *   这句代码这样理解：
 * <p>
 *       将interval按照(a, b) -> a[0] - b[0]这个规则来排序。
 * <p>
 *       而这个规则是，a和b分别代表一个[ , ]，a[0]和b[0]分别代表[ , ]的第一个值。
 * <p>
 *           当a[0] - b[0]>0，相当于交换二者的位置；
 * <p>
 *           当a[0] - b[0]=0，不做实际操作；
 * <p>
 *           当a[0] - b[0]<0，相当于没有操作。
 * <p>
 * 2. 比如相邻的数组a[1,4]和b[2,5]，当我们发现a[1]>b[0]时，两个数组表示的范围存在重合，数组的左值一定是a[0]（更小的值做范围起点），而右值则为a[1] b[1]二者中更大的值。
 * <p>
 *  
 * <p>
 * 最后解释一下res.toArray(new int[0][]);
 * <p>
 * toArray(T)方法，T参数表示将res按照T模板转换，new int[0][]用最少的内存提供了二维数组模板。
 * <p>
 * 还有改成return res.toArray(new int[5][]);后输出错误：
 * <p>
 * 输入
 * <p>
 * [[1,3],[2,6],[8,10],[15,18]]
 * <p>
 * 输出
 * <p>
 * [[1,6],[8,10],[15,18],null,null]
 */
public class Sample004 {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0 || intervals == null) return res.toArray(new int[0][]);
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            //可以用连续的不止两个数组重合，所以用while循环
            while (i < intervals.length - 1 && intervals[i][0] <= right) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            res.add(new int[]{left, right});
            i++;
        }
        return res.toArray(new int[0][]);
    }
}
