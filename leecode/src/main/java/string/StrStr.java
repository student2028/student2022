package string;

/**
 * 实现 strStr() 函数。
 * <p>
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。*
 * 说明：
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 */
public class StrStr {

    public static void main(String[] args) {
        StrStr strStr = new StrStr();
        System.out.println(strStr.strStr("helloworld", "world"));

    }

    public int strStr(String haystack, String needle) {
        //暴力匹配
        int m = haystack.length();
        int n = needle.length();
        for (int i = 0; i <= m - n; i++) {
            int j = 0;
            for (; j < n; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j))
                 break;
            }
            if (j == n  ) return i;
        }
        return -1;
    }
}