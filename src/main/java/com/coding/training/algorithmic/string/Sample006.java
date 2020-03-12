package com.coding.training.algorithmic.string;

import java.util.Arrays;

/**
 * 有效的字母异位词
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 */
public class Sample006 {

    public static void main(String[] args) {
        System.out.println(anagramWord("car", "rat"));
    }

    public static boolean anagramWord(String str1, String str2){
        if (str1 == null || str2 == null || str1.length() != str2.length()) return false;

        int[] records = new int[256];
        Arrays.fill(records, 0);

        for(int i = 0; i < str1.length(); i++) {
            records[str1.charAt(i)]++;
            records[str2.charAt(i)]++;
        }

        for(int i = 0; i < str1.length(); i++) {
            if (records[str1.charAt(i)] % 2 != 0) return false;
        }

        return true;
    }
}
