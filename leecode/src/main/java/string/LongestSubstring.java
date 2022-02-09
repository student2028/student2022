package string;

import java.util.HashMap;
import java.util.Map;

/**
 * 3 leecode num
 *给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */
public class LongestSubstring {

    //参考答案
    public static  int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "abcabcbb";
        int a = lengthOfLongestSubstring(str);
        System.out.println(a);
        System.out.println(lengthOfLongestSubstring("bbbbbbb"));
    }

    /**
     * 思路1.直观的想法
     * 扫描字符串，存储到hashset里面，如果遇到相同的，则存一个长度，清空再来
     * 思路2.使用hashMap key存储字符，值存储下标 + 1
     * 如果不存在 就put到hashmap中去，如果存在的时候怎么办？
     * 直接从hashmap中取到一个值 重置 一下start 再次开始即可
     * 例子 abcdecqt  重复的是c c第一次存的时候是3 :2+1 后面再遇到c的时候，我们直接从d开始就可以了，
     * 这样比第一种思路有一些优化
     */



}
