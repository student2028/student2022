package hard;

import common.TreeNode;

/**
 * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
 *
 * 二叉搜索树的定义如下：
 *
 *     任意节点的左子树中的键值都 小于 此节点的键值。
 *     任意节点的右子树中的键值都 大于 此节点的键值。
 *     任意节点的左子树和右子树都是二叉搜索树。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-sum-bst-in-binary-tree
  */
public class MaxSumBST {

    public static void main(String[] args) {

    }

    int maxSum = Integer.MIN_VALUE;
    public int maxSumBST(TreeNode root) {
        traverse(root);
        return  maxSum;
    }

    /**
     *  后序遍历
     *  res[0]表示以root为根节点的树是否是bst 1表示是
     *  res[1] 表示以root为根节点的树的最小值
     *  res[2]表示以root为根节点的树的最大值
     *  res[3]表示以root为根节点的树的和值
     */
    private int[] traverse(TreeNode root) {
        if(root == null) return new int[] {1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };

        int[] left = traverse(root.left);
        int[] right = traverse(root.right);
        int[] res = new int[4];

        if(left[0] == 1 && right[0] == 1 && left[2] < root.val && right[1] > root.val ) {
            res[0] = 1;
            res[1] = Math.min(root.val, left[1]);
            res[2] = Math.max(root.val, right[2]);
            res[3] = root.val + left[3] + right[3];
            maxSum = Math.max(maxSum, res[3]);
       } else {
            res[0] = 0;
        }

        return res;
    }
}
