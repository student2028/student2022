package array;

import static common.Utils.printArray;

/**
 * leecode 287 寻找重复数 todo:good
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
 * <p>
 * 示例
 * 输入：nums = [1,3,4,2,2]
 * 输出：2  
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * 输入：nums = [1,1,2]
 * 输出：1
 * <p>
 * 官解有三，说二
 * 1. 环形链表判圈法 找到入口点那题同款 即快慢指针方法 最佳 空间与时间效率最好 O(n)
 * 2. 使用 二分查找方法 依据原理是 某个值 小于等于它的值是单增的 而且不超过此值本身，如果超过 则表明重复发生在其前面
 * 一直减小找到发生重复的数字
 * <p>
 */
public class FindDuplicate {

    public static void main(String[] args) {

        int[] nums = {1, 2, 1};
        System.out.println(findDuplicate(nums));

        int[] nums2 = {1, 2, 3, 4, 5, 4};
        System.out.println(findDuplicate2(nums2));
        printArray(nums2);
        System.out.println(findDuplicate3(nums2));
        printArray(nums2);
    }

    //copy from leecode
    private static int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }


    private static int findDuplicate2(int[] nums) {

        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;

    }

    private static int findDuplicate3(int[] nums) {
        //如果 nums[num]里面的数据是负数 则表示已经被更新过 数据重复
        int res = -1;
        for (int num : nums) {
            if (nums[Math.abs(num)] < 0) res = Math.abs(num);
            else nums[Math.abs(num)] = -nums[Math.abs(num)];
        }
        for (int i = 0; i < nums.length; i++) if (nums[i] < 0) nums[i] = -nums[i];
        return res;
    }


}
