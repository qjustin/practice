package com.coding.training.algorithmic.offer;

/**
 * 面试题11. 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，
 * 输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 * 示例 1：
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * 输入：[2,2,2,0,1]
 * 输出：0
 * 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * 思路：
 * 1. 升序旋转数组中我们选取的中间位置pivot只有两种情况，要么位于前端数组，要么位于后端数组
 * 6,7,8,9,    1,2,3,4,5
 * 前端数组     后端数组
 * 2. 当pivot位于前端数组时：pivot 一定 >= low
 * 5,6,7,8,      9,    1,2,3,4
 * low           pivot
 * 3. 当pivot位于后端数组时：pivot 一定 <= low
 * 6,7,8,9,    1,      2,3,4,5
 * low         pivot
 * 注意：
 * 升序选择high，降序选择low作为参考，因为判断pivot 在前段还是后段会有区别
 */
public class Num0009 {
    public static void main(String[] args) {
        // 只有一个数字
        int[] arr = new int[]{0};
        System.out.println(searchMinValue(arr));
        // 升序不反转数组
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(searchMinValue(arr));
        // 无重复数字
        arr = new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3};
        System.out.println(searchMinValue(arr));
        // 有重复数字，重复数字刚好是最小数字
        arr = new int[]{8, 9, 1, 1, 3, 4, 5, 6, 7};
        System.out.println(searchMinValue(arr));
        // 有重复数字，单不是最小的的那个重复
        arr = new int[]{6, 7, 8, 9, 1, 2, 3, 4, 4};
        System.out.println(searchMinValue(arr));
        // 有重复刚好第一个和最有一个重复
        arr = new int[]{2, 1, 2, 2, 2, 2, 2, 2, 2};
        System.out.println(searchMinValue(arr));
        //所有元素都相等
        arr = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(searchMinValue(arr));
    }

    public static int searchMinValue(int[] arr) {
        if (arr.length == 0) return -1;
        if (arr.length == 1) return 0;

        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int pivot = low + (high - low) / 2;
            /**
             * 判断最小值在那前段数组还是后段数组，升序选择high，降序选择low作为参考，因为判断pivot 在前段还是后段会有区别
             *  pivot > low, 则 pivot 在前段数组
             *  pivot < low, 则 pivot 在后段数组
             */
            if (arr[pivot] > arr[high]) {
                low = pivot + 1;
            } else if (arr[pivot] < arr[high]) {
                high = pivot;
            } else {
                high--;
            }
        }

        return high;
    }
}
