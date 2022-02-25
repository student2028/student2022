package dp;

/**
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。
 * 如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * 示例 1：
 * <p>
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 * <p>
 * 示例 2：
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 * 提示：
 * 1 <= prices.length <= 5 * 104
 * 1 <= prices[i] < 5 * 104
 * 0 <= fee < 5 * 104
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 */
public class BuyStock {

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        BuyStock buyStock = new BuyStock();
        System.out.println(buyStock.maxProfit(prices, fee));
    }

    //使用dp 思路

    /**
     * dp[i][0] 表示第i天手里卖了股票后最大的利润
     * dp[i][1] 表示第i天手里持有股票时最大的利润
     * 转移方程：
     * dp[i][0] 要么是昨天有股票，今天卖出，要么是昨天卖出的
     * 所以状态转移方程是  dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i] - fee )
     * 同样的dp[i][1] 要么是今天买的股票 要么是昨天就已经有的股票
     * dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i])
     * 最终要返回的结果就是 dp[i][0]中的最大值 或者说是最后一个
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        int max = 0;
        //初始化值
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            max = Math.max(dp[i][0], dp[i][1]);
        }
        System.out.println(max);
        return dp[n - 1][0];
        //return max;
    }
}
