package array;

/**
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 * <p>
 * 示例 2：
 * <p>
 * 输入：mat = [[1,2],[3,4]]
 * 输出：[1,2,3,4]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diagonal-traverse
 * 模拟法
 * 数学规律法
 * 有人评论说使用 类似 树的层序遍历的方法 这个是怎么理解呢？
 */
public class FindDialogOrder {
    public static void main(String[] args) {

    }

    public int[] findDiagonalOrder(int[][] mat) {
        //col 从小到大再从大到小再从小到大
        if (mat == null) return null;
        int m = mat.length;
        int n = mat[0].length;
        int cnt = 0;
        boolean up = true;
        int i = 0, j = 0, k = 0; //分别表示行 列 和 目标数组的下标
        int[] res = new int[m * n];
        while (cnt < m * n) //遍历所有元素
        {
            res[k++] = mat[i][j];
            cnt++;
            if (up) {//向右上遍历 行变小 列值变大
                //如果下一个位置在范围内
                if (i - 1 > -1 && j + 1 < n) {
                    i--;
                    j++;
                }
                //如果不在则改变遍历方向
                else {
                    up = false;
                    //若当前遍历到最后一列 则下一个位置是同列的下一行
                    if (j == n - 1) i++;
                    else j++;
                }
            } else { //向左下遍历
                //同上 后一个位置在范围内
                if (j - 1 > -1 && i + 1 < m) {
                    j--;
                    i++;
                } else {
                    //改变方向
                    up = true;
                    if (i == m - 1) j++;
                    else i++;
                }
            }
        }
        return res;
    }
}
