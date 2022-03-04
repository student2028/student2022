package binarytree;

import common.TreeNode;

/**
 * leecode 543 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * <p>
 * 1.一般情况下 可能需要穿越根节点 树分布比较平均的情况下
 * 2.特殊情况下 某一子树特别深  ， 而另一侧很浅的情况下，最长的直径就不会穿过根节点
 * <p>
 * 所以求最长直径
 * 就要求 树的最大深度
 * <p>
 * node 为根节点的 深度为  max(left , right) + 1
 * left,right 为左右子树的深度
 * 则 经过该节点的最大节点数为 l + r + 1
 * <p>
 * 递归搜索每个节点并设一个全局变量 ans 记录node 节点数的最大值，最后返回 ans-1 即为树的直径。
 */
public class DiameterOfBT {

    static int ans = 0;

    public static void main(String[] args) {

        TreeNode root = TreeNode.of(1, 2, 3, 4, 5);
        root.show();
        System.out.println(diameterOfBT(root));

    }

    private static int diameterOfBT(TreeNode root) {
        depth(root);
        return ans;
    }

    //depth 是求以root为根节点的树的深度
    private static int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);

        ans = Math.max(ans, left + right);

        return Math.max(left, right) + 1;

    }


}
