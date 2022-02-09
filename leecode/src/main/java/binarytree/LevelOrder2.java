package binarytree;

import common.TreeNode;

import java.util.*;

/**
 * leecode 107
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 使用队列和栈相结合？
 */
public class LevelOrder2 {

    public static void main(String[] args) {

//        TreeNode root  = TreeNode.of(new Integer[]{3,9,20,null,null,5,7});
//        List<List<Integer>> list =  levelOrder2(root);
//        System.out.println(list);

        TreeNode root = TreeNode.of(1, 2, 3, 4, null, null, 5);
        List<List<Integer>> list = zigzagLevelOrder(root);
        System.out.println(list);


    }

    private static List<List<Integer>> levelOrder2(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        Stack<List<Integer>> st = new Stack<>();
        while (!qu.isEmpty()) {
            int len = qu.size();
            List<Integer> tl = new ArrayList<>();
            while (len > 0) {
                TreeNode node = qu.poll();
                tl.add(node.val);
                if (node.left != null) qu.offer(node.left);
                if (node.right != null) qu.offer((node.right));
                len--;
            }
            st.push(tl);
        }

        while (!st.isEmpty()) {
            List<Integer> item = st.pop();
            list.add(item);
        }

        return list;
    }


    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //和层序遍历类似 可以加一个判断 如果是偶数层的时候，先添加右孩子 否则添加左孩子
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        boolean flag = false;
        while (!qu.isEmpty()) {
            int len = qu.size();
            List<Integer> list = new ArrayList<>();
            while (len > 0) {
                TreeNode node = qu.poll();
                list.add(node.val);
                if (node.left != null) qu.offer(node.left);
                if (node.right != null) qu.offer(node.right);
                len--;
            }
            if(flag) {
                Collections.reverse(list);
            }
            flag = !flag;
            ans.add(list);
        }
        return ans;
    }

}
