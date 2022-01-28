package basic;

/**
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * <p>
 * 示例 1：
 * 输入：x = 4
 * 输出：2
 * 示例 2：
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Sqrt {
    public static void main(String[] args) {
        Sqrt test = new Sqrt();

        System.out.println(test.mySqrt(4));
        System.out.println(test.mySqrt(8));
    }

    public int mySqrt(int x) {
        /**
         *  i的平方根为j 则 j * j 等于 i
         *  如果没有整数的平方根  则怎么求呢？
         *  因为可以舍弃小数，向下取，所以可以直接i*i > x 则直接返回i-1即可
         *  这是暴力解法 leecode中会超时如果n非常大效率很低O(n)
         *  可以改成二分查找的方式 logn
         */
//        for (int i = 1; i <= x; i++) {
//            if (i * i == x) return i;
//            if(i*i > x ) return i - 1;
//        }
//        return 0;
        int l = 0 ;
        int r = x;
        int ans = 0;
        while( l <= r) {
            int mid = l + (r-l)/2;
            if((long) mid * mid  <= x) { //如果数大，则这里会溢出
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

}
