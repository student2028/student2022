package binarytree;


import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leecode 111
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 *
 *  根据carl 的提示，还是用层序遍历的套路来做
 *  层序遍历的时候 找到的第一个叶子节点，就决定了树的最小深度 就可以返回了
 *  判断 如果左右孩子为空，则就是叶子节点，
 *  则就可以返回了
 */
public class MinDepthOfTree
{

    public static void main(String[] args) {

        TreeNode root =   TreeNode.of(3,9,20,null,null,15,7);
        root.show();
        System.out.println(minDepthOfTree(root));
        System.out.println(dfs(root));

    }

    //dfs的思路 求高度 求左右子树的最小高度
    private static int dfs(TreeNode root) {
        if(root == null) return 0;
        else if(root.left == null) return dfs(root.right) + 1;
        else if (root.right == null) return dfs(root.left) + 1;
        else return Math.min(dfs(root.left), dfs(root.right)) + 1;
    }

    private  static int minDepthOfTree(TreeNode root) {
        // 叶子节点 即 左右孩子都是空的节点 判断这种节点
        if(root == null) return 0;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        int minDepth = 0;
        while(!qu.isEmpty()) {
            int len = qu.size();
            while( len > 0) {
                TreeNode node = qu.poll();
                if(node.left == null && node.right == null) {
                    //叶子节点
                    minDepth ++ ;
                    return minDepth;
                }
                if(node.left != null) qu.offer(node.left);
                if(node.right != null) qu.offer(node.right);
                len --;
            }
            minDepth ++;
        }
        return 0;
    }

}
