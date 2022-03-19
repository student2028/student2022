package binarytree;

import common.TreeNode;

import java.util.Stack;

/**
 * leecode 98
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 由定义可知，二叉搜索树 的中序遍历 左中右 输出 是一个递增有序数组。
 * 所以我们可以根据这一点进行判断
 * 可以写递归和非递归的两种方法
 * 非递归的使用中序遍历 稍加改动即可
 * 判断 如果违反定义返回false
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 */
public class ValidateBST {

    //use for isValidtBST
    static TreeNode pre = null;

    public static void main(String[] args) {

        TreeNode root = TreeNode.of(2, 2, 2);
        System.out.println(isValidBST(root));

        TreeNode root2 = TreeNode.of(3, 2, 4);
        root2.show();
        System.out.println(isValidBST(root2));

    }

    private static boolean isValidBST(TreeNode root) {

        if (root == null) return true;
        boolean left = isValidBST(root.left);
        if (pre != null && pre.val >= root.val) return false;
        pre = root;
        boolean right = isValidBST(root.right);
        return left && right;
    }


    //使用代码随想录上的迭代模板即可
    //中序遍历即可 中序遍历就是升序
    private static boolean validateBST(TreeNode root) {
        if(root == null) return true;

        Stack<TreeNode> st = new Stack<>();
        st.push(root);

        Integer preVal = null;
        TreeNode node = null;

        while(!st.isEmpty()) {
            node = st.pop();
            if(node != null) {
                if(node.right!= null) st.push(node.right);
                st.push(node);
                st.push(null);
                if(node.left != null) st.push(node.left);
            } else
            {
                if(preVal == null) { preVal = st.pop().val; continue; }
                node = st.pop();
                if(preVal >= node.val)  return false;
                preVal = node.val;
            }
        }
        return true;
    }


}
