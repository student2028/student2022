package linkedlist;

import common.ListNode;

import java.util.PriorityQueue;

/**
 * leecode 23. 合并K个升序链表
 * hot100
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * <p>
 * 输入：lists = []
 * 输出：[]
 * <p>
 * 此题思路：
 * 1. 顺序合并 写出合并两个链表的 然后顺序两两合并 第一个是空链表
 * 2. 同上 只是改成递归实现 分治算法
 * 3. 使用数据结构 priority queue 把所有链表的数据过一遍存入pq 然后现取出即可 使用了pq的特性
 * 4. 同上 使用大的基数数组  过一遍把所有数据存到数组中，然后再读出构建链表即可 技巧 为了防止链表中值特别大， 可以让它的值减去一个值再存
 */

public class MergeKLists {

    public static void main(String[] args) {

        ListNode l1 = ListNode.from(1, 4, 5);
        ListNode l2 = ListNode.from(1, 3, 4);
        ListNode l3 = ListNode.from(2, 6);
        ListNode[] lists = new ListNode[]{l1, l2, l3};
        MergeKLists mergeKLists = new MergeKLists();
        ListNode list = mergeKLists.mergeKLists(lists);
        list.show();
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode dummy = new ListNode(0);
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                lists.length, (a, b) -> (a.val() - b.val())
        );

        for(ListNode head : lists) if(head != null) pq.add(head);

        ListNode list = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            list.next = node;
            if (node.next != null) pq.add(node.next);
            list = list.next;
        }

        return dummy.next;
    }


}
