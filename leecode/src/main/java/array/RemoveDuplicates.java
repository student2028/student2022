package array;

import static common.Utils.printArray;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicates {

    public static void main(String[] args) {

        int[] nums = new int[]{1, 1, 1, 2};
        RemoveDuplicates test = new RemoveDuplicates();
        test.removeDuplicates(nums);
        printArray(nums);
    }

    public int removeDuplicates(int[] nums) {
        int N = nums.length;
        int n = 0;
        for (int i = 0; i < N; i++) {
            //if (i == 0 || nums[i] != nums[i - 1]) nums[n++] = nums[i];
            if (i == 0 || nums[n - 1] != nums[i]) nums[n++] = nums[i]; //n表示慢指针 i表示快指针 还是双指针做法
        }
        return n;
    }

}
