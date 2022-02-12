package dp;

/**
 * 最长回文子串 动规 和 中心扩展法
 * 还是看carl讲的最好理解 谢谢carl
 * https://www.programmercarl.com/0647.%E5%9B%9E%E6%96%87%E5%AD%90%E4%B8%B2.html#%E5%85%B6%E4%BB%96%E8%AF%AD%E8%A8%80%E7%89%88%E6%9C%AC
 */
public class LongestPalindrome {


    public static void main(String[] args) {

        LongestPalindrome test = new LongestPalindrome();
        System.out.println(test.longestPalindrome2("xabad"));

    }

    //最长回文串的动规解法和中心扩展法
    //动规 boolean dp[i][j]表示i..j的字符串是否是回文 所以j>=i
    //转移方程呢？  dp[i][j] 如果s[i]!=s[j] 则直接是false和默认值相同可以不做处理
    //如果s[i]=s[j] 分情况处理：
    //1. i==j 则是同一个字符 则dp[i][j] = true
    //2. j-i=1 则是相邻的两个字符  则dp[i][j] = true
    //3. j-i>1 则取决于dp[i+1][j-1]的值
    //如何初始化呢？默认值都是false 不需要显式的初始化 或者说dp[i][i]都是true
    //遍历顺序呢？根据dp[i][j] 依赖于dp[i+1][j-1]所以需要从下往上 从左往右进行遍历
    //特殊情况判断
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;
        boolean[][] dp = new boolean[len][len];

        int begin = 0;
        int maxLen = 0;
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(j) == s.charAt(i)) {
                    if (j - i < 3) dp[i][j] = true;
                    else dp[i][j] = dp[i + 1][j - 1];
                } //不相同默认就是false 不做处理
                if (dp[i][j] && maxLen < j - i + 1) {
                    begin = i;
                    maxLen = j - i + 1;
                }
            }
        }

        //我们需要找到最长的子串 所以需要知道 begin index和最大的长度
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 中心扩展法的思路
     * 遍历中间每一个字符 （第一个和最后一个没必要遍历）
     * 向两边扩展 如果两边的字符相同，就是回文串，否则就不是
     * 中心选择有两种， 如果长度是奇数，那中间的字符就是一个
     * 如果字符串长度是偶数，则中间是有两个的，所以存在left=right || left +1 = right两种情况
     * 代码分开来写 比较好理解
     */

    //返回start end index for substring
    int[] index = new int[2]; //用来存储start end index
    int maxLen = 1;

    public void extend(String s, int left, int right, int size) {

        while (left >= 0 && right < size && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (maxLen < right - left + 1) {
            maxLen = right - left + 1;
            index[0] = left + 1;
            index[1] = right ;
        }
    }

    //求最长回文子串
    public  String  longestPalindrome2(String s) {

        int size = s.length();
        for (int i = 0; i < size; i++) {
            extend(s, i, i, size);
            extend(s, i, i+1, size);
        }
        return s.substring(index[0],index[1]);
    }

}
