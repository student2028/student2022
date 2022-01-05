package linkedlist;

import common.ListNode;

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

        ListNode l1 = ListNode.from(1,4,5);
        ListNode l2 = ListNode.from(1,3,4);
        ListNode l3 = ListNode.from(2,6);

        ListNode[] lists = new ListNode[]{l1,l2,l3};
        mergeKLists(lists).show();


    }

    private static ListNode  mergeKLists(ListNode[] lists) {
        ListNode dummy = null;
        for (int i = 0; i < lists.length; i++) {
            dummy = mergetList(dummy, lists[i]);
        }

        return dummy;
    }

    private static ListNode mergetList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if(l1.val() < l2.val()) {
                curr.next = l1;
                l1 = l1.next;
            } else
            {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

}
