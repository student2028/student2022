package array;

/**
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,0,1]
 * 输出：2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 *
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：2
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MissingNum {

    /**
     * 1. 排序 比较
     * 2. 异或 因为nums[i] ^ i = 0
     * 3. 等差数列求和公式求和 遍历
     * */

    public static void main(String[] args) {
        MissingNum missingNum = new MissingNum();
        int[] nums = new int[]{0,2};
        System.out.println(missingNum.missingNumber2(nums));
    }

    public int missingNumber(int[] nums) {
        int N = nums.length;
        int sum = N * (N + 1)/2;
        for(int n : nums) sum -= n;
        return  sum;
    }

    public int missingNumber2(int[] nums) {
        int N = nums.length;
        int ans = N; //?这里为什么是N 因为下面的判断是<N
        for (int i = 0; i < N; i++) {
            ans ^= nums[i] ^ i;
        }
        return ans;
    }

    }
