package dp;


/**
 * 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * <p>
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 做完 零钱况换 发现和这个几乎是一模一样的逻辑
 * 都是用最少的数 用最少的个数
 * coins 就是完全平方数 i*i <= n
 */
public class NumSquares {

    public static void main(String[] args) {
        NumSquares numSquares = new NumSquares();
        System.out.println(numSquares.numSquares(12));

    }

     public int numSquares(int n) {
        //定义dp[]数组 dp[i]表示  和为i的 完全平方数最小数量是dp[i]
        //物品就是 j*j 表示的完全平方数 j*j <= i
//        dp[j] 可以由dp[j - i * i]推出， dp[j - i * i] + 1 便可以凑成dp[j]。
//        此时我们要选择最小的dp[j]，所以递推公式：dp[j] = min(dp[j - i * i] + 1, dp[j]);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) { //遍历背包大小
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) { //遍历物品
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
