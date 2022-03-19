package linkedlist;

import common.ListNode;

/**
 * leecode 2
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例 342 + 465 = 807
 * 2 - 4 - 3
 * 5 - 6 - 9
 * 7 - 0 - 3 - 1
 * 思考：
 * 链表遍历 相同位置的元素相加
 * 还要考虑逆序 挺好的  个位就是第一个
 * 直接遍历即可
 * 如果是顺序的 还需要首先 反转一下链表
 * <p>
 * 之前做过类似的题 两个 数字字符串 加法
 */
public class AddTwoNums {

    public static void main(String[] args) {

        ListNode num1 = ListNode.of(new int[]{3, 4, 2});
        ListNode num2 = ListNode.of(new int[]{9, 6, 5});
        num1.show();
        num2.show();

        addTwoNums(num1, num2).show();

    }

    private static ListNode addTwoNums(ListNode num1, ListNode num2) {

        //代表加法的进位
        int carry = 0;
        ListNode add1 = num1;
        ListNode add2 = num2;

        ListNode head = new ListNode();
        ListNode sums = head;

        int n1 = 0;
        int n2 = 0;
        int sum = 0;
        //这里最容易忽略的就是carr!=0
        while (add1 != null || add2 != null || carry != 0) {
            n1 = add1 != null ? add1.val() : 0;
            n2 = add2 != null ? add2.val() : 0;
            sum = n1 + n2 + carry;
            carry = sum / 10;
            sum = sum % 10;

            sums.next = new ListNode(sum);
            sums = sums.next;

            if (add1 != null) add1 = add1.next;
            if (add2 != null) add2 = add2.next;
        }

        return head.next;
    }


}
