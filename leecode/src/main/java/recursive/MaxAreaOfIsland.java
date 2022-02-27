package recursive;

/**
 * leecode 695 最大岛屿面积
 * 和200 岛屿数量类似
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * <p>
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * <p>
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-area-of-island
 */
public class MaxAreaOfIsland {
    public static void main(String[] args) {

    }

    int m = 0;//行数
    int n = 0;//列数

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, dfs(grid, i, j));
            }
        }
        return maxArea;
    }

    public int dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != 1) return 0;
        int area = 1;
        grid[i][j] = 2;
        area += dfs(grid, i, j - 1);
        area += dfs(grid, i, j + 1);
        area += dfs(grid, i + 1, j);
        area += dfs(grid, i - 1, j);
        return area;
    }

}
