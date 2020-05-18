package com.coding.training.algorithmic.history.search;

/**
 *  查找旋转数组的最小元素
 */
public class Sample003 {
        /**
     * 6.2 查找旋转数组的最小元素（存在重复项）
     */
    public static int findMin(int[] arr) {
        int len = arr.length;
        if (len == 0) return -1;

        int low = 0;
        int high = len - 1;
        int pivot;

        while (low < high) {
            pivot = low + (high - low) / 2;

            if (arr[pivot] > arr[high])
                low = pivot + 1;
            else if (arr[pivot] < arr[high])    // else
                high = pivot;                   // high - pivot;
            else                                // 移除
                high--;                         // 移除
        }

        return arr[low];
    }
}
