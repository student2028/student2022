package linkedlist;


import common.ListNode;

/**
 * leecode 141
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，
 * 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * 注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * <p>
 * 快慢指针方法
 * fast fast = fast.next.next
 * slow slow = slow.next
 * 如果 slow = null && fast == null 即他们都达到了尾部
 * 如果 fast 追上了 slow 则表示有环存在
 *
 *  注意 特殊情况的判断
 *
 *  发散思维： 如果找到环 请找到环入口接点的值 环的长度？
 *  从例子上理解：
 *     1 , 2, 3, 4, -> 2
 *     环入口是 2 环找长度是 3
 *
 *
 */
public class CircleList {

    public static void main(String[] args) {

        ListNode list1 = ListNode.of(new int[]{1, 2, 3, 4, 5});
        System.out.println(hasCycle(list1));

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;

        System.out.println(hasCycle(n1));

    }

    private static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;

        ListNode fast = head;
        ListNode slow = head;
        while (fast!=null && fast.next != null) {
             fast = fast.next.next;
             slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }
}
