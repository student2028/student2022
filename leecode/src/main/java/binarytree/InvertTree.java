package binarytree;


import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 反转二叉树
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 */
public class InvertTree {

    public static void main(String[] args) {

        TreeNode root = TreeNode.of(4,2,7,1,3,6,9);
        root.show();
       // invertTree(root);
        invertTreeLevel(root);
        root.show();
    }

    //递归算法  dfs
    private static void invertTree(TreeNode root) {
        if(root == null) return;
        //swap left and right child
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        invertTree(root.left);
        invertTree(root.right);
    }

    //迭代算法
    private static void invertTreeLevel(TreeNode root) {
        if(root == null) return;
        Queue<TreeNode> qu  = new LinkedList<>();
        qu.offer(root);
        while(!qu.isEmpty()){
            int len = qu.size();
            while(len > 0) {
                TreeNode node = qu.poll();
                if(node.left != null) qu.offer(node.left);
                if(node.right != null) qu.offer(node.right);
                //swap left and right
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                len --;
            }
        }
    }

//缩进的空格数 * depth

}
