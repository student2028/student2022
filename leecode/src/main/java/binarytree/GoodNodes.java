package binarytree;

import common.TreeNode;

/**
 * leecode 1448
 * 二叉树中的好节点数目
 * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
 * <p>
 * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
 * 二叉树中节点数目范围是 [1, 10^5] 。
 * 每个节点权值的范围是 [-10^4, 10^4] 。
 * <p>
 * 输入：root = [3,1,4,3,null,1,5]
 * 输出：4
 */
public class GoodNodes {

    public static void main(String[] args) {
        GoodNodes goodNodes = new GoodNodes();
        TreeNode root = TreeNode.of(3,1,4,3,null,1,5);
        root.show();
        System.out.println(goodNodes.goodNodes(root));
    }

    int ans = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return ans;
    }

    public void dfs(TreeNode root, int max) {//父节点路径上最大值
        if (root == null) return;
        if (root.val >= max) {
            max = root.val;
            ans++;
        }

        dfs(root.left, max);
        dfs(root.right, max);
    }

}
