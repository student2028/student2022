package linkedlist;

import common.ListNode;

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
        //转成字符串进行处理 然后头插法
        ListNode l3 = null;
        StringBuilder s1 = new StringBuilder(100);
        StringBuilder s2 = new StringBuilder(100);
        while (l1 != null) {
            s1.append(l1.val());
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.append(l2.val());
            l2 = l2.next;
        }
        int sum = 0;
        int carry = 0;
        String add1 = s1.toString();
        String add2 = s2.toString();
        int len1 = add1.length() - 1;
        int len2 = add2.length() - 1;
        while (len1 > -1 || len2 > -1 || carry > 0) {
            int a = len1 > -1 ? add1.charAt(len1--) - '0' : 0;
            int b = len2 > -1 ? add2.charAt(len2--) - '0' : 0;
            sum = a + b + carry;
            carry = sum / 10;
            sum = sum % 10;
            ListNode ln = new ListNode(sum, l3);
            l3 = ln;
        }

        return l3;
    }
}
