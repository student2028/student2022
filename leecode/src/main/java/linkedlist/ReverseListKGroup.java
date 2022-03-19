package linkedlist;

import common.ListNode;

/** leecode 25  k个一组反转链表
 * k个一组反转链表 如果最后剩下的不够k个 则不反转
 *      给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 进阶：
 *     你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 *     你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
  */
public class ReverseListKGroup {

    public static void main(String[] args) {

        ReverseListKGroup test = new ReverseListKGroup();
        ListNode head = ListNode.from(1, 2, 3, 4, 5, 6, 7,8,9);
        head.show();
        test.reverseKGroup(head, 3).show();

      }


    public ListNode reverseKGroup(ListNode head, int k) {
        //反复调用 reverseList方法
        int len = 0;
        ListNode cur = head;
        while(cur != null) {cur = cur.next; len ++; }

        int start = 1;
        while(start + k - 1 <= len) {
            head = reverseList(head, start, start + k - 1);
            start += k;
        }

        return head;
    }

    //参考92 可以反转 left.. right 之间的部分链表 反复调用这个方法来操作
    //题目中索引 left right是从1开始 计算的
    public ListNode reverseList(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        int i = 1;
        while(i < left) { pre = pre.next; i++; }
        ListNode cur = pre.next;
        while(i++ < right) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }



}
