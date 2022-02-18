package array;


import java.util.Arrays;

/**
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 示例 1：
 *
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * 提示：
 *     1 <= nums.length <= 100
 *     0 <= nums[i] <= 109
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-number
 */
public class LargestNum {
    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        LargestNum largestNum = new LargestNum();
        System.out.println(largestNum.largestNum(nums));
    }

    public String largestNum(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .map(String::valueOf)
                .sorted((s1,s2) -> (s2+s1).compareTo(s1+s2))
                .reduce(String::concat)
                .filter(s->!s.startsWith("0"))
                .orElse("0");
    }
}
