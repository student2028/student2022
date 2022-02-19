package array;

import common.Utils;

/**
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库的sort函数的情况下解决这个问题。
 *
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 *
 * 示例 2：
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 提示：
 *     n == nums.length
 *     1 <= n <= 300
 *     nums[i] 为 0、1 或 2
 * 进阶：
 *
 *     你可以不使用代码库中的排序函数来解决这道题吗？
 *     你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 感觉这是一道非常好的题 评论中有人说小米出过这个题
 * 这个题还可以改变一下颜色的顺序 是一个不错的题目
 *
  */
public class SortColors {

    public static void main(String[] args) {
        SortColors sortColors = new SortColors();
        sortColors.sortColors(2,0,2,1,1,0);

    }
    //移动元素的思路
    public void sortColors(int... nums) {
        int N = nums.length;
        int left = 0;
        int right = N - 1;
        int ind = 0;
        while (ind < N ) {
            if(nums[ind] == 0) Utils.swap(nums, ind, left ++);
            else if(nums[ind] == 2) {
                Utils.swap(nums, ind , right --);
                //下面这两个没想到当时 为什么需要做下面两步呢？
                //因为指针是向前移动的 所以交换到最后面的2 就不用再做处理了 所以需要 N--
                //因为被交换的数据可能还需要进一步处理 所以ind -- 和后面的ind ++对冲之后再次进行判断
                //以防当前交换的数据就是0 需要再进一步处理
                N--;
                ind --;
            }
            ind ++;
        }
        //printArray
        Utils.printArray(nums);
    }

 }
