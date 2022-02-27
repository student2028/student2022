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
        int left = findLeft(nums, target);
        int right = findRight(nums, target);
        return new int[] {left ,right };
    }

    //充分理解二分查找 二分查找的变化用法
    //找到有序数组中与target相同的最小索引值
    //二分查找的时候要压缩right范围 记住这一点
    private static int findLeft(int[] nums, int target) {
        int left = 0;
        int n = nums.length;
        int right = n -1;
        while( left <= right) {
            int mid = left + (right - left)/2;
            if(nums[mid] >= target) {
               right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if(left < n && nums[left] == target) return left;
        return -1;
    }

    //找到最右边的值  就要扩大left的值 一直多往右边找
    private static int findRight(int[] nums, int target) {
        int left = 0;
        int n = nums.length ;
        int right = n - 1;
        while(left <= right) {
            int mid = left + (right - left)/2;
            if(nums[mid] <= target) left = mid + 1;
            else right = mid - 1;
        }
        if(right >=0 && nums[right] == target) return right;
        return -1;
    }





}
