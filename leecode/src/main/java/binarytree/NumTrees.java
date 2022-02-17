package binarytree;


/**
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 *
 * dp[3]，就是 元素1为头结点搜索树的数量 + 元素2为头结点搜索树的数量 + 元素3为头结点搜索树的数量
 * 元素1为头结点搜索树的数量 = 右子树有2个元素的搜索树数量 * 左子树有0个元素的搜索树数量
 * 元素2为头结点搜索树的数量 = 右子树有1个元素的搜索树数量 * 左子树有1个元素的搜索树数量
 * 元素3为头结点搜索树的数量 = 右子树有0个元素的搜索树数量 * 左子树有2个元素的搜索树数量
 * 有2个元素的搜索树数量就是dp[2]。
 * 有1个元素的搜索树数量就是dp[1]。
 * 有0个元素的搜索树数量就是dp[0]。
 * 所以dp[3] = dp[2] * dp[0] + dp[1] * dp[1] + dp[0] * dp[2]
 */
public class NumTrees {

    public int numTrees(int n ){
        //分步计数原理 所以是相乘
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <=n ; i++) {
            for (int j = 1; j <=i ; j++) {
                dp[i] += dp[i-j] * dp[j-1];
            }
        }

        return dp[n];
   }

    public static void main(String[] args) {

        NumTrees numTrees = new NumTrees();
        System.out.println(numTrees.numTrees(2));
        System.out.println(numTrees.numTrees(3));
    }
}
