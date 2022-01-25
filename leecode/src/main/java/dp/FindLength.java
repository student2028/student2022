package dp;


/**
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 示例：
 *
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
 *
 * dp[i][j] ：以下标i - 1为结尾的A，和以下标j - 1为结尾的B，最长重复子数组长度为dp[i][j]。--最长公共前缀
 * 根据dp[i][j]的定义，dp[i][j]的状态只能由dp[i - 1][j - 1]推导出来。 即当A[i - 1] 和B[j - 1]相等的时候，dp[i][j] = dp[i - 1][j - 1] + 1;
 *
 *
 */

public class FindLength {



    public static void main(String[] args) {
        FindLength test = new FindLength();
        int[] n1 = {1,2,3,2,1};
        int[] n2 = {3,2,1,4,7};
        System.out.println(test.findLength(n1,n2));

    }

     public int findLength(int[] nums1, int[] nums2) {
        int result  = 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        //初始化
        for (int i = 1; i < nums1.length + 1; i++) {
            for (int j = 1; j < nums2.length + 1; j++) {
                if(nums1[i-1] == nums2[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;
    }
}
