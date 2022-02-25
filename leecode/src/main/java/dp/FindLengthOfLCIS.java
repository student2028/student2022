package dp;

/**
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 * <p>
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,5,4,7]
 * 输出：3
 * 解释：最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,2,2]
 * 输出：1
 * 解释：最长连续递增序列是 [2], 长度为1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindLengthOfLCIS {

    //双指针思路 如果 后一个数小于等于前一个数 说明要开始一个新的序列 start = i
    //这种思路和贪心很像 主要的好处是 有一个start 如果需要进一步输出最长序列的话
    //也是比较方便做的
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null) return 0;
        int ans = 1;
        int start = 0;
        int N = nums.length;
        for (int i = 1; i < N; i++) {
            if (nums[i] <= nums[i - 1]) start = i;
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }

    //贪心思路 如果nums[i+1] > nums[i] 则count++ 否则 count = 1
    public int findLength1(int[] nums) {
        if (nums == null) return 0;
        if (nums.length == 1) return 1;
        int n = nums.length;
        int res = 1;
        int count = 1;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] > nums[i]) count++;
            else count = 1;
            res = Math.max(res, count);
        }
        return res;
    }

    public static void main(String[] args) {
        FindLengthOfLCIS test = new FindLengthOfLCIS();
        int[] nums = new int[]{1, 3, 6, 2, 1, 9, 10, 12};
        System.out.println(test.findLengthOfLCIS(nums));
        System.out.println(test.findLength1(nums));
        System.out.println(test.findLength2(nums));

    }

    //动规思路
    //dp[i] 表示取i元素时最长的连续递增子数组的长度
    //if(nums[i+1] > nums[i] 则dp[i] = dp[i-1] + 1; else  1;
    public int findLength2(int[] nums) {
        if (nums.length < 2) return 1;
        int res = 1;
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] > nums[i]) {
                dp[i + 1] = dp[i] + 1;
            }
            res = Math.max(dp[i + 1], res);
        }

        return res;
    }
}
