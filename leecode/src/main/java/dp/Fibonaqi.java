package dp;

/**
 *
 */
public class Fibonaqi {

    int[] dp  = null;
    public Fibonaqi(int n) {
        fibonaqi(n);
    }

    public static void main(String[] args) {

        Fibonaqi fibonaqi = new Fibonaqi(10);
        for (int i = 0; i < 11; i++) {
            System.out.println(fibonaqi.getVal(i));
        }

    }

    /**
     * dp 五步曲 carl
     *  1. 确定dp[i] and i的含义
     *  第i个数的fibonaqi数是dp[i]
     *  2. 确定递堆公式 或 状态转移方程
     *  dp[i] = dp[i - 1] + dp[i - 2];
     *  3. dp 如何进行初始化
     *  题目中也给出了dp[0]= 0 and dp[1[= 1
     *  4. 确定遍历顺序
     *  从递归公式dp[i] = dp[i - 1] + dp[i - 2];中可以看出，dp[i]是依赖 dp[i - 1] 和 dp[i - 2]，那么遍历的顺序一定是从前到后遍历的
     *  5. 举例推导dp数组
     *
     */
    private int fibonaqi(int n ) {
        if( n < 2 ) return n;
        //定义数组
        dp = new int[n + 1];
        //初始化
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public int  getVal(int i) {
        if(i < dp.length )
           return dp[i];
        else
            return 0;
    }

}
