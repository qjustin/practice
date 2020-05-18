package com.coding.training.algorithmic.history.search;

/**
 * 在旋转排序数组中搜索
 */
public class Sample002 {
        /**
     * 7. 在旋转排序数组中搜索 (存在重复项)
     */
    public static int search(int[] arr, int target) {
        int len = arr.length;
        if (len == 0) return -1;

        int low = 0;
        int high = len - 1;
        int pivot;

        while (low <= high) {
            pivot = low + (high - low) / 2;
            if (arr[pivot] == target) {
                return pivot;
            } else if (arr[pivot] > arr[high]) {                    // } else if(nums[low] <= nums[pivot]) {
                if (target < arr[pivot] && target >= arr[low])
                    high = pivot;                                   // high = pivot - 1;
                else
                    low = pivot + 1;
            } else if (arr[pivot] < arr[high]) {                     // } else if (arr[pivot] <= arr[high]) {
                if (target > arr[pivot] && target <= arr[high])
                    low = pivot + 1;
                else
                    high = pivot;                                   // high = pivot - 1;
            } else {                                                // 移除
                high--;                                             // 移除
            }                                                       // 移除
        }

        return -1;
    }
}
