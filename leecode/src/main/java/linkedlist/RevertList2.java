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
 */
public class RevertList2 {

    public static void main(String[] args) {

        ListNode head = ListNode.from(1, 2, 3, 4, 5);
        head.show();
        ListNode node = reverseBetween(head, 2, 4);
        node.show();
        ListNode node2 = reverseBetween(ListNode.from(3, 5), 1, 2);
        node2.show();


    }


    /**
     * 头插法
     * next 要放到pre后面
     * cur要放到next后面
     * 所以代码是
     * next = cur.next
     * cur.next = next.next
     * next.next = pre.next;
     * pre.next = next;
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }


}
