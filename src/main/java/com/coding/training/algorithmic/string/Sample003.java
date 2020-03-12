package com.coding.training.algorithmic.string;

/**
 * 反转字符串
 */
public class Sample003 {

    public static void main(String[] args) {
        System.out.println(reverseString("hello"));
    }

    public static String reverseString(String str) {
        if (str.length() < 2) return str;

        char[] arr = str.toCharArray();
        int leftIndex = 0;
        int rightIndex = arr.length - 1;

        while(leftIndex < rightIndex) {
            char tmp = arr[leftIndex];
            arr[leftIndex] = arr[rightIndex];
            arr[rightIndex] = tmp;

            leftIndex++;
            rightIndex--;
        }

        return new String(arr);
    }
}
