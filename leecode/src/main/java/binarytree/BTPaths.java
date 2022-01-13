package binarytree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/***
 * leecode 257 二叉树的所有路径
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * 叶子节点 是指没有子节点的节点。
 *
 * 类型 简单题（简单我也不会哈）
 *
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 *
 *
 */
public class BTPaths {

    public static void main(String[] args) {

        TreeNode root = TreeNode.of(1, 2, 3, null, 5);
        root.show();

        List<String> res = btPaths(root);
        res.stream().forEach(System.out::println);

    }

    private static List<String> btPaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null) return result;
        List<Integer> paths = new ArrayList<>();
        traversal(root, paths, result);
        return result;
    }

    /**
     * 递归加回溯
     * 根节点到叶子节点
     * 先序遍历的思路
     * 使用List<Integer>  来存储临时数据 到叶子节点 就完成一条完整的路径
     */
    private static void traversal(TreeNode root, List<Integer> paths, List<String> result) {

        paths.add(root.val); //前序 先读取根节点的值
        //如果是叶子节点
        if (root.left == null && root.right == null) {
            result.add(paths.stream().map(x -> x.toString()).collect(Collectors.joining("->")));
        }

        if (root.left != null) {
            traversal(root.left, paths, result);
            //回溯
            paths.remove(paths.size() - 1);//去掉尾部元素
        }

        if (root.right != null) {
            traversal(root.right, paths, result);
            paths.remove(paths.size() - 1);
        }
    }


    /**
     * 迭代法 copy from carl programmer
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null)
            return result;
        Stack<Object> stack = new Stack<>();
        // 节点和路径同时入栈
        stack.push(root);
        stack.push(root.val + "");
        while (!stack.isEmpty()) {
            // 节点和路径同时出栈
            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();
            // 若找到叶子节点
            if (node.left == null && node.right == null) {
                result.add(path);
            }
            //右子节点不为空
            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }
            //左子节点不为空
            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
        }
        return result;
    }

}
