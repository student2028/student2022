package array;

import static common.Utils.printArray;

/**
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 已按 非递减顺序 排序
 * <p>
 * 进阶：
 * <p>
 * 请你设计时间复杂度为 O(n) 的算法解决本问题
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortSquares {

    //本题的思路 双指针比较妙
    //直观的思路就是先计算平方再排序
    //使用双指针 一个指向0 一个指向尾部N-1 然后对比平方值然后逆序 类似于归排排序的 这样做 主要是考虑到负数值 平方后可能会比较大 所以这样处理一下
    public int[] sortedSquares(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        int left = 0;
        int right = N - 1;
        int index = right;
        while( left <= right) {
            if(nums[left]*nums[left] > nums[right]*nums[right]) {
                ans [index --] = nums[left]*nums[left];
                left ++;
            } else {
                ans [index --] = nums[right]*nums[right];
                right --;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-7, -3, 2, 3, 11};
        SortSquares test = new SortSquares();
        int[] arr = test.sortedSquares(nums);
        printArray(arr);

    }
}
