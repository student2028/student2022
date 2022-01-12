package binarytree;


import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * leecode 114 二叉树展开为链表
 * 叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *  
 * 输入：root = [1,2,5,3,4,null,6]
 * 1
 * 2        5
 * 3   4    null   6
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * <p>
 * <p>
 *     自己写的答案很丑啊，多用了一个队列，其实不需要用的
 *     递归写法看的题解
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 */
public class Tree2List {

    public static void main(String[] args) {
        //  TreeNode root = TreeNode.of(1, 2, 5, 3, 4, null, 6);
        TreeNode root = TreeNode.of(0);

       // root.listShow();
        flattenR(root);
        root.listShow();
    }

    //画画图就好理解了 自己在本本上好好画画
    private static void flattenR(TreeNode root) {
        if(root==null) return;  //中
        flatten(root.left);     // 左
        flatten(root.right);    // 右
        TreeNode last = root.right;    //存old右
        root.right = root.left;         //左移到右
        root.left = null;               // 左置空
        while(root.right!=null) root = root.right; // 指向右末尾
        root.right = last;         //拼接

    }

    //即原地修改root 这种写法丑陋啊
    private static void flatten(TreeNode root) {
        if (root == null  ) return;
        // 如果只有root 也要判断  否则下面会出现环
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        Queue<TreeNode> qu = new LinkedList<>();
        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            System.out.print(node.val + "\t");
            qu.offer(node);
            if (node.right != null) st.push(node.right);
            if (node.left != null) st.push(node.left);
        }
        TreeNode temp = root;
        while (!qu.isEmpty()) {
            TreeNode node = qu.poll();
            if(temp != node) {
                temp.left = null;
                temp.right = node;
                temp = node;
            }
        }
    }

}