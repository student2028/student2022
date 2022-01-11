package binarytree;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leecode 101 easy
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 * <p>
 * 1
 * 2          2
 * 3                  3
 * 如何判断镜像
 * 左子树 与 右子树 对称
 * left.right =
 */
public class SymmetricTree {

    public static void main(String[] args) {
        TreeNode root = TreeNode.of(1,2,2,3,4,4,3);
//        root.show();
//        System.out.println(isSymetric(root.left, root.right));

        TreeNode node2 = TreeNode.of(1, 2, 2, null, 3, null, 3);
        node2.show();
//        System.out.println(isSymetric(node2.left, node2.right));
        System.out.println(isSsmetric2(node2));
        System.out.println(isSsmetric2(root));

    }

    //递归三步曲 确定参数  确定终止条件 单步逻辑
    //对称比较的是左子树和 右子树 不是左节点和右节点 这点需要注意
    private static boolean isSymetric(TreeNode left, TreeNode right) {

        //如果节点是空 则是对称的 终止条件
        if (left == null && right == null) return true; // 两个都是 null
        if (left == null || right == null) return false; // 某一个是null
        if (left.val != right.val) return false;  //注意不要写成 == return true

        boolean outside = isSymetric(left.left, right.right);
        boolean inside = isSymetric(left.right, right.left);

        return inside && outside;
    }


    //非递归算法
    //使用普通队列
    private static boolean isSsmetric2(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> qu = new LinkedList<>();
        qu.offer(root.left);
        qu.offer(root.right);
        while (!qu.isEmpty()) {
            TreeNode left = qu.poll();
            TreeNode right = qu.poll();
            if (left == null && right == null) continue; //父节点是叶子节点 忽略即可
            if (left == null || right == null || left.val != right.val) return false;

            //outside
            qu.offer(left.left);
            qu.offer(right.right);
            //inside
            qu.offer(left.right);
            qu.offer(right.left);
        }

        return true;
    }

}
