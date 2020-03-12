package com.coding.training.algorithmic.string;

/**
 * 验证回文字符串
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this 题目, we define empty string as valid palindrome.
 *
 * Example 1:
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 *
 * Input: "race a car"
 * Output: false
 */
public class Sample007 {
    public static void main(String[] args) {
        System.out.println(isPalindromeString("race a car"));
    }

    public static boolean isPalindromeString(String str) {
        if (str == null || str.length() < 2) return false;

        String newStr = str.toLowerCase();
        char[] arr = newStr.toCharArray();

        int leftIndex = 0;
        int rightIndex = str.length() - 1;

        while(leftIndex < rightIndex) {
            while(!(arr[leftIndex] >= 'a' && arr[leftIndex] <= 'z') && leftIndex < rightIndex) {
                leftIndex++;
            }
            while(!(arr[rightIndex] >= 'a' && arr[rightIndex] <= 'z') && rightIndex > leftIndex) {
                rightIndex--;
            }

            if (arr[leftIndex] != arr[rightIndex]) return false;

            leftIndex++;
            rightIndex--;
        }

        return true;
    }
}
