package binarytree;

import common.TreeNode;

/**
 *
 * 这道题官解有三种思路
 * 第三种是树hash
 * 第二种是kmp
 * 第一种是dfs
 *
 */

public class IsSubTree {

    public static void main(String[] args) {


    }

    public boolean isSubTree(TreeNode s, TreeNode t) {
        return dfs(s,t);
    }

    public boolean dfs(TreeNode s, TreeNode t) {
        if(s == null) return false;
        return check(s,t) || dfs(s.left, t) || dfs(s.right, t);
    }

    public boolean check(TreeNode s, TreeNode t) {
         if(s == null && t == null) return true;
         if(s == null || t == null || s.val != t.val) return false;

         return check(s.left, t.left) && check(s.right, t.right);
    }

}
