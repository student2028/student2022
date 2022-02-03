package binarytree;

import common.TreeNode;


/**
 * https://leetcode-cn.com/problems/binode-lcci/submissions/
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，
 * 把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，
 * 也就是在原始的二叉搜索树上直接修改。
 *
 * 返回转换后的单向链表的头节点。
 *
 * 注意：本题相对原题稍作改动
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binode-lcci
 */
public class Bst2List {

    public static void main(String[] args) {

        Bst2List test = new Bst2List();
        TreeNode root = TreeNode.array2BST(1,2,3,4,5,6);
        root = test.convertBiNode(root);
        root.show();
    }

    TreeNode head = new TreeNode(-1);
    TreeNode prev = head;

    public TreeNode convertBiNode(TreeNode root) {
        if(root == null) return root;
        convertBiNode(root.left);
        prev.right = root; //root成为前置节前的右孩子
        prev = root;  //root 变为下一次的前置节点了
        root.left = null;//root的左孩子置为空
        convertBiNode(root.right);
        return head.right;
    }

}
