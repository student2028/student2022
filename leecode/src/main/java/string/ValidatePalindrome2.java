package string;

/**
 * leecode 680 验证回文串2
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * 示例 1:
 * <p>
 * 输入: s = "aba"
 * 输出: true
 * <p>
 * 示例 2:
 * <p>
 * 输入: s = "abca"
 * 输出: true
 * 解释: 你可以删除c字符。
 * <p>
 * 示例 3:
 * <p>
 * 输入: s = "abc"
 * 输出: false
 * 提示:
 * <p>
 * 1 <= s.length <= 105
 * s 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome-ii
 */
public class ValidatePalindrome2 {

    public static void main(String[] args) {
        ValidatePalindrome2 test = new ValidatePalindrome2();
        System.out.println(test.isPalindrome("abca"));
        System.out.println(test.isPalindrome("abcde"));
    }

    //思路 双指针 因为有一个次机删除一个字符 所以跳过去检查 i+1 or j -1 即可
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return isValid(s, left + 1, right) || isValid(s, left, right - 1);
            left++;
            right--;
        }
        return true;
    }

    public boolean isValid(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
