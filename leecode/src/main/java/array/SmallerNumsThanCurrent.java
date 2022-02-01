package array;

import static common.Utils.printArray;

/**
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * <p>
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * <p>
 * 以数组形式返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * <p>
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 */
public class SmallerNumsThanCurrent {

    /**
     * 这是一道简单题 是codetop上找到的 有企业面试这个题
     * 这个题不错 考的是排序的 有多种思路 所以方便考查
     * 你可以直接用Arrays.sort 面试官也可以让你自己手撕一个快排
     * 同时还能再考虑其他的点 是一个不错的题目
     * 1. 暴力解法 这个是非常直观的想法 直接循环两次 对于每一个数都找到小于它的数量 leecode会超时
     * 2. 因为有提示是小于101 可以考虑计数排序 排序之后如何获取目标值？
     * 1 3 3 5  -> 0,1,0,2,0,1 ->
     * 1 : 0
     * 3 : 1
     * 5 : 1 + 2 = 3
     * 示例数据 2,0,0,1,3 计数排序后
     * 2 0 0 1 3 -> 2, 1, 1, 1 -> 0, 2, 3, 4
     * <p>
     * 然后 可以看出来 小于当前数的等于cnt[0..i-1]的和
     * <p>  因为nums[i]=0的时候 肯定是最小值 没有值比它更小 所以返回0
     * 所以我们可以使用cnt[0] 存储 num[i]=1的值即 小于1的数的个数
     * 即nums[i]的目标值用cnt[nums[i]-1]来存储
     * num[i]的数量 存在cnt[nums[i]]的位置
     * 3. 排序后 两分查找 要保留原有数组的下标
     * 先复制一份输入数组，并对其进行快速排序；再对排好序的数组进行二分查找，若有重复元素，则取所有重复元素中的第一个元素。
     * 4. 排序加hash 代码随想录
     * 建议掌握  计数排序  和 二分查找
     */

    public static void main(String[] args) {

        int[] nums = new int[]{1, 3, 3, 5};
        SmallerNumsThanCurrent test = new SmallerNumsThanCurrent();
        printArray(test.smallerNumbersThanCurrent(nums));
        printArray(test.smallerNumbersThanCurrent(new int[]{2, 0, 0, 1, 3}));

    }

    public int[] smallerNumbersThanCurrent2(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        int[] cnt = new int[101];
        for (int i = 0; i < N; i++) {
            cnt[nums[i]]++;
        }
        // 如何理解这个代码块的逻辑？
        //  cnt[i] += cnt[i - 1] 表示 cnt[1] （表示值为1的数量 加上 值为0 的数量 就是值为2的数量的目标值)
        for (int i = 1; i < 101; i++) {
            cnt[i] += cnt[i - 1];
        }
        for (int i = 0; i < N; i++) {
            ans[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        }
        return ans;
    }

    //过渡版的 容易理解 直观的理解
    //比起暴力 这种方式 少比较了一些 下面这个写法 可以通过leecode的要求
    //但是比起上面一个 差了许多
    //再进一步思考  我能不能使用类似滚动数组的方式  我既然知道了前面的一个值
    //即对cnt数组进行进一步的优化 进化到不需要再进行循环查找的地步
    //
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        int[] cnt = new int[101];
        for (int i = 0; i < N; i++) {
            cnt[nums[i]]++;
        }

        for (int i = 0; i < N; i++) {
            if (nums[i] == 0) {
                ans[i] = 0;
                continue;
            }
            for (int j = 0; j < nums[i]; j++) {
                ans[i] += cnt[j];
            }
        }
        return ans;
    }
}
