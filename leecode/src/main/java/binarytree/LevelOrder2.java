package binarytree;

import common.TreeNode;

import java.util.*;

/**
 * leecode 107
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 使用队列和栈相结合？
 *
 */
public class LevelOrder2 {

    public static void main(String[] args) {

        TreeNode root  = TreeNode.of(new Integer[]{3,9,20,null,null,5,7});

        List<List<Integer>> list =  levelOrder2(root);
        System.out.println(list);

    }

    private  static List<List<Integer>> levelOrder2(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();
        if(root == null) return list;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        Stack<List<Integer>> st = new Stack<>();
        while(!qu.isEmpty()) {
            int len = qu.size();
            List<Integer> tl = new ArrayList<>();
            while(len > 0) {
                TreeNode node = qu.poll();
                tl.add(node.val);
                if(node.left != null) qu.offer(node.left);
                if(node.right != null) qu.offer((node.right));
                len --;
            }
            st.push(tl);
        }

        while(!st.isEmpty()) {
            List<Integer> item = st.pop();
            list.add(item);
        }

        return list;
    }
}
