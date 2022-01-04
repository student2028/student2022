package linkedlist;


import common.ListNode;

/**
 * leecode 148
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 */
public class SortList {
    public static void main(String[] args) {

        ListNode head = ListNode.from(-1, 5, 3, 4, 0);
        head.show();
        sortList(head).show();

    }

    private static ListNode sortList(ListNode head) {


        return head;
    }




}
