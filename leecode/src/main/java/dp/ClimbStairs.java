package dp;


/**
 * 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1 阶 + 1 阶
 * 2 阶
 * <p>
 * 题目扩展：如果可以爬m 阶 怎么做呢？
 */
public class ClimbStairs {

    public static void main(String[] args) {
        ClimbStairs cs = new ClimbStairs();

        System.out.println(cs.climbStairs(2, 2));
        System.out.println(cs.climbStairs(3, 2));
        System.out.println(cs.climbStairs(4, 2));
        System.out.println(cs.climbStairs(5, 2));
        System.out.println(cs.climbStairs(6, 2));
        System.out.println(cs.climbStairs(7, 2));


    }

    public int climbStairs(int n) {
        if (n < 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //一次可最多爬m阶楼梯呢？
    // dp[i] = dp[i-1] + dp[i-2] + ... dp[i-m]? 写一个循环即可
    public int climbStairs(int n, int m) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (i >= j)
                    dp[i] += dp[i - j];
            }
        }
        return dp[n];
    }

}
