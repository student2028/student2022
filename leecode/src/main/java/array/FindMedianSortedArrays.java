package array;

/**
 * leecode
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。

 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * 提示：
 *
 *     nums1.length == m
 *     nums2.length == n
 *     0 <= m <= 1000
 *     0 <= n <= 1000
 *     1 <= m + n <= 2000
 *     -106 <= nums1[i], nums2[i] <= 106
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/2020-top-interview-questions/xoyf7i/
 * 来源：力扣（LeetCode）
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        FindMedianSortedArrays test = new FindMedianSortedArrays();
        int[] nums1 = {1,3};
        int[] nums2 = {2};
        System.out.println(test.findMedianSortedArrays(nums1, nums2));

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //思路 合并数组 然后 根据数组长度 是奇数 还是偶数求中位数
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] nums3 = new int[n1+n2];
        //合并nums1,nums2 到 nums3中去
        int index = n1 + n2 - 1;
        n1--; n2 --; //指向最后的元素
        while(n1>=0 && n2>=0) {
            if(nums1[n1]>=nums2[n2]) {
                nums3[index--] = nums1[n1--];
            }else {
                nums3[index--] = nums2[n2--];
            }
        }
        while(n1 >=0)  nums3[index--] = nums1[n1--];
        while(n2 >=0) nums3[index--] = nums2[n2--];
        //求中位数
        int len = nums3.length;
        double res = 0;
        if(len % 2 == 0) { //偶数个 有两个
            int mid = len/2;
            res = (nums3[mid] + nums3[mid-1])*1.0/2;
        } else {
            res = nums3[len/2];
        }
        return res;
    }
}
