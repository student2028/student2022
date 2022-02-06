package array;

import common.Utils;

import static common.Utils.printArray;

/**
 * 给定一个非负整数数组 nums，  nums 中一半整数是 奇数 ，一半整数是 偶数 。
 * 对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。
 * 你可以返回 任何满足上述条件的数组作为答案 。
 * 示例 1：
 * <p>
 * 输入：nums = [4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 进阶：可以不使用额外空间解决问题吗？
 */
public class SortArrayByParityII {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3};
        SortArrayByParityII test = new SortArrayByParityII();
        int[] res = test.sortArrayByParityII2(nums);
        printArray(res);
    }

    /**
     * 如果返回一个新的数组 则可以进行两次遍历  一次遍历存偶数 一个遍历存奇数
     */
    public int[] sortArrayByParityII(int[] nums) {
        int[] ans = new int[nums.length];
        int x = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                ans[x] = nums[i];
                x += 2;
            }
        }
        x = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                ans[x] = nums[i];
                x += 2;
            }
        }
        return ans;
    }

    //直接原地修改数组 思路是 使用双指针 i 指向偶数下标 j指下奇数下标 找到符合条件的则交换
    public int[] sortArrayByParityII2(int[] nums) {
        int N = nums.length;
        int j = 1;
        for (int i = 0; i < N; i += 2) {
            //只有当nums[i]为奇数时才处理
            if (nums[i] % 2 == 0) continue;
            while (j < N && nums[j] % 2 == 1) {
                //从奇数下标中找偶数 然后交换 找不到就说明数据本身就是有序的
                j += 2;
            }
            if (j < N) Utils.swap(nums, i, j);
        }
        return nums;
    }

}
