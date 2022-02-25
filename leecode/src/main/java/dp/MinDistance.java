package dp;

/***
 * 583. 两个字符串的删除操作
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 * 每步 可以删除任意一个字符串中的一个字符。
 * 示例 1：
 *
 * 输入: word1 = "sea", word2 = "eat"
 * 输出: 2
 * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
 *
 * 示例  2:
 *
 * 输入：word1 = "leetcode", word2 = "etco"
 * 输出：4
 * 提示：
 *     1 <= word1.length, word2.length <= 500
 *     word1 和 word2 只包含小写英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings
 * 思路 使用最长公共子序列 然后 用两个字符串的长度之和 减去这个值*2 即可
 *
 */
public class MinDistance {

    public static void main(String[] args) {
        MinDistance minDistance = new MinDistance();
        System.out.println(minDistance.minDistance("sea","eat"));
        System.out.println(minDistance.minDistance("leetcode","etco"));

    }

    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1+1][n2+1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2 ; j++) {
                dp[i][j] = word1.charAt(i-1) == word2.charAt(j-1) ? dp[i-1][j-1] +  1 : Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return n1 + n2 - 2 * dp[n1][n2];
    }
}
