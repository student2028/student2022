package linkedlist;


import common.ListNode;

/**
 * Leecode 21 easy
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 * 按归并的思路来做即可
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 */
public class MergeTwoLists {
    public static void main(String[] args) {

        ListNode l1 = ListNode.from(new int[]{1, 2, 4});
        ListNode l2 = ListNode.from(new int[]{1, 3, 4});

        l1.show();
        l2.show();

        mergeTwoLists(l1, l2).show();

        ListNode l3 = null;
        ListNode l4 = new ListNode(0);
        mergeTwoLists(l3, l4).show();

    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode temp = head;
        while (l1 != null && l2 != null) {
            if (l1.val() < l2.val()) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1 == null ? l2 : l1;

        return head.next;
    }


}
