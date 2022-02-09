package binarytree;


import common.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * leecode 236
 * <p>
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * <p>
 * 1.这道题的递规算法 写起来比较容易，理解起来有些困难
 * 我是这样理解的 找一个具体的例子 看着代码如何在这个树上进行执行
 * 一直走其左孩子 ，如果左孩子是叶子节点了，还没有找到，则返回出栈，再判断右孩子
 * 举一个具体的例子 一定要推演一下，这样就慢慢地理解了递归的代码
 * 2.非递归代码的思路
 * 使用一个hashMap 存储每一个treeNode的父节点，这样我们就可以根据p 一直跳出来一个链，表示其 p->parent->parent的链表 存在hashset中去
 * 然后拿这个去q中去找，找到就返回 就是最近的公共节点，找不到就找q.parent, 一直找不到就返回null
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {

        TreeNode root = TreeNode.of(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        root.show();

        TreeNode p = root.find(5);
        TreeNode q = root.find(1);

        System.out.println(lowestCommonAncestor(root, p, q));


    }

    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果根节点等于其中某一个节点 肯定 root就是要找的
        if (root == null || root == q || root == p) return root;

        //从左子树找
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        //从右子树找
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //判断
        if (left != null && right != null) return root;
        else if (left == null && right != null) return right;
        else if (right == null && left != null) return left;
        else return null;

    }

    //迭代的思路 前提是每一个treeNode的val值都是不同的
    static Map<Integer, TreeNode> map = new HashMap<>();//用来存储每一个节点的和他的父节TreeNode 方便判断 而没存成integer
    static Set<Integer> set = new HashSet<>();//用来存储p的父亲链条

    private static void dfs(TreeNode root) {
        if (root.left != null) {
            map.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            map.put(root.right.val, root);
            dfs(root.right);
        }
    }

    private static TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        //填充map
        dfs(root);
        //找p的父亲链条存到set中去
        while (p != null) {
            set.add(p.val);
            p = map.get(p.val);//找到p的父亲
        }
        //从set中查找是否有q的父亲
        while (q != null) {
            if (set.contains(q.val)) return q;
            q = map.get(q.val);//寻找q的父亲
        }
        return null;
    }

}
