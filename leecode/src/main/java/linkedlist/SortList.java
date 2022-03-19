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
 * <p>
 * 链表归并排序
 * 如何寻找mid 使用快慢指针
 */
public class SortList {
    public static void main(String[] args) {

        ListNode head = ListNode.from(4,2,1,3);
        head.show();

        mergeSort(head).show();

    }

    private static ListNode mergeSort(ListNode head) {

        if(head == null || head.next == null) return head;

        ListNode mid = getMid(head);
        ListNode rightHead = mid.next;  //右边的链表部分
        mid.next = null;   //断开中间节点与后面的联系 head 成为左边的链表部分

        ListNode left = mergeSort(head);
        ListNode right = mergeSort(rightHead);

        return mergeLists(left,right);

    }

    private static ListNode mergeLists(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
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
        return dummy.next;
    }

    //这种写法的问题是 如果只有两个元素链表，则每次都返回的是第二个元素 导致一个死循环
    //所以要么加一个pre 要么初始化的时候 用fast = head.next.next;
    private static ListNode getMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }


}
