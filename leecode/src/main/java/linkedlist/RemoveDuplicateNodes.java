package linkedlist;

import common.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 */
public class RemoveDuplicateNodes {

    public static void main(String[] args) {
        ListNode head = ListNode.from(1, 1, 1, 1, 2, 3, 3);
        head.show();
        RemoveDuplicateNodes test = new RemoveDuplicateNodes();
        test.deleteDuplicates(head);
        head.show();

    }

    public ListNode deleteDuplicates(ListNode head) {
        //删除重复元素 已经升序排序了 如果当前值和下一个值相同 则让当前值的下一个等于next.next 否则才是cur = cur.next
        if (head == null) return head;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val() == cur.val())
                cur.next = cur.next.next;
            else cur = cur.next;
        }
        return head;
    }
}
