package binarytree;


import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leecode 515
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 *1,3,2,5,3,null,9
 */
public class LevelLargeValues {
    public static void main(String[] args) {

        TreeNode root = TreeNode.of(1,3,2,5,3,null,9);
        root.show();


        System.out.println();


    }


    public static  List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        while(!qu.isEmpty()) {
            int len = qu.size();//本层个数
            int line_max = Integer.MIN_VALUE;
            while(len > 0) {
                TreeNode node = qu.poll();//取出节点
                line_max = line_max < node.val ? node.val : line_max;
                if(node.left != null ) qu.offer(node.left);
                if(node.right != null) qu.offer(node.right);
                len --;
            }
            //一层结束 添加 line_max to list
            list.add(line_max);
            line_max = Integer.MIN_VALUE;
        }
        return list;
    }
}
