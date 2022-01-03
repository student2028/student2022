package linkedlist;

import common.ListNode;

/**
 * 反转链表
 * 递归和非递归写法
 * <p>
 * 没什么好说的  熟悉就好了
 * 示例  1 -> 2 -> 3 -> 4 -> 5
 *
 */
public class RevertList {
    public static void main(String[] args) {


        ListNode head = ListNode.of(new int[]{1, 2, 3, 4, 5});

        head.show();

        //revertList(head).show();

        revertListR(head).show();


    }

    private static ListNode revertList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode cur = head.next;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = null;
        return pre;
    }

    /**
     * @param head
     * @return
     *  1 2 3 4 5
     *  一直递归下去 head.next=null 返回  head=5
     *  node=5 node 5 4 3 2 1 这个后面的递归完成
     *  head.next.next = head 让自己下一个的指针指回自己
     */
    private static ListNode revertListR(ListNode head) {
        if(head == null || head.next == null ) return head;
        ListNode node = revertList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

}
