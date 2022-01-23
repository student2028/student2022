package dp;

/**
 * leecode 62
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * 输入：m = 3, n = 7
 * 输出：28
 *
 * 示例 2：
 *
 * 输入：m = 3, n = 2
 * 输出：3
 * dp五步曲：
 * 1. 定义dp数组 并理解其含义 一维二维？
 * 2. 初始化dp数组 根本含义进行初始化
 * 3。写状态转移方程 dp[i] 与 dp[i]相邻元素的关系
 * 4. 按什么顺序推理？
 * 5. 举例验证是否正确
 */
public class UniquePaths {

    public static void main(String[] args) {

        UniquePaths test = new UniquePaths();
        System.out.println(test.uniquePaths(3,2)); //3
        System.out.println(test.uniquePaths(3,7)); //28

    }

    //画二维表格进行推理 二维的dp数组
    public int uniquePaths(int m , int n) {

        int[][] dp = new int[m][n];//dp[i][j]表示从(0,0)出发到(i,j)有dp[i,j]条不同的路径
        //初始化 位于左边或上边的那一行或列 值是1  表示从(0,0)过去只有一条路径 
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m - 1][n - 1];
    }

}
