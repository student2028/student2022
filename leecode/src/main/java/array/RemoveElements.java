package array;


import static common.Utils.printArray;
import static common.Utils.swap;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 *
 */
public class RemoveElements {

    public static void main(String[] args) {
        int[] nums = { 0,1,2,2,3,0,4,2};
        int target = 2;

        int i =  removeElements(nums, target);
        System.out.println(i);
        printArray(nums);
    }

    private static int removeElements(int[] nums, int target) {
        //不需要考虑超出新长度后面的元素  可以理解只需要把不等于target的值 插入到前面即可 或者把符合条件的数据全部移到后面去
        //双指针 类似 moveX2End思路
        int N =  nums.length;
        int left = 0;
         for (int i = 0; i < N; i++) {
            if(nums[i] != target) {
                nums[left] = nums[i];
                left ++;
            }
        }
         return left;
    }

}
