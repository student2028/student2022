package binarytree;


import common.TreeNode;

/**
 * leecode 236
 *
 *  给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {

        TreeNode root = TreeNode.of(3,5,1,6,2,0,8,null,null,7,4);
        root.show();

        TreeNode p = root.find(5);
        TreeNode q = root.find(1);

        System.out.println(lowestCommonAncestor(root, p, q));


    }

    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果根节点等于其中某一个节点 肯定 root就是要找的
        if(root == null || root == q || root == p) return root;

        //从左子树找
        TreeNode left = lowestCommonAncestor(root.left, p,q);
        //从右子树找
        TreeNode right = lowestCommonAncestor(root.right, p,q);
        //判断
        if(left != null  && right != null) return root;
        else if(left == null && right != null) return right;
        else  if(right == null && left != null) return left;
        else return null;

    }

}
