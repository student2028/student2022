package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leecode 15 三数之和 Middle
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
  */
public class ThreeNumSum {

    public static void main(String[] args) {

        int[] nums = {-1,0,1,2,-1,-4};

        List<List<Integer>> res = threeSum(nums);
        res.stream().forEach(System.out::println);

    }

    /**
     * 双指针法
     *  依然还是在数组中找到 abc 使得a + b +c =0，我们这里相当于 a = nums[i] b = nums[left] c = nums[right]
     *  接下来如何移动left 和right呢， 如果nums[i] + nums[left] + nums[right] > 0
     *  就说明 此时三数之和大了，因为数组是排序后了，所以right下标就应该向左移动，这样才能让三数之和小一些。
     *
     *  如果 nums[i] + nums[left] + nums[right] < 0 说明 此时 三数之和小了，left 就向右移动，才能让三数之和大一些，直到left与right相遇为止。
     *
     */
    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            if(nums[i] > 0) //排序后最左侧的数据nums[i]大于0 则肯定不存在满足需求的数据了
                return res;
             //技巧代码  排序后的数组 可以通过下面的方式去重
            if(i>0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = N - 1;

            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];

                if(sum > 0) {
                        //大于0 则right --
                    right --;
                } else if (sum <0) {
                    left ++;
                } else {
                    //==0 找到一个满足条件的
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    //继续找 不改变i的前提下
                    while(right > left && nums[right] == nums[right - 1]) right --;
                    while(right > left && nums[left] == nums[left + 1]) left ++;
                }
                right --;
                left ++;
            }

        }
        return res;

    }


}
