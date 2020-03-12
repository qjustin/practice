package com.coding.training.algorithmic.string;

public class Sample017 {
    public static String minWindowIIII(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty() || s.length() < t.length()) {
            return "";
        }

        char[] tArr = t.toCharArray();
        int[] tCount = new int[256];
        for (char c : tArr) {
            tCount[c] += 1;
        }

        String res = "";
        char[] sArr = s.toCharArray();
        int sLen = sArr.length;
        int[] sCount = new int[256];

        int satisfy = 0;
        int satTarget = tArr.length;

        int minLen = sLen;

        for (int i = 0, j = 0; j < sLen; j++) {
            char cur = sArr[j];
            sCount[cur] += 1;
            if (sCount[cur] <= tCount[cur]) {
                satisfy++;
            }

            // 前移i
            while (i <= j && tCount[sArr[i]] < sCount[sArr[i]]) {
                sCount[sArr[i]] -= 1;
                i++;
            }

            if (satisfy == satTarget && j - i + 1 <= minLen) {
                res = s.substring(i, j + 1);
                minLen = j - i + 1;
            }
        }


        return res;
    }
}
