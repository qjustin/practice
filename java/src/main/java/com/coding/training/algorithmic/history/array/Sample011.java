package com.coding.training.algorithmic.history.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 字谜分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * ["eat", "tea", "tan", "ate", "nat", "bat"]
 * ,
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class Sample011 {
    //HashMap + ArrayList，对字母异位词进行分组
    //一个keyString对应一个ArrayList，归纳一组字母异位词
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<List<String>>();
        }
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char chr[] = s.toCharArray();
            Arrays.sort(chr);
            String keyString = String.valueOf(chr);
            if (!map.containsKey(keyString)) {
                map.put(keyString, new ArrayList<String>());
            }
            map.get(keyString).add(s);

        }
        return new ArrayList<List<String>>(map.values());
    }
}
