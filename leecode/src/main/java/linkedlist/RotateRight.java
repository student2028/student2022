package linkedlist;

import common.ListNode;

/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 示例 1：
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 * 示例 2：
 *
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 * 提示：
 *
 *     链表中节点的数目在范围 [0, 500] 内
 *     -100 <= Node.val <= 100
 *     0 <= k <= 2 * 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 */
public class RotateRight {
    public static void main(String[] args) {

    }

    public ListNode rotateRight(ListNode head, int k) {
        //链表的长度求出来 移动的k值可能远大于长度 所以我们可以取模 超过了长度反复做就没意义了 浪费时间空间
        //向右旋转k个 其实就是先把链表分两段，然后把后面的拼接到前面的就可以
        //第一段长度多少 len总长度 5 k=2 则前一段为5-2=3 后一段为k个长度的
        if(head == null || head.next == null || k==0) return head;
        int len = getLen(head);
        if(k%len==0) return head;
        int j = len - k%len; //取得真正的k值
        //拆分链表
        ListNode tmp = head;
        int i = 1;
        while(i<j){
            i++;
            tmp = tmp.next;
        }
        ListNode head2 = tmp.next;
        tmp.next = null;
        //找到head2的最后一个节点的前置节点 然后拼接链表即可
        tmp = head2;
        while(tmp.next != null) tmp = tmp.next;
        tmp.next = head;
        return head2;
    }


    public int getLen(ListNode head) {
        if(head == null) return 0;
        ListNode tmp = head;
        int len = 0;
        while(tmp!=null){
            len++;
            tmp = tmp.next;
        }
        return len;
    }

}
