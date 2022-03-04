package dp;


import java.util.Arrays;

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

    //评论区中非常优秀的一个题解
    public int coinChange2(int[] coins, int amount) {
        if(coins.length == 0)
            return -1;

        //声明一个amount+1长度的数组dp，代表各个价值的钱包，第0个钱包可以容纳的总价值为0，其它全部初始化为无穷大
        //dp[j]代表当钱包的总价值为j时，所需要的最少硬币的个数
        int[] dp = new int[amount+1];
        Arrays.fill(dp,1,dp.length,Integer.MAX_VALUE);

        //i代表可以使用的硬币索引，i=2代表只在第0个，第1个，第2个这三个硬币中选择硬币
        for (int i = 0; i < coins.length; i++) {
            /**
             * 	当外层循环执行一次以后，说明在只使用前i-1个硬币的情况下，各个钱包的最少硬币个数已经得到，
             * 		有些钱包的值还是无穷大，说明在仅使用前i-1个硬币的情况下，不能凑出钱包的价值
             * 	现在开始再放入第i个硬币，要想放如w[i]，钱包的价值必须满足j>=w[i]，所以在开始放入第i个硬币时，j从w[i]开始
             */
            for (int j = coins[i]; j <= amount; j++) {
                /**
                 * 	如果钱包当前的价值j仅能允许放入一个w[i]，那么就要进行权衡，以获得更少的硬币数
                 * 		如果放入0个：此时钱包里面硬币的个数保持不变： v0=dp[j]
                 * 		如果放入1个：此时钱包里面硬币的个数为：		v1=dp[j-coins[i]]+1
                 * 		 【前提是dp[j-coins[i]]必须有值，如果dp[j-coins[i]]是无穷大，说明无法凑出j-coins[i]价值的钱包，
                 * 	              那么把w[i]放进去以后，自然也凑不出dp[j]的钱包】
                 * 	所以，此时当钱包价值为j时，里面的硬币数目为 dp[j]=min{v0,v1}
                 * 	如果钱包当前价值j能够放入2个w[i]，就要再进行一次权衡
                 * 		如果不放人第2个w[i]，此时钱包里面硬币数目为，v1=dp[j]=min{v0,v1}
                 * 		如果放入第2个w[i],  此时钱包里面硬币数目为，v2=dp[j-coins[i]]+1
                 * 	所以，当钱包的价值为j时，里面的硬币数目为dp[j]=min{v1,v2}=min{v0,v1,v2}
                 * 	钱包价值j能允许放入3个，4个.........w[i]，不断更新dp[j]，最后得到在仅使用前i个硬币的时候，每个钱包里的最少硬币数目
                 */
                if(dp[j-coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j-coins[i]]+1);
                }
            }
        }
        if(dp[amount] != Integer.MAX_VALUE)
            return dp[amount];
        return -1;
    }

}
