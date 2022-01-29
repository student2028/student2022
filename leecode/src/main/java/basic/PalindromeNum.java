package basic;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 121
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 */
public class PalindromeNum {
    public static void main(String[] args) {
        //思路很明显 如果是负数 则返回false
        //如果是正数 先把它转成数组再进行判断
        PalindromeNum test = new PalindromeNum();
        System.out.println(test.isPalindrome2(121));
        System.out.println(test.isPalindrome2(-121));
        System.out.println(test.isPalindrome2(0));


    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        //转成字符串进行处理
        String str = x + "";
        int N = str.length();
        int left = 0;
        int right = N - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    //如果不转成字符串行不行 或者说不使用栈或队列等其他辅助结构行不行呢？
    //官解的思路是转一半数字
    // 我们简单点 就把数字反转生成新的数字进行比较
    public boolean isPalindrome2(int x) {
        if (x < 0) return false;
        int newx = 0;
        int tmp = x;
        while (tmp > 0) { // 官解更优 判断条件只需要一半 转一半的数即可
            if(newx * 10 + tmp % 10 > Integer.MAX_VALUE) return false;
            newx = newx * 10 + tmp % 10;
            tmp /= 10;
        }

        return newx == x;
    }



}
