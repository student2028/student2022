package dp;


import static common.Utils.printArray;

/**
 * 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例: 输入: [-2,1,-3,4,-1,2,1,-5,4] 输出: 6 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class MaxSubArray {

    public static void main(String[] args) {

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        printArray(nums);

        MaxSubArray test = new MaxSubArray();
        // System.out.println(test.maxSubArray(nums));

        System.out.println(test.maxSubArray2(nums));

        System.out.println(test.maxSubArray3(nums));

    }

    /**
     * 双重循环 暴力计算
     * 从每一个点开始进行判断
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i; j < nums.length; j++) {
                count += nums[j];
                res = Math.max(res, count);
            }
        }

        return res;
    }

    /**
     * 贪心算法 贪在哪里？
     * 如果加起来和 < 0，则放弃 从下一个再算起来
     */
    public int maxSubArray2(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    /**
     * dp算法
     * dp[i] 表示i结尾的数组中最大子续和的长度
     * dp[i] = Math.max(dp[i-1] + nums[i], nums[i])
     * 当前值加入到序列中 或者只使用当前值  因为前面的和可能是负数
     */
    public int maxSubArray3(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
