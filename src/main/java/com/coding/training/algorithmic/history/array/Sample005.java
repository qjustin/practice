package com.coding.training.algorithmic.history.array;

/**
 * 旋转数组
 * 将包含 n 个元素的数组向右旋转 k 步。
 * <p>
 * 例如，如果  n = 7 ,  k = 3，给定数组  [1,2,3,4,5,6,7]  ，向右旋转后的结果为 [5,6,7,1,2,3,4]。
 */
public class Sample005 {
    public static void main(String[] args) {
        reverseArray(new int[] {1,2,3,4,5,6,7}, 3);
    }

    public static void reverseArray(int[] arr, int k) {
        int[] tmpArray = new int[arr.length];
        int flag = arr.length - k;
        int i = flag;

        for (int j = 0; j < tmpArray.length; j++) {
            tmpArray[j] = arr[i++];
            System.out.print(arr[i -1] + " ");

            if (i == flag) break;
            if (i == arr.length) i = 0;
        }
    }
}
