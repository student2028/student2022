package linkedlist;

import common.ListNode;

/**
 * leecode 234
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 输入：head = [1,2]
 * 输出：false
 */
public class PalindromeList {

    public static void main(String[] args) {

        ListNode list = ListNode.from(1,2,2,1);
        list.show();

        PalindromeList test = new PalindromeList();
        System.out.println(test.isPalindrome(list));

    }

    ListNode left = null;

    public boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }
    public boolean traverse(ListNode right) {
        if(right == null) return true;
        boolean res  = traverse(right.next);
        //后序遍历代码
        res = res && left.val() == right.val();
        left = left.next;
        return res;
    }

}
