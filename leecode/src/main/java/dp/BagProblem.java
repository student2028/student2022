package dp;

/**
 * /Question/problem/puzzle/
 * question:问题很模糊，没有明确答案。
 * problem:问题定义清楚，答案很多，最优解需要选择。
 * puzzle:边界定义非常清楚，答案很明确。
 * <p>
 * 01背包问题：
 * 背包可以装w重量的物品
 * 物品要么拿要么不拿 物品有重量和价值
 * 求可以如何拿到最大的价值的物品
 *
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
  */


public class BagProblem {


    public static void main(String[] args) {
//        int[] weight = {1, 3, 4};
//        int[] value = {15, 20, 30};
//        int bagWight = 4;
//        testWeightBagProblem(weight, value, bagWight);

        int[] nums =  {1,5,11,5};
        BagProblem bagProblem = new BagProblem();
        System.out.println(bagProblem.canPartition(nums));

    }

    public static void testWeightBagProblem(int[] weight, int[] value, int bagWeight) {
        int wLen = weight.length;
        //定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值
        int[] dp = new int[bagWeight + 1];
        //遍历顺序：先遍历物品，再遍历背包容量
        for (int i = 0; i < wLen; i++) {
            for (int j = bagWeight; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        //打印dp数组
        for (int j = 0; j <= bagWeight; j++) {
            System.out.print(dp[j] + " ");
        }
    }

    /**
     * 背包问题， 背包总量是sum/2 从物品中找出和为sum/2，则成功
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums) sum += num;
        if(sum % 2  == 1) return false;
        int target = sum/2;
        int[] dp = new int[target + 1];
        for(int num : nums) { //遍历 物品
            for (int i = target; i >= num ; i--) { //遍历背包 只要背包大于num 就可以往里面加
                //理解递推公式的意义  dp[i] 表示不放当前物品时的情况 dp[i-num] + num表示放当前物品的情况
                //这两种情况就可以推导出当前背包的最大物品值
                dp[i] = Math.max(dp[i], dp[i - num] + num);
            }
        }
        return dp[target] == target;
    }



}
