package search;

import java.util.ArrayList;
import java.util.List;

import static common.Utils.printArray;

/**
 * leecode 34 searchRange
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶： 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 */
public class SearchRange {

    public static void main(String[] args) {

        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;

        printArray(searchRange(nums, target));

        int[] nums2 = {1, 1, 2};
        printArray(searchRange(nums2, 1));

    }

    //使用二分查找
    private static int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        res[0] = biSearch(nums, target);
        res[1] = biSearch2(nums, target);
        return res;
    }

    //求相等的值的最小的下标
    //我们要求最左边的下标 就要往左找 就是相等的时候继续往左找 即>= target right = mid -1这一部分进行处理
    //后面判断的时候 为什么判断 left 呢?
    //debug 一下跳出的时候 就是left = mid + 1
    //所以要考虑下 left 是否越界N
    private static int biSearch(int[] nums, int target) {
        int N = nums.length;
        int left = 0;
        int right = N - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            //求最小的位置 则>= 都让right变小
            if (nums[mid] >= target) right = mid - 1;
            else left = mid + 1;
        }
        if (left < N && nums[left] == target) return left;
        return -1;
    }

    //求最右边的相等的值 最大的下标 解析同上 我们要找最右边的 即如果<=的时候 右边假定还有，则继续让left = mid + 1 向上走
    //同理 最后退出的时候 为什么看right值 ？
    // debug 一下 最后跳出的时候是在 right = mid - 1
    // 同理考虑下right 是否越界0
    private static int biSearch2(int[] nums, int target) {
        int N = nums.length;
        int left = 0;
        int right = N - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target)
                left = mid + 1;
            else right = mid - 1;
        }
        if (right >= 0 && nums[right] == target) return right;

        return -1;
    }




}
