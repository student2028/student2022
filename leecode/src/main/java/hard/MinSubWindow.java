package hard;

import array.MinSubArrayLen;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：
 *
 *     对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 *     如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 */
public class MinSubWindow {

    public static void main(String[] args) {
        MinSubWindow test = new MinSubWindow();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(test.minWindow(s,t));
    }

    //滑动窗口法 labuladong
    public String minWindow(String s, String t) {
        if(t.length() > s.length()) return "";
        Map<Character,Integer> window = new HashMap<>();
        Map<Character,Integer> tmap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i), 0) + 1);
        }
        int start = 0;
        int len = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int count = 0;

        while(right < s.length()) {
            char c = s.charAt(right);
            right ++;
            if(tmap.containsKey(c)) {
                window.put(c, window.getOrDefault(c,0) + 1);
                if(tmap.get(c).equals(window.get(c))) count++;
            }

            //如果t中的都找到了 则收缩left
            while(count == tmap.size()) {
                //更新窗口
                if(right - left < len){
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                left ++;
                if(tmap.containsKey(d)) {
                    if(window.get(d).equals(tmap.get(d))) count--;
                    window.put(d, window.get(d) - 1);
                }
            }

        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

}