package com.coding.training.algorithmic.history.sort;

/**
 * https://www.cnblogs.com/acm-bingzi/p/mergesort.html
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{99, 2, 3, 5, 1, 23, 6, 78, 34, 23, 4, 5, 78, 34, 65, 32, 65, 76, 32, 76, 1, 9};

        sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    public static void sort(int[] arr) {
        split(arr, new int[arr.length], 0, arr.length - 1);
    }

    public static void split(int[] arr, int[] tmp, int low, int high) {
        if (low < high) {
            int pivot = low + (high - low) / 2;

            split(arr, tmp, low, pivot);
            split(arr, tmp, pivot + 1, high);
            merge(arr, tmp, low, pivot, high);
        }
    }

    public static void merge(int[] arr, int[] tmp, int low, int pivot, int high) {
        int left = low;
        int right = pivot + 1;
        int index = 0;

        while(left <= pivot && right <= high) {
            if (arr[left] <= arr[right]) {
                tmp[index++] = arr[left++];
            } else {
                tmp[index++] = arr[right++];
            }
        }

        while (left <= pivot) {
            tmp[index++] = arr[left++];
        }

        while (right <= high) {
            tmp[index++] = arr[right++];
        }

        index = 0;
        while (low <= high) {
            arr[low++] = tmp[index++];
        }
    }




































































/*
    public static void recursionMergeSort(int[] arr, int low, int high, int[] tmp) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            // 递归拆分左边
            recursionMergeSort(arr, low, mid, tmp);
            // 递归拆分右边， 注意high=1 哦
            recursionMergeSort(arr, mid + 1, high, tmp);
            // 合并
            merge(arr, low, mid, high, tmp);
        }
    }

    public static void nonRecursionMergeSort(int[] arr, int[] tmp) {
        for (int i = 1; i <= arr.length; i *= 2) {
            for (int j = 0; j + i <= arr.length; j += i * 2) {
                // Math.min 的目的是处理 整个待排序数组为奇数的情况
                // merge(array, j, j + i - 1, Math.min(j + 2 * i - 1, array.length - 1), tmp);
                int low = j;
                int mid = j + i - 1;
                int high = Math.min(j + 2 * i - 1, arr.length - 1);

                merge(arr, low, mid, high, tmp);
            }
        }
    }

    public static void merge(int[] arr, int low, int mid, int high, int[] tmp) {
        // 设置左边与右边的起始位置
        int leftIdx = low;
        int rightIdx = mid + 1;
        int tmpIdx = 0;

        // 边界判断
        while (leftIdx <= mid && rightIdx <= high) {
            // 数组有序，分别拿出各自最小的比较
            if (arr[leftIdx] <= arr[rightIdx]) {
                tmp[tmpIdx++] = arr[leftIdx++];
            } else {
                tmp[tmpIdx++] = arr[rightIdx++];
            }
        }

        // 将左边剩下的元素放入数组
        while (leftIdx <= mid) {
            tmp[tmpIdx++] = arr[leftIdx++];
        }
        // 将右边剩下的元素放入数组
        while (rightIdx <= high) {
            tmp[tmpIdx++] = arr[rightIdx++];
        }

        // 回填
        tmpIdx = 0;
        while (low <= high) {
            arr[low++] = tmp[tmpIdx++];
        }
    }
*/
}
