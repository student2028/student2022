package recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 著名的八皇后问题
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例 1：
 * <p>
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：[["Q"]]
 * 提示：
 * 1 <= n <= 9
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 */
public class NQueues {
    public static void main(String[] args) {
        NQueues nQueues = new NQueues();
        List<List<String>> res = nQueues.solveNQueens(8);
        System.out.println(res);
    }

    List<List<String>> res = new ArrayList<>();


    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        //填充.值为默认值
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessboard[i][j] = '.';
            }
        }

        backtrack(0, n, chessboard);

        return res;
    }

    //判断是否有效的位置需要一个方法
    public void backtrack(int row, int n, char[][] chessboard) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(new String(chessboard[i]));
            }
            res.add(list);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(row, col, n, chessboard)) {
                chessboard[row][col] = 'Q';
                backtrack(row + 1, n, chessboard);
                chessboard[row][col] = '.';
            }
        }

    }

    //判断同一列 右上方 左上方 是否有皇后
    public boolean isValid(int row, int col, int n, char[][] chessboard) {

        //同一列的
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == 'Q') return false;
        }

        //右上角的 45度 chessboard[row - 1[col + 1]
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chessboard[i][j] == 'Q') return false;
        }

        //左下方的 135度 chessboard[row - 1][col - 1]
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') return false;
        }

        return true;
    }


}
