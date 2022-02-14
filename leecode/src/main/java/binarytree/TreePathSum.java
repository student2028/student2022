package binarytree;

import common.TreeNode;

import java.util.*;

/**
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * 解释：等于目标和的根节点到叶节点路径如上图所示。
 * <p>
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 */
public class TreePathSum {

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        //使用一个栈来进行遍历 如果节点是叶子节点 则判断和是不是等于targetSum
        //如果不是叶子节点但是 数据和已经大于targetsum也没有必要处理了

        return false;
    }

    //官解方法之一 递归
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 首先我们可以想到使用广度优先搜索的方式，记录从根节点到当前节点的路径和，以防止重复计算。
     * 这样我们使用两个队列，分别存储将要遍历的节点，以及根节点到这些节点的路径和即可。
     */
    public boolean bfs(TreeNode root, int targetSum) {
        if(root == null) return false;
        Queue<TreeNode> queNode = new LinkedList<>();
        Queue<Integer> queVal = new LinkedList<>();
        queNode.offer(root);
        queVal.offer(root.val);
        while(!queNode.isEmpty()) {
            TreeNode node = queNode.poll();
            int num = queVal.poll();
            //如果当前节点是叶子节点并且值等于targetSum 返回true
            if(node.left == null && node.right == null && num == targetSum) {
                 return true;
            }
            if(node.left != null) {
                queNode.offer(node.left);
                queVal.offer(node.left.val + num);
            }
            if(node.right != null) {
                queNode.offer(node.right);
                queVal.offer(node.right.val + num);
            }
        }
        return false;
    }

    public boolean dfs(TreeNode root, int targetSum) {
        if(root == null) return false;
        Stack<TreeNode> stNode = new Stack<>();
        Stack<Integer> stVal = new Stack<>();
        stNode.push(root);
        stVal.push(root.val);
        while(!stNode.isEmpty()){
            TreeNode node = stNode.pop();
            int num = stVal.pop();
            //如果是叶子节点
            if(node.left == null && node.right == null && num == targetSum) return true;
            if(node.left != null) {
                stNode.push(node.left);
                stVal.push(node.left.val + num);
            }
            if(node.right != null){
                stNode.push(node.right);
                stVal.push(node.right.val + num);
            }
        }
        return false;
    }

    //leecode 路径总和2
    //https://leetcode-cn.com/problems/path-sum-ii/submissions/
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        //这个题的思路也比较明显 做就是了 根节点到叶子节点
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> qu = new LinkedList<>();
        Queue<List<Integer>> qu2 = new LinkedList<>();
        qu.offer(root);
        List<Integer> list = new ArrayList();
        list.add(root.val);
        qu2.offer(list);
        TreeNode node = null;
        while(!qu.isEmpty()) {
            node = qu.poll();
            list = qu2.poll();
            if(node.left == null && node.right == null) {
                //leaf node, so do it judge equal or not if equal then add
                System.out.println(list);
                if(list.stream().mapToInt(x->x).sum() == targetSum)
                    res.add (new ArrayList<>(list));
            }
            if(node.left != null) {
                qu.offer(node.left);
                List<Integer> ll = new ArrayList<>(list);
                ll.add(node.left.val);
                qu2.offer(ll);
            }
            if(node.right != null) {
                qu.offer(node.right);
                List<Integer> lr = new ArrayList<>(list);
                lr.add(node.right.val);
                qu2.offer(lr);
            }
        }

        return res;
    }

        public static void main(String[] args) {

        TreeNode root = TreeNode.of(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1);
        TreePathSum test = new TreePathSum();
        root.show();
        System.out.println(test.hasPathSum(root, 22));

    }
}
