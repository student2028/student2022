package basic;

import java.util.Arrays;

import static common.Utils.printArray;
import static common.Utils.swap;

/***
 * 划分操作
 *
 */
public class Partition {

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,1,1,2,0,0};
        printArray(nums);
        Partition partition = new Partition();
        partition.partition(nums, 0, nums.length - 1);
        printArray(nums);
    }

    /**
     * 在nums数组的left,right 范围内选择一个数字 划分完成后
     * 左边的数据比它小 右边的数据比他大
     **/
    public int partition(int[] nums, int left, int right) {
            int l = left;
            int r = right;
            int pivot = left;
            while (l <= r) {
                while (l <= r && nums[l] <= nums[pivot]) l++;
                while (l <= r && nums[r] > nums[pivot]) r--;
                swap(nums, l, r);
                printArray(nums);
            }
            swap(nums, l, left);
            return l;
    }
}
