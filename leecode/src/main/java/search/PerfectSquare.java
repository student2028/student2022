package search;

/***
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 *
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 * 示例 1：
 *
 * 输入：num = 16
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：num = 14
 * 输出：false
 *
 * 提示：
 *     1 <= num <= 2^31 - 1
 * 链接：https://leetcode-cn.com/problems/valid-perfect-square
 */
public class PerfectSquare {

    public static void main(String[] args) {
        PerfectSquare test = new PerfectSquare();
        System.out.println(test.isPerfectSquare(4));
        System.out.println(test.isPerfectSquare(1800));
        System.out.println(test.isPerfectSquare(2500));

    }

    public boolean isPerfectSquare(int num) {
        //二分查找法来判定
        if (num == 1) return true;
        //如果大于1
        int left = 1;
        int right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid < num) {
                left = mid + 1;
            } else if((long)mid*mid > num){
                right = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
