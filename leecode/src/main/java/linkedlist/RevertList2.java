package linkedlist;

import common.ListNode;

/**
 * leecode 92 middle
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 输入：head = [1,2,3,4,5,6,7,8,9], left = 2, right = 4
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

        ListNode head = ListNode.from(1, 2, 3, 4, 5,6,7,8,9);
        head.show();
        ListNode node = reverseBetween(head, 2, 7);
        node.show();
        ListNode node2 = reverseBetween(ListNode.from(3, 5), 1, 2);
        node2.show();

        ListNode root1 = ListNode.from(1,2,3,4,5,6,7);
        root1.show();
        reverseRange(root1, 2,5).show();

    }


    /**
     * 头插法
     * next 要放到pre后面
     * 所以 next.next = pre.next;
     *     pre.next = next;
     *     但是 next.next 值要放哪里呢？ 所以前面有
     *     cur.next = next.next;
     *     pre/cur 都没有变化，但是它们后面的next 是一直在变
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        //先前进到left 假定 left = 1
        for (int i = 1; i < left ; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        //假定 left = 1 right = 2
        for(int i = 1 ; i < right - left + 1; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }


    //递归法 递归前N个节点
    static ListNode successor = null;
    public static ListNode reverseN(ListNode head, int n ) {
        if(n == 1) {
            successor = head.next;
            return head;
        }

        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    public static ListNode reverseRange(ListNode  head, int m, int n) {
        //base case
        if(m == 1) return reverseN(head, n);
        //前进到反转的起点
        head.next = reverseRange(head.next , m - 1, n -1);
        return head;
    }

}
