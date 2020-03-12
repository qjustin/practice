package com.coding.training.algorithmic.array;

/**
 * 判断无序数组是否存在重复
 * 数组交集并并集 都可以用数组下标发，但需要注意 数据范围不超过数组长度
 */
public class Sample007 {

    public static void main(String[] args) {
        System.out.println(hasDuplicate(new int[]{3}));
    }

    public static boolean hasDuplicate(int[] array) {
        if (array.length < 2) return false;

        quickSort(array, 0, array.length - 1);

        int prevIdx = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[prevIdx] != array[i]) {
                prevIdx = i;
            } else {
                return false;
            }
        }

        return true;
    }

    public static void quickSort(int[] array, int low, int high) {
        if (array.length < 2) return;

        if (low < high) {
            int i = low;
            int j = high;
            int key = array[low];


            while (i < j) {
                // 这个while从数组的右边开始找，找到到一个小于key的就推出循环，否则向左收缩 减减
                while (i < j && array[j] >= key) {
                    j--;
                }
                array[i] = array[j];

                // 这个while从数组的右边开始找，找到到一个大于key的就推出循环，否则向右收缩 加加
                while (i < j && array[i] <= key) {
                    i++;
                }
                array[j] = array[i];

                array[i] = key;
            }

            quickSort(array, low, i - 1);
            quickSort(array, i + 1, high);
        }
    }
}
