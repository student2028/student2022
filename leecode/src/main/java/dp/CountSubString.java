package dp;

/**
 * leecode 647 回文子串
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * 示例 1：
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * <p>
 * 示例 2：
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * 提示：
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 */
public class CountSubString {

    public static void main(String[] args) {
        CountSubString countSubString = new CountSubString();
        System.out.println(countSubString.countSubString("abc"));
        System.out.println(countSubString.countSubString("aaa"));
    }

    //dp方法
    //dp[i][j] boolean 表示[i][j]是一个 i.j是否是一个回文
    //如果s[i]==s[j] 并且(j-i<=1 || dp[i+1][j-1] 则dp[i][j]=true
    //遍历顺序 因为 【i+1】[j-1] 推出i 所以
    //所以一定要从下到上，从左到右遍历，这样保证dp[i + 1][j - 1]都是经过计算的。
    //i 从大到小 j从i到size 遍历的顺序 因为j大于等于i才有意义
    public int countSubString(String s) {
        int res = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    res++;
                }
            }
        }
        return res;
    }
}
