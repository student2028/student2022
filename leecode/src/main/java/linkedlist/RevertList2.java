package linkedlist;

import common.ListNode;

/**
 * leecode 92 middle
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * <p>
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 * <p>
 * <p>
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * 一定要先用笔在纸上画一画 找一找思路
 * 链表的题没有那么难
 * 一点一点的技巧 积累起来
 * 今天的题是一点一点调出来的
 *
 */
public class RevertList2 {

    public static void main(String[] args) {

        ListNode head = ListNode.from(1, 2, 3, 4, 5);
        head.show();
        revertList(head, 2, 4).show();

        ListNode list2 = ListNode.from(6,7,8,9,10,11,12,13);
        list2.show();
        revertList(list2, 3,8).show();

    }

    private static ListNode revertList(ListNode head, int left, int right) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1, head);
        //存下来开始的那一个节点 和 最后一个节点
        int i = 1;
        ListNode npre = dummy;
        while (i < left) {
            npre = npre.next;
            head = head.next;
            i++;
        }
        npre.next = null;
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode temp = head;
        while (cur != null && i < right) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            i++;
        }

        temp.next = cur;
        npre.next = pre;
        return dummy.next;
    }

}
