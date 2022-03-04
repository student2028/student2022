package dp;

/**
 * leecode 64
 * 最小路径和
 * <p>
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * 输入：grid = [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 */
public class MinPathSum {

    public static void main(String[] args) {

        MinPathSum test = new MinPathSum();
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(test.minPathSum(grid));

    }

    public int minPathSum(int[][] grid) {
        int l = grid.length;
        int c = grid[0].length;
        int[][] dp = new int[l][c];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < l; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int i = 1; i < c; i++) dp[0][i] = dp[0][i - 1] + grid[0][i];
        for (int i = 1; i < l; i++)
            for (int j = 1; j < c; j++)
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);

        return dp[l - 1][c - 1];
    }

}
