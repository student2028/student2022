package binarytree;

import common.TreeNode;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * <p>
 * 自顶向下的递归  前序遍历  O(N2) 效率比较底 重复计算树的高度
 * 自下而上的递归  后序遍历  O(N)
 */
public class BalanceTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        else
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode root) {
        if (root == null) return 0;
        else return Math.max(height(root.left), height(root.right)) + 1;
    }

    public boolean isBalanceTree(TreeNode root) {
        return false;
    }

    private int postOrder(TreeNode root) {
        if (root == null) return 0;
        int lh = postOrder(root.left);
        int rh = postOrder(root.right);
        if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) return -1;
        else return Math.max(lh, rh) + 1;
    }

}
