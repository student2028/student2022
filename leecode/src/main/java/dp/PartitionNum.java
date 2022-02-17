package dp;

/**
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 */
public class PartitionNum {

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};

        PartitionNum partitionNum = new PartitionNum();
        System.out.println(partitionNum.canPartition(nums));
    }

    //01背包问题啊
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int num : nums) { //遍历物品
            for (int i = target; i >= num; i--) {
                dp[i] = Math.max(dp[i], dp[i - num] + num);//添加num 和 不添加num
            }
        }
        return dp[target] == target;//背包刚好装的值等于需求就成功了
    }
}
