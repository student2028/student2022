package dp;


/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
  */
public class CoinsChange {

    public static void main(String[] args) {

        int[] coins = new int[] {1,2,5};
        CoinsChange test = new CoinsChange();
        System.out.println(test.coinChange(coins, 11));
        int[] coins2 = new int[] {2};
        System.out.println(test.coinChange(coins2, 3));
    }

    /**
     * 完全背包问题
     * @param coins 币值数组 里面存储的是各种货币 10元5元类似的
     * @param amount 总数量  可以理解为背包需要达到的理想的价值数
     * 物品就是coins中的每一种货币
     * amount 就是背包里面装的物品的总值
     * dp[i] 表示凑足金额为i的最小货币数量
     * dp[i] 可以从dp[i - coins[i]]中推出来 表示不使用coins[i]的总额为i-coins[i]的最小货币数量
     *  dp[i] = min(dp[i-coins[j]]  + 1, dp[i]);
     *
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int max = Integer.MAX_VALUE;
        //初始化
        for (int i = 0; i < dp.length; i++) {
            dp[i] = max;
        }
        //当金额为0时值是0
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) { //遍历物品
            for (int j = coins[i]; j <= amount ; j++) {
                if(dp[j - coins[i]] != max) { //如果还是初始值 就不比了
                    dp[j] = Math.min(dp[j], dp[j- coins[i]] + 1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }

}
