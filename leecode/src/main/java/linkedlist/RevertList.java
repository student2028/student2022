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
        revertList(head).show();


    }

    //头插法 反转链表
    private static ListNode revertList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode cur = pre.next;
        while( cur.next != null) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }

    /**
     * @param head
     * @return
     *  1 2 3 4 5
     *  一直递归下去 head.next=null 返回  head=5
     *  node=5 node 5 4 3 2 1 这个后面的递归完成
     *  head.next.next = head 让自己下一个的指针指回自己
     *  labuladong的说明 有一些好的点 就是整体考虑的思想
     *
     */
    private static ListNode revertListR(ListNode head) {
        //如果链表只有一个元素 或 链表为空 则直接返回
        if(head == null || head.next == null ) return head;
        //可以整体考虑 不进入递归 链表head = head + head.next 所以 我去反转head.next
        //反转后的last 就是要返回的节点 而我们要处理的就是把head.next 置为空 把 head.next.next = head
        //容易理解一些
        ListNode last = revertList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

}
