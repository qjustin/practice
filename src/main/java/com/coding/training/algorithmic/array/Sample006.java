package com.coding.training.algorithmic.array;

/**
 * 从排序数组中删除重复项
 *
 * 小心处理第0个元素
 */
public class Sample006 {
    public static void main(String[] args) {
        int[] result = removeDuplicate(new int[]{1,1, 2, 3, 3, 4, 5, 6, 7, 7, 8, 8});

        for (int i = 0; i < result.length; i ++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] removeDuplicate(int[] array) {
        if (array.length < 2) return array;

        int[] result = new int[array.length];
        int prevIdx = 0;
        int index = 1;
        result[0] = array[0];

        for(int i = 0; i < array.length; i++) {
            if (array[prevIdx] != array[i]) {
                result[index++] = array[i];
                prevIdx = i;
            }
        }

        return result;
    }
}
