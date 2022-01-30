package basic;

/**
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 *         丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *         示例 1：
 *         输入：n = 6
 *         输出：true
 *         解释：6 = 2 × 3
 *
 *         示例 2：
 *         输入：n = 8
 *         输出：true
 *         解释：8 = 2 × 2 × 2
 *
 *         示例 3：
 *         输入：n = 14
 *         输出：false
 *         解释：14 不是丑数，因为它包含了另外一个质因数 7 。
 *
 *         示例 4：
 *         输入：n = 1
 *         输出：true
 *         解释：1 通常被视为丑数。
 *
 *         来源：力扣（LeetCode）
 *         链接：https://leetcode-cn.com/problems/ugly-number
 *         著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UglyNum {

    public static void main(String[] args) {
        UglyNum test = new UglyNum();

        System.out.println(test.isUgly(1));
        System.out.println(test.isUgly(6));
        System.out.println(test.isUgly(8));
        System.out.println(test.isUgly(14));

    }

    public boolean isUgly(int n) {
        if(n < 1) return  false; //0和负数都不满足条件
        if(n == 1) return true;
        int[] factors = new int[] { 2, 3, 5};
        // 反复除 最后剩下的数和1相比
        for (int i = 0; i < 3; i++) {
            while( n % factors[i] == 0) {
                n /= factors[i];
            }
        }
        return n == 1;
    }
}
