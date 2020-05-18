package com.coding.training.algorithmic.history.array;

/**
 * array.008 只出现一次的数字
 */
public class Sample008 {
    /**
     * Q:
     * 输入是否有序
     * 值范围是否超出数组长度
     */

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{1, 3, 2, 4, 2, 9, 1, 7, 7, 9, 3}));
    }

    public static int singleNumber(int[] array) {
        int result = 0;
        quickSort(array, 0, array.length - 1);

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] != array[i + 1]) {
                result = array[i];
                break;
            }
        }

        return result;
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int i = low;
            int j = high;
            int pivot = array[low];

            while (i < j) {
                while (i < j && pivot <= array[j]) {
                    j--;
                }
                array[i] = array[j];
                while (i < j && pivot >= array[i]) {
                    i++;
                }
                array[j] = array[i];
                array[i] = pivot;

                quickSort(array, low, i - 1);
                quickSort(array, i + 1, high);
            }
        }
    }
}
