package binarytree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * leecode 199
 * 二叉树的右侧视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 输入: [1,null,3]
 * 输出: [1,3]
 *
 *   还是用层序遍历的套路模板
 *   思路想清楚了，代码就很容易写了
 *
 */
public class RightSideView {

    public static void main(String[] args) {
        TreeNode root = TreeNode.of(1, 2, 3, null, 5, null, 4);
        root.show();
        System.out.println();
        System.out.println(rightSideView(root));
        TreeNode root2 = TreeNode.of(1, 3, null);
        System.out.println(rightSideView(root2));
    }

    private static List<Integer> rightSideView(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        TreeNode node = null;
        while (!qu.isEmpty()) {
            int len = qu.size();
            while (len > 0) {
                node = qu.poll();
                if (node.left != null) qu.offer(node.left);
                if (node.right != null) qu.offer(node.right);
                len--;
            }
            //一层结束 ，最右侧的值存在node中
            list.add(node.val);
        }

        return list;
    }
}
