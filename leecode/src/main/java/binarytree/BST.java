package binarytree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static common.Utils.printArray;

/**
 * 二叉搜索树相关问题
 * binary search tree :BST
 * <p>
 * 二叉搜索树的特性
 * 根节点的值大于左节点的值  小于右节点的值
 * 它左右子树也满足以上条件
 * 所以二叉搜索树的中序遍历结果值就是一个 有序的数组
 */
public class BST {

    public static void main(String[] args) {

        TreeNode root = TreeNode.of(1, 0, 2, 2);
        BST bst = new BST();
        printArray(bst.getMajors(root));

        TreeNode root2 = TreeNode.of(2, 1);
        bst.insertNode(root2, 3);
        root2.show();

        bst.deleteNode(root2, 3);
        root2.show();


        TreeNode root3 = bst.sortedArrayToBST(new int[]{1, 2, 3, 4, 5});
        root3.show();


        TreeNode root4 = bst.sortedArrayToBST(new int[]{10, 5, 15, 3, 7, 0, 18});
        int low = 7, high = 15;
        int sum = bst.rangeSumBST(root4,low,high);
        System.out.println(sum);

    }

    /**
     * 寻找BST中的众数 众数可能有多个啊
     */

    List<Integer> list = new ArrayList<>();
    int count = 0;
    int maxCount = 0;
    TreeNode pre = null;

    public int[] getMajors(TreeNode root) {

        findMajor(root);
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        //list.stream().mapToInt(x->x).toArray();
        return ans;
    }

    private void findMajor(TreeNode root) {
        if (root == null) return;
        findMajor(root.left);

        if (pre == null || pre.val != root.val) count = 1;
        else count++;

        if (count > maxCount) {
            maxCount = count;
            list.clear();
            list.add(root.val);
        } else if (count == maxCount) {
            list.add(root.val);
        }

        pre = root;

        findMajor(root.right);
    }

    //insert node to bst
    public TreeNode insertNode(TreeNode root, int key) {
        //如果为空 则直接返回一个新节点
        if (root == null) return new TreeNode(key);

        //如果根值大于key 则去左子树找地方插入
        if (root.val > key) {
            root.left = insertNode(root.left, key);
        }
        //如果根节点小于key 则去右子树找地方插入
        if (root.val < key)
            root.right = insertNode(root.right, key);

        return root;
    }

    /**
     * 删除 BST 中的节点
     * 分五种情况
     * 1. 如果节点为空 返回空
     * 2. 如果找不到节点 返回 root
     * 3. 如果节点左子树为空 则返回右子树
     * 4. 如果节点右子树为空 返回左子树
     * 5.如果左右子树都不为空 则右子树占位，左子树去右子树第一个左孩子的左孩子...下面补位即可
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val == key) {
            //找到该节点
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            else {
                TreeNode tmp = root.right;
                while (tmp.left != null) tmp = tmp.left;
                tmp.left = root.left; //左孩子 给右子中最左面的插入
                root = root.right; //右孩子占位
                return root;
            }
        }
        if (root.val > key) root.left = deleteNode(root.left, key);
        if (root.val < key) root.right = deleteNode(root.right, key);

        return root;
    }

    /**
     * 有序数组转成bst
     * 首先取数组中间元素的位置，不难写出int mid = (left + right) / 2;，这么写其实有一个问题，就是数值越界，
     * 例如left和right都是最大int，这么操作就越界了，在二分法 (opens new window)中尤其需要注意！
     * 这里int mid = left + ((right - left) / 2);的写法相当于是如果数组长度为偶数，中间位置有两个元素，取靠左边的。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return traversal(nums, 0, nums.length - 1);
    }

    private TreeNode traversal(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = traversal(nums, left, mid - 1);
        root.right = traversal(nums, mid + 1, right);
        return root;
    }

//    给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。

    public int rangeSumBST(TreeNode root, int low, int high) {
            if (root == null) return 0;
            int sum = 0;
            if (root.val >= low && root.val <= high) {
                sum = root.val;
            }
            //优化点 可以加上剪枝
           int  left = root.val < low ? 0 : rangeSumBST(root.left, low, high);
           int right = root.val > high ? 0 :rangeSumBST(root.right, low, high);
            return sum + left + right;
     }



}
