package string;

/**
 * 387. 字符串中的第一个唯一字符
 * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
 * 示例 1：
 * <p>
 * 输入: s = "leetcode"
 * 输出: 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 */
public class FirstUniqueChar {

    public static void main(String[] args) {
        FirstUniqueChar firstUniqueChar = new FirstUniqueChar();
        System.out.println(firstUniqueChar.firstUniqChar("leetcode"));

    }

    public int firstUniqChar(String s) {
        char[] freq = new char[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            freq[c - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            if (freq[chars[i] - 'a'] == 1) return i;
        }
        return -1;
    }

}
