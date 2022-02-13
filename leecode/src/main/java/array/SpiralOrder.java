package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * leecode 54. 螺旋矩阵
 * 高频率的题目
 */

public class SpiralOrder {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;
        int m = matrix.length;
        int n = matrix[0].length;
        int t = 0;
        int l = 0;
        int r = n - 1;
        int b = m - 1;

        while (true) {
            //从左上 开始向右
            for (int i = l; i <= r; i++) res.add(matrix[t][i]);
            if (++t > b) break;
            //向下
            for (int i = t; i <= b; i++) res.add(matrix[i][r]);
            if (--r < l) break;//r是在减少 圈越来越小
            //向左 在底部 从右向左
            for (int i = r; i >= l; i--) res.add(matrix[b][i]);
            if (--b < t) break; //圈越来越小b越来越小
            //向上
            for (int i = b; i >= t; i--) res.add(matrix[i][l]);
            if (++l > r) break;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        SpiralOrder spiralOrder = new SpiralOrder();
        List<Integer> list = spiralOrder.spiralOrder(matrix);
        System.out.println(list);
    }
}
