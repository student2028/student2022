package binarytree;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leecode 104 easy
 * 树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 */
public class MaxDepthOfTree {
    public static void main(String[] args) {
        TreeNode  root = TreeNode.of(3,9,20,null,null,15,7);
        root.show();
        System.out.println();
        System.out.println(maxDepthR(root));

        System.out.println(maxDepth(root));
    }

    private  static int maxDepthR(TreeNode root) {
         return maxDepthOfTree(root, 0);
    }

    /**
     * 递归求解
     * @param root
     * @param depth
     * @return
     */
    private static int maxDepthOfTree(TreeNode root, int depth) {
        if(root == null) return depth; //终止条件
        depth ++;
        int ld = maxDepthOfTree(root.left, depth);
        int rd = maxDepthOfTree(root.right, depth);
        return ld > rd ? ld : rd;
    }

    private  static int maxDepth(TreeNode root) {
        //非递归法求树的最大深度
        if(root == null) return 0;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        int depth = 0;
        while(!qu.isEmpty()){
            int len = qu.size();
            while(len > 0) {
                TreeNode node = qu.poll();
                if(node.left != null) qu.offer(node.left);
                if(node.right != null) qu.offer(node.right);
                len --;
            }
            depth++;
        }

        return depth;
    }

}
