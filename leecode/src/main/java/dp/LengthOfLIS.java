package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 示例 1：
 * <p>
 * 输入：nums = [10,9,2,5,3,4,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * 提示：
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * <p>
 * 进阶：
 * <p>
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 * 你能将最长的序列打印出来吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 */
public class LengthOfLIS {
    public static void main(String[] args) {
        LengthOfLIS lengthOfLIS = new LengthOfLIS();
        System.out.println(lengthOfLIS.lengthOfLIS2(10, 9, 2, 5, 3, 4, 7, 101, 18));
    }

    /**
     * 只要数组不是空 则至少有一个元素
     * max = 1
     * dp[i] 表示前i个元素中连续递增子序列的最大长度
     * 初始化是dp[i]=1 至少是自身一个元素的长度 是1
     * 如果 0-i-1中 如果 nums[i] > nums[j] dp[i] = Math.max(dp[i], dp[j] + 1)
     */
    public int lengthOfLIS(int... nums) {
        if(nums == null) return 0;
        int n = nums.length;
        int max = 1;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    //同时返回最长的序列 来自官解下方评论
    //需要为每一个dp[i] 建一个对应的List<>来存储它的列表
    public List<Integer> lengthOfLIS2(int... nums) {
        int n = nums.length;
        int[] dp = new int[n];
        List<List<Integer>> paths = new ArrayList<>();
        int max = 0;
        int index = -1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            List<Integer> path = new ArrayList<>();
            path.add(nums[i]);
            paths.add(path); //
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] < dp[j] + 1) {
                        List<Integer> temp = new ArrayList<>(paths.get(j));
                        temp.add(nums[i]);
                        paths.remove(i);
                        paths.add(temp);
                        dp[i] = dp[j] + 1;
                    }
                }
            }
            if (max < dp[i]) {
                max = dp[i];
                index = i;
            }
        }
        if (index == -1) return null;
        return paths.get(index);
    }

}
