package linkedlist;

import common.ListNode;

import java.util.Stack;

/**
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 示例1：
 * <p>
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 * <p>
 * 示例2：
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 * <p>
 * 示例3：
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 * 此题和上一个addTwoNum的区别在于 它不是逆序存储的
 * 所以要么直接使用栈 要么直接转成字符串进行处理
 */
public class AddTwoNums2 {
    public static void main(String[] args) {
        ListNode l1 = ListNode.from(7, 2, 4, 3);
        ListNode l2 = ListNode.from(5, 6, 4);
        ListNode l3 = new AddTwoNums2().addTwoNums(l1, l2);
        l3.show();
    }

    public ListNode addTwoNums(ListNode l1, ListNode l2) {
        //这道题使用栈比较好哇
        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();
        int sum , carry = 0;
        ListNode res = null;
        while(l1!=null) { st1.push(l1.val()); l1 = l1.next; }
        while(l2!=null) { st2.push(l2.val()); l2 = l2.next; }

        while(carry > 0 || !st1.isEmpty() || !st2.isEmpty()){
            int a = st1.isEmpty() ? 0 : st1.pop();
            int b = st2.isEmpty() ? 0 : st2.pop();
            sum = a + b + carry;
            carry = sum / 10;
            sum = sum % 10;
            res = new ListNode(sum, res);
        }

        return res;
    }
}
