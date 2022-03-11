package binarytree;

import common.TreeNode;

/**
 * 654. 最大二叉树
 * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
 * <p>
 * 创建一个根节点，其值为 nums 中的最大值。
 * 递归地在最大值 左边 的 子数组前缀上 构建左子树。
 * 递归地在最大值 右边 的 子数组后缀上 构建右子树。
 * <p>
 * 返回 nums 构建的 最大二叉树 。
 * <p>
 * 输入：nums = [3,2,1,6,0,5]
 * 输出：[6,3,5,null,2,0,null,null,1]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
 */
public class MaxBT {

    public static void main(String[] args) {

        MaxBT test = new MaxBT();

        int[] nums = new int[]{3, 2, 1, 6, 0, 5};
        test.constructMaximumBinaryTree(nums).show();

    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {

        if (left > right) return null;
        //找到最大值和索引
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = left; i <= right; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }

        TreeNode root = new TreeNode(max);
        root.left = build(nums, left, index - 1);
        root.right = build(nums, index + 1, right);

        return root;
    }


}
