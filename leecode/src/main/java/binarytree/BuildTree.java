package binarytree;


import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * leecode 105. 从前序与中序遍历序列构造二叉树
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 * 前序遍历   根 左子树 右子树
 * 中序遍历   左子树  根 右子树
 *
 */
public class BuildTree {

    public static void main(String[] args) {
       int[] preorder = {3,9,20,15,7};
       int[] inorder = {9,3,15,20,7};

       TreeNode root = buildTree(preorder,inorder);
        root .show();
    }

    //用于存储 中序遍历的节点值 与 索引 值
    static Map<Integer, Integer> indexMap = new HashMap<>();

    private static TreeNode buildTree(int[] preorder, int[] inorder) {

        int N = inorder.length ;
        for (int i = 0; i < N; i++) {
            indexMap.put(inorder[i], i);
        }

        return buildTree(preorder,inorder,0,N-1, 0, N - 1);
    }

    private  static TreeNode buildTree(int[] preorder,int[] inorder, int il, int ir, int pl, int pr) {
         //递归终止条件
        if(pl > pr || il > ir) return null;

        TreeNode root   =  new TreeNode (preorder[pl]);
        //cur index in inorder
        int index = indexMap.get(root.val);
        root.left =  buildTree(preorder,inorder,il, index - 1, pl + 1, index - il + pl);
        root.right = buildTree(preorder,inorder,index + 1, ir, index - il + pl + 1, pr);
        return root;
    }



}
