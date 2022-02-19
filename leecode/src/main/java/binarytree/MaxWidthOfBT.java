package binarytree;

import common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * <p>
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 3     2
 * / \     \
 * 5   3     9
 * <p>
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * <p>
 * 1
 * /
 * 3
 * / \
 * 5   3
 * <p>
 * 输出: 2
 * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 * <p>
 * 示例 3:
 * <p>
 * 输入:
 * <p>
 * 1
 * / \
 * 3   2
 * /
 * 5
 * <p>
 * 输出: 2
 * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 * <p>
 * 示例 4:
 * <p>
 * 输入:
 * <p>
 * 1
 * / \
 * 3   2
 * /     \
 * 5       9
 * /         \
 * 6           7
 * 输出: 8
 * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 * <p>
 * 注意: 答案在32位有符号整数的表示范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-width-of-binary-tree
 * 深度遍历 与 层序遍历均可 思路都是一样的
 * 元素在数组中存的时候 p=1 pleft=2*p pright=2*p+1 记住这一点即可
 */
public class MaxWidthOfBT {

    public static void main(String[] args) {

    }

    //层序遍历
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int res = 1;
        Queue<TreeNode> qu = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        qu.offer(root);
        list.add(1); //index 值 索引值
        while (!qu.isEmpty()) {
            int len = qu.size();
            while (len > 0) {
                TreeNode node = qu.poll();
                int index = list.poll();

                if (node.left != null) {
                    qu.offer(node.left);
                    list.offer(index * 2);
                }
                if (node.right != null) {
                    qu.offer(node.right);
                    list.offer(index * 2 + 1);
                }
                len--;
            } //一层结束
            if (list.size() >= 2)
                res = Math.max(res, list.getLast() - list.getFirst() + 1);
        }
        return res;
    }


}
