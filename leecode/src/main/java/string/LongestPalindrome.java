package string;

/**
 * leecode 409 最长回文串
 * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串 。
 * <p>
 * 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
 * 示例 1:
 * <p>
 * 输入:s = "abccccdd"
 * 输出:7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * <p>
 * 示例 2:
 * 输入:s = "a"
 * 输入:1
 * <p>
 * 示例 3:
 * 输入:s = "bb"
 * 输入: 2
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindrome
 */
public class LongestPalindrome {

    public static void main(String[] args) {

    }

    //思路 字符串中的字符分为两类 一类是奇数 一类是偶数
    //偶数的字符肯定可以直接用来做回文  可以加进来长度
    //奇数的 如果长度是1有多个 只取一个做中间字符也是可以的
    //如果奇数大于1  则取小于奇数的最大偶数 加入即可 N /2 *2 这个是关键 通过这个操作完成了转换
    public int longestPalindrome(String s) {
        int[] count = new int[128];//字符的范围
        for(char ch : s.toCharArray()) count[ch] ++;
        int ans = 0;
        boolean hassingle = false;
        for(int num : count) {
            if(num % 2 == 0) ans += num;
            if(num %2 == 1) ans += num/2 * 2;
            if(num %2 == 1) hassingle = true;
        }
        if(hassingle) ans++;
        return ans;
    }

}
