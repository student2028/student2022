package linkedlist;


import common.ListNode;

/**
 * leecode 160. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * <p>
 * 图示两个链表在节点 c1 开始相交：
 * <p>
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * <p>
 * 进阶：你能否设计一个时间复杂度 O(m + n) 、仅用 O(1) 内存的解决方案？
 * <p>
 * 理解相交， 链表的next 只能指向一个节点 所以不会出现 X 形 只会出现Y 字形的相交
 * 1.直观思路 使用hashset 存储扫描第一个节点的数据 ，再遍历第二个链表的时候判断 找出即返回
 * 2.官解第二题比较有技巧性不好理解 代码非常简单 即 双指针方法 同时遍历 两个链表
 * 因为存在可能两个链一不同长度 所以 当一个链表跑完了 则指向另一个的开头 再开始走
 * 3. 先遍历两个长度 让长的先走差的距离  然后再分别比较 相同则返回  没有就是null
 * 空间时间上第二种最优 最容易想到的就是第一种和第三种
 * 好好理解第二种
 */

public class IntersectionList {
    public static void main(String[] args) {

        ListNode la = ListNode.from(4, 1, 8, 4, 5);
        ListNode lb = ListNode.from(5, 6, 1, 8, 4, 5);

        ListNode node = intersectListNode(la, lb);
        if(node == null) {
            System.out.println("null");
        }

        ListNode lc = ListNode.from(4, 1, 8, 4, 5);
        ListNode ld = lc.next.next;
        ListNode dummy = new ListNode(100, ld);

        intersectListNode(lc, dummy).show();



    }

    private static ListNode intersectListNode(ListNode l1, ListNode l2) {

        if (l1 == null || l2 == null) return null;
        ListNode pA = l1;
        ListNode pB = l2;
        while (pA != pB) {
            pA = pA == null ? l2 : pA.next;
            pB = pB == null ? l1 : pB.next;

        }
        return pA;
    }
}
