package linkedlist;

import common.ListNode;

/**
 * leecode19. 删除链表的倒数第 N 个结点
 * <p>
 * // 链表中结点的数目为 sz
 * // 1 <= sz <= 30
 * // 0 <= Node.val <= 100
 * // 1 <= n <= sz
 * // 进阶：你能尝试使用一趟扫描实现吗？
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * <p>
 * 输入：head = [1], n = 1
 * 输出：[]
 * <p>
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * <p>
 * 最直观的思路就是
 * 1.正向删除
 * 2.求出链表的长度 然后算出正向删除的第n个 n = len - n + 1
 * <p>
 * 这样在求链表长度的时候已经扫描了一遍数据
 * <p>
 * 官解提出的另外两种方法：
 * 1. 使用 栈 扫描一遍入栈 这样出栈的时候 就是倒着算的 非常方便 也算符合扫描一次的条件
 * 2. 使用快慢指针 这里的技巧之处是 让fast 先跑n个节点 然后再一起跑一次一步 这样当fast到尾部的时候
 * slow 就跑到了倒数第N个节点
 * <p>
 * 在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），它的 next\textit{next}next 指针指向链表的头节点。
 * 这样一来，我们就不需要对头节点进行特殊的判断了。
 * 例如，在本题中，如果我们要删除节点 yyy，我们需要知道节点 yyy 的前驱节点 xxx，并将 xxx 的指针指向 yyy 的后继节点。
 * 但由于头节点不存在前驱节点，因此我们需要在删除头节点时进行特殊判断。但如果我们添加了哑节点，
 * 那么头节点的前驱节点就是哑节点本身，此时我们就只需要考虑通用的情况即可。
 *
 */
public class RemoveNthFromEnd {

    public static void main(String[] args) {

        ListNode head = ListNode.of(new int[]{5, 4, 3, 2, 1});
        head.show();

        removeNth(head, 1).show();
        //  removeNthFromEnd(head, 1).show();
    }

    /**
     * 正向删除第N个元素
     *
     * @param head
     * @param n
     * @return
     */
    private static ListNode removeNth(ListNode head, int n) {

        ListNode dummy = new ListNode(-1, head);
        ListNode temp = dummy;
        for (int j = 1; j < n; j++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;

        return dummy.next;
    }

    /**
     * 1 2 3 4 5 -> 3
     */
    private static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(-1, head);
        ListNode fast = head;
        ListNode slow = dummy;
        //fast 先走n个节点
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        //fast == null 表示到达链表尾部 此时slow所在节点正好是倒数第N个节点
        slow.next = slow.next.next;
        return dummy.next;
    }

}
