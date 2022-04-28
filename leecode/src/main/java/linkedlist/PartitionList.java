package linkedlist;

import common.ListNode;

/**
 * leecode 86 middle
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * <p>
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * <p>
 * 能写出来重要，更重要的是如何知道自己写出来了？ 如何指导写不出来的人能写出来呢？
 * 这个题 需要提前熟悉 链表的插入和删除操作
 * 先练习一下
 * 单链表的删除需要一个技巧  需要一个dummyNode 指向head 这样不需要对head进行特殊处理
 * 因为删除的需要pre节点 pre.next = pre.next.next 即可删除pre.next 但是head没有pre
 * 所以需要dummyNode = new ListNode(-1,head)
 * <p>
 * 插入节点 t
 * t.next = head.next;
 * head.next = t;
 * <p>
 * [1,4,3,0,2,5,2] 3
 * [1,0,2,2,4,3,5]
 *
 * 看官解，学思路
 * 直观来说我们只需维护两个链表 small\textit{small}small 和 large\textit{large}large 即可，
 * small\textit{small}small 链表按顺序存储所有小于 xxx 的节点，large\textit{large}large 链表按顺序存储所有大于等于 xxx 的节点。
 * 遍历完原链表后，我们只要将 small\textit{small}small 链表尾节点指向 large\textit{large}large 链表的头节点即能完成对链表的分隔。
 *
 */
public class PartitionList {

    public static void main(String[] args) {

        ListNode head = ListNode.from(4,2,1,3);
        head.show();

     //   partitionList(head, null).show();

        sortList(head).show();

    }

     //left的左侧都是满足val<x的数据
    private static  ListNode partitionList(ListNode head, ListNode end) {
        if(head == end || head.next == end) return head;
        int x = head.val();
        ListNode dummy = new ListNode(0,head);
        ListNode pre = dummy , cur = head, left = dummy;
        while(cur != end) {
            if(cur.val() < x) {
                //cur删除 然后移到left后面即可
                pre.next = cur.next;
                cur.next = left.next;
                left.next = cur;
                left = left.next;
            }
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }

   private static ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode small = new ListNode(), large = new ListNode();
        ListNode psmall = small, plarge = large, node = head.next;
        while(node != null) {
            if(node.val() < head.val()) {
                psmall .next = node;
                psmall = psmall.next;
            }else {
                plarge.next = node;
                plarge = plarge.next;
            }
            node = node.next;
        }

        psmall.next = head;
        head.next = null;
        plarge.next = null;

        small = sortList(small.next);
        large = sortList(large.next);

        head.next = large;
        return small;
   }





}
