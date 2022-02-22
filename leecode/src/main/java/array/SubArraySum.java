package array;

import sun.print.SunMinMaxPage;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * <p>
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 */
public class SubArraySum {

    public static void main(String[] args) {
        SubArraySum subArraySum = new SubArraySum();
        int[] nums = { 2,2,2,2};
        System.out.println(subArraySum.subArraySum(nums, 2));

    }

    //前缀和 加 字典的方式进行处理O(n)的方法就可以解出来
    public int subArraySum(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>(); //key是前缀和 值是 前缀和出现的次数
        //map.put(0,1);//以防第一个元素就等于k
        int sum = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if(sum ==k ) count ++;
            if (map.containsKey(sum - k)) {//如果找到了前缀和是k的 如果第一个元素是k 怎么办？所以需要map.put(0,1)
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

}
