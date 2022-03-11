package binarytree;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化与反序列化
 */
public class CodeBT {
    public static void main(String[] args) {


    }

    public String code(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        //层序遍历
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);

        while (!qu.isEmpty()) {
            TreeNode node = qu.poll();
            if (node == null) {
                sb.append("#").append(",");
            } else {
                sb.append(node.val).append(",");
                qu.offer(node.left);
                qu.offer(node.right);
            }
        }

        return sb.toString();
    }

    public TreeNode decode(String data) {
        if (data.isEmpty()) return null;
        String[] strs = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(strs[0]));
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root);
        for (int i = 0; i < strs.length / 2; i++) {
            TreeNode node = qu.poll();
            if (strs[2 * i + 1].equals("#")) {
                node.left = null;
            } else {
                node.left = new TreeNode(Integer.valueOf(strs[2 * i + 1]));
                qu.offer(node.left);
            }

            if (strs[2 * i + 2].equals("#")) {
                node.right = null;
            } else {
                node.right = new TreeNode(Integer.valueOf(strs[2 * i + 2]));
                qu.offer(node.right);
            }
        }

        return root;
    }
}
