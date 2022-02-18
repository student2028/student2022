package array;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * 提示：
 *     1 <= target <= 109
 *     1 <= nums.length <= 105
 *     1 <= nums[i] <= 105
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 */
public class MinSubArrayLen {
    public static void main(String[] args) {
        int target = 7;
        int[] nums = {2,3,1,2,4,3};
        MinSubArrayLen minSubArrayLen = new MinSubArrayLen();
        System.out.println(minSubArrayLen.minSubArrayLen(target, nums));
    }

    //滑动窗口的完美示例
    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;
        for(;right < nums.length ; right++) {
            sum += nums[right];
            while(sum >= target) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }


}
