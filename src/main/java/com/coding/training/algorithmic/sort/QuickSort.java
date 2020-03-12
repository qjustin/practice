package com.coding.training.algorithmic.sort;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5, 1, 23, 6, 78, 34, 23, 4, 5, 78, 34, 65, 32, 65, 76, 32, 76, 1, 9};

        sort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int left = low;
            int right = high;
            int pivot = arr[left];

            while (left < right) {
                while (left < right && arr[right] >= pivot) {
                    right--;
                }
                arr[left] = arr[right];
                while (left < right && arr[left] <= pivot) {
                    left++;
                }
                arr[right] = arr[left];
                arr[left] = pivot;
            }

            sort(arr, low, left - 1);
            sort(arr, left + 1, high);
        }
    }



























/*
    public static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int i = low;
            int j = high;
            int pivot = arr[low];

            while (i < j) { // 此处的while循环结束，则完成了元素key的位置调整
                while (i < j && pivot <= arr[j]) {
                    j--;
                }
                arr[i] = arr[j];
                while (i < j && pivot >= arr[i]) {
                    i++;
                }
                arr[j] = arr[i];
                arr[i] = pivot;  //此处不可遗漏
            }

            // i - 1, i + 1, 因为比pivot小的都在左边，比pivot大的都在右边边，因此这里以i为中心分左右
            // 但是左右两边还要继续排序，因为每次递归只从左边和右边各找出一个进行交换
            quickSort(arr, low, i - 1);
            quickSort(arr, i + 1, high);
        }
    }
*/
}
