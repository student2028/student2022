package dp;

/**
 * 编辑距离 lee72
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 示例 1： 输入：word1 = "horse", word2 = "ros" 输出：3 解释： horse -> rorse (将 'h' 替换为 'r') rorse -> rose (删除 'r') rose -> ros (删除 'e')
 * <p>
 * 示例 2： 输入：word1 = "intention", word2 = "execution" 输出：5 解释： intention -> inention (删除 't') inention -> enention (将 'i' 替换为 'e') enention -> exention (将 'n' 替换为 'x') exention -> exection (将 'n' 替换为 'c') exection -> execution (插入 'u')
 * <p>
 * 提示：
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 * #
 * 思路
 */
public class EditDistance {

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.minDistance("horse", "ros"));
        System.out.println(editDistance.minDistance("intention", "execution"));

    }

    //dp[i][j] 表示 word1[0..i] 转成 word2[0..j] 需要的最小操作次数
    //if word1[i-1] == word2[j-1]  则 dp[i][j] = dp[i-1][j-1]
    //or else 则从前后上下推过来 取最小值
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 0; i <= n1; i++) {
            dp[i][0] = i;//从i字符串到空串 需要i步操作
        }
        for (int j = 0; j <= n2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[n1][n2];
    }
}
