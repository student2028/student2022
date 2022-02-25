package dp;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * 示例 1：
 * <p>
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 * <p>
 * 提示：
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-subsequence
 */
public class IsSubsequence {

    public static void main(String[] args) {
        IsSubsequence isSubsequence = new IsSubsequence();
        System.out.println(isSubsequence.isSubSequence("abc", "ahbgdc"));
        System.out.println(isSubsequence.isSubSequence2("abc", "ahbgdc"));
    }

    //判断s是否是t的子序列
    public boolean isSubSequence(String s, String t) {
        //思路 最长公共子序列的套路
        //dp[i][j] 表示字符串0-i,0-j的最长公共子串长度
        //if(s.charAt(i-1)==t.charAt(j-1)? dp[i][j] = d[i-1][j-1]+1 : dp[i][j-1];因为此处判断的是s是t的子串
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                dp[i][j] = s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1] + 1 : dp[i][j - 1];
            }
        }
        return dp[s.length()][t.length()] >= s.length();
    }

    //双指针思路
    //遍历s t 只要找到相同的 则s++ 如果不同s不动t动即可
    public boolean isSubSequence2(String s, String t) {
        int i = 0;
        int j = 0;
        while(i< s.length() && j < t.length() ) {
            if(s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == s.length();
    }

}