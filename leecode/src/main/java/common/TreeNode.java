package common;

import java.util.*;


/**
 * 二叉树节点
 * 二叉树
 * 从数组存储转成链表存储
 * 层序遍历
 * 递归遍历
 * 迭代遍历
 * 添加节点
 * 删除节点
 * 转成链表
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode {" +
                "val=" + val +
                '}';
    }

    //假定TreeNode is a list
    public void listShow() {
        TreeNode temp = this;
        while(temp != null) {
            System.out.print(temp.val + "\t");
            System.out.print("null" + "\t");
            temp = temp.right;
        }
    }

    //根据传入的数字数组来构建二叉树
    //根据顺序存储的数组 构建链式存储的二叉树
    //先从叶子结点构建  从后往前
//    public static TreeNode of(int... arr) {
//
//        int N = arr.length;
//        TreeNode[] nodes = new TreeNode[N];
//        for (int i = N - 1; i >= 0; i--) {
//            TreeNode node = new TreeNode(arr[i]);
//            nodes[i] = node;
//            //find it's  left and right child if possible
//            if (2 * i + 1 < N && nodes[2 * i + 1] != null) node.left = nodes[2 * i + 1];
//            if (2 * i + 2 < N && nodes[2 * i + 2] != null) node.right = nodes[2 * i + 2];
//        }
//        return nodes[0];
//    }

    public static TreeNode of(Integer... arr) {
        int N = arr.length;
        TreeNode[] nodes = new TreeNode[N];
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] != null) {
                TreeNode node = new TreeNode(arr[i]);
                nodes[i] = node;
                //find it's  left and right child if possible
                if (2 * i + 1 < N && nodes[2 * i + 1] != null) node.left = nodes[2 * i + 1];
                if (2 * i + 2 < N && nodes[2 * i + 2] != null) node.right = nodes[2 * i + 2];
            } else nodes[i] = null;
        }
        return nodes[0];
    }

    public void show() {
        System.out.print("level order:  \n");
        levelOrderTraverse(this);
    }

    public void preOrderShow() {
        System.out.print("pre order:    ");
        preOrderTraversalR(this);
        System.out.println();
    }

    public void inOrderShow() {
        System.out.print("middle order: ");
        inOrderTraversalR(this);
        System.out.println();
    }

    public void postOrderShow() {
        System.out.print("post order:   ");
        postOrderTraversalR(this);
        System.out.println();
    }


    private void preOrderTraversalR(TreeNode tree) {
        if (tree == null) return; //递归终止条件
        System.out.print(tree.val + "\t");  //单层递归操作
        preOrderTraversalR(tree.left);
        preOrderTraversalR(tree.right);
    }


    private void inOrderTraversalR(TreeNode tree) {
        if (tree == null) return; //递归终止条件
        inOrderTraversalR(tree.left);
        System.out.print(tree.val + "\t");  //单层递归操作
        inOrderTraversalR(tree.right);
    }

    private void postOrderTraversalR(TreeNode tree) {
        if (tree == null) return; //递归终止条件
        postOrderTraversalR(tree.left);
        postOrderTraversalR(tree.right);
        System.out.print(tree.val + "\t");  //单层递归操作

    }

    //迭代算法遍历二叉树
    //借助栈 递归能实现的 栈都能实现 递归在os中就是通过系统栈来实现的
    public void preOrderTraversal() {
        System.out.print("pre    order: "); //中左右 所以入栈顺序是 右左中
        Stack<TreeNode> stack = new Stack();
        stack.push(this);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                stack.push(node);
                stack.push(null);//标记一下，遇到它可以访问了 现在是处理数据
            } else {
                //开始访问数据
                System.out.print(stack.pop().val + "\t");
            }
        }
        System.out.println();

    }

    //迭代法中序遍历  左中右的输出方式  那入栈的时候就是 右中左
    public void inOrderTraversal() {
        System.out.print("middle order: ");
        Stack<TreeNode> stack = new Stack();
        stack.push(this);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (node.right != null) stack.push(node.right);
                stack.push(node);
                stack.push(null);//标记一下，遇到它可以访问了 现在是处理数据
                if (node.left != null) stack.push(node.left);
            } else {
                //开始访问数据
                System.out.print(stack.pop().val + "\t");
            }
        }
        System.out.println();

    }

    public void postOrderTraversal() {
        System.out.print("post   order: "); //左右中 入栈顺序则为 中右左
        Stack<TreeNode> stack = new Stack();
        stack.push(this);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                stack.push(node);
                stack.push(null);//标记一下，遇到它可以访问了 现在是处理数据
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            } else {
                //开始访问数据
                System.out.print(stack.pop().val + "\t");
            }
        }
        System.out.println();

    }

    //中序遍历 迭代算法2
    public void inOrder2() {
        TreeNode cur = this;
        Deque<TreeNode> st = new LinkedList<>();
        while(cur != null || !st.isEmpty()) {
            if(cur != null) {
                st.push(cur);
                cur = cur.left;
            } else {
                cur = st.pop();
                System.out.println(cur.val);
                cur = cur.right;
            }
        }
    }


    public   TreeNode find(int val) {
        return findNode(this, val);
    }

    private TreeNode findNode(TreeNode root, int val) {

        if(root == null || root.val == val) return root;
        //从左子树找
        TreeNode left = findNode(root.left, val);
        TreeNode right = findNode(root.right, val);

        if(left == null && right == null) return null;
        if(left == null && right != null) return right;
        if(right == null && left != null) return left;
        return null;
    }


    /**
     * 借助队列进行层序遍历
     *
     * @param root
     */
    private void levelOrderTraverse(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);

        while (!que.isEmpty()) {
            int len = que.size();
            while(len > 0) {
                TreeNode tnode = que.poll();
                System.out.print(tnode.val + "\t");
                if (tnode.left != null) que.offer(tnode.left);
                if (tnode.right != null) que.offer(tnode.right);
                len --;
            }
            //下一层之前输出断行
            System.out.println();
        }
    }

    public static TreeNode array2BST(int... nums) {
        return traversal(nums, 0, nums.length - 1);
    }

    //从根节点到当前节点的所有路径
    List<List<TreeNode>> allPaths = new ArrayList<>();
    public List<List<TreeNode>> paths(TreeNode root) {

         return  allPaths;
    }

    private static TreeNode traversal(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = traversal(nums, left, mid - 1);
        root.right = traversal(nums, mid + 1, right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.of(1, 2, 3, 4, 5, 6, 7);



     }

}


