package search;


/***
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 示例 1：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 *
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 *
 * 示例 3：
 *
 * 输入：nums = [1], target = 0
 * 输出：-1
 * 提示：
 *
 *     1 <= nums.length <= 5000
 *     -10^4 <= nums[i] <= 10^4
 *     nums 中的每个值都 独一无二
 *     题目数据保证 nums 在预先未知的某个下标上进行了旋转
 *     -10^4 <= target <= 10^4
 * 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
 ** 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 */
public class LC33 {

    /**
     * 理解题意思 就是从数组中找一个目标数字 找到返回下标 找不到就返回-1
     * 但是这个数组有个特点  是一个有序数组 但是按某个值进行了旋转 可以理解
     * 数组可能分成了两部分 每一部分都是有序的 但整体不是有序的
     * 很明显是应该使用二分查找 但又不是简单的二分查找
     * 需要仔细思量一下如何判断
     * 考点： 二分查找
     */

    public static void main(String[] args) {

        LC33 lc33 = new LC33();
        int[] a = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        System.out.println(lc33.search(a, target));
        System.out.println(lc33.search(a, 3));


    }

    //官解清晰易懂 这次我顶顶顶 官姐
    public int search(int[] nums, int target) {
        int n = nums.length;
        if(n == 0 ) return -1;
        if(n == 1 ) return nums[0] == target ? 0 : -1;

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) return mid;
            if (nums[0] <= nums[mid]) { //说明 0 -- middle是有序的 可以按二分走
                 if ( target > nums[0] && target < nums[mid])  right = mid - 1;
                     else left = mid + 1;
            } else { //如果 mid - n - 1 之间有序的 可以被二分查找
                if(target > nums[mid] && target < nums[n - 1]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return -1;
    }

    //copy from guanjie
    public int search2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

}
