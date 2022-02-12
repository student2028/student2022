package dp;

/**
 * 最长回文子序列
 */
public class LongestPalindromeSubseq {

    /**
     * 还套用 之前做 最长回文子串 动规的套路 注意回文序列 不要求字符连续
     * dp[i][j] 表示字符串[i,j]之间最长的回文长度就是dp[i][j] 所以最后返回值就是dp[0][len-1]
     * 转移方程就是：dp[i][j] 如果s[i][j]是回文 则dp[i][j] = dp[i][j] + 2
     * 如果不相同，则从i+1 or j-1 中选一个最大的
     * 初始化呢？ dp[i][i] = 1
     * 如何进行遍历呢？ 根据状态转移方程 需要从下往上 从左往右
     * 自己画一画表格 找找感觉
     */
    public int longestPalindromeSubseq(String s) {
        int size = s.length();
        int[][] dp = new int[size][size];

        for (int i = size - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < size; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][size - 1];
    }

    public static void main(String[] args) {
        LongestPalindromeSubseq test = new LongestPalindromeSubseq();
        System.out.println(test.longestPalindromeSubseq("abbxbbba"));
        System.out.println(test.longestPalindromeSubseq("abcde"));

    }


}
