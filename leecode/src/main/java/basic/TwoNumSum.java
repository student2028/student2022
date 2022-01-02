package basic;

import java.util.HashMap;
import java.util.Map;

import static common.Utils.printArray;


/**
 * 两数之和 leecode第一题
 * 一个整型数组  一个目标整数值
 * 从数组中找到两个元素的和等于 目标整数值
 * 假定 你可以假设每种输入只会对应一个答案。
 * 但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 思路：
 * 1.可以直接使用双循环 外层循环表示元素1
 * 里层循环表示元素2
 * 检查两个值加等于target
 * 时间复杂度较高
 * 2. 用空间换时间 这是常用的一个原则
 * 我们里层能不用循环来判断吗？
 * 能不能使用一个hashmap来判断呢？
 *
 * 这是一个查找的问题，查找会想到二分查找？
 * 但是二分查找要求数组是一个有序的
 * 而且排序之后数据的index也会发生变化
 *
 *
 */
public class TwoNumSum {

    public static void main(String[] args) {

        int[] nums = {2, 7, 11, 15};
        int[] nums2 = {3, 3};
        printArray(twoSum(nums, 9));
        printArray(twoSum(nums2, 6));

        printArray(twoSum2(nums, 9));
        printArray(twoSum2(nums2, 6));

    }

    private static int[] twoSum(int[] arr, int target) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (arr[i] + arr[j] == target) return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    private static int[] twoSum2(int[] arr, int target) {
        int N = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (map.containsKey(target - arr[i])) {
                return new int[]{i, map.get(target - arr[i])};
            }
            map.put(arr[i], i);
        }
        return new int[]{-1, -1};

    }

}
