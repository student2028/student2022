package dp;

/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * <p>
 * 示例 2:
 * <p>
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 * <p>
 * dp[i]：分拆数字i，可以得到的最大乘积为dp[i]。
 * 可以想 dp[i]最大乘积是怎么得到的呢？
 * <p>
 * 其实可以从1遍历j，然后有两种渠道得到dp[i].
 * 一个是j * (i - j) 直接相乘
 * 一个是j * dp[i - j]，相当于是拆分(i - j)，对这个拆分不理解的话，可以回想dp数组的定义。
 * ，j * (i - j) 是单纯的把整数拆分为两个数相乘，而j * dp[i - j]是拆分成两个以及两个以上的个数相乘。
 * 如果定义dp[i - j] * dp[j] 也是默认将一个数强制拆成4份以及4份以上了。
 * 所以递推公式：
 *
 */
public class IntegerBreak {

    public static void main(String[] args) {

        IntegerBreak test = new IntegerBreak();
        System.out.println(test.integerBreak(2));
        System.out.println(test.integerBreak(10));

    }

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        //初始化
        dp[2] = 1;
        for (int i = 3; i < n + 1; i++) {
            for (int j = 1; j < i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }
}
