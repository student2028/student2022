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

        ListNode head = ListNode.from(1, 4, 3, 0, 2, 5, 2);
        head.show();

        //在1后面插入结点6
//        ListNode n6 = ListNode.from(6);
//        ListNode l = head;
//        while (l.next != null) {
//            if (l.val() == 1) {
//                n6.next = l.next;
//                l.next = n6;
//                l = l.next;
//            }
//            l = l.next;
//        }
//        head.show();
//
//        //删除节点5
//        l = new ListNode(-1, head);
//        while (l.next != null) {
//            if (l.next.val() == 5) {
//                l.next = l.next.next;
//            }
//            l = l.next;
//        }
//
//        head.show();

        partitionList(head, 3).show();

    }


    private static ListNode partitionList(ListNode head, int x) {
        if(head == null) return null;
        ListNode dummy = new ListNode(-1, head);
        ListNode first = dummy; // 指向待插入的位置
        ListNode second = head; //一直往前走
        while(second.val() < x) {
            second = second.next;
            first = first.next;
        }
        //上面这一段为什么是必须的？ 一去掉就会丢数据 下面这段代码可以调整
        while (second != null && second.next != null) {
            if (second.next.val() < x) {
                //插入小于x的值 插入前要先把second 删除
                //从当前位置删除 然后插入到first后面
                ListNode temp = second.next;
                second.next = second.next.next;
                //插入到待插入的位置
                temp.next = first.next;
                first.next = temp;
                first = first.next;
                continue;
            }
               second = second.next;
        }

        return dummy.next;
    }


}
