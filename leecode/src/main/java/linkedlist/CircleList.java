package linkedlist;


import common.ListNode;

/**
 * leecode 141 and leecode 142
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
 * fast = head;
 * slow = head;
 * while(fast!=null && fast.next!=null)
 * fast fast = fast.next.next
 * slow slow = slow.next
 *
 * 如果 slow = null && fast == null 即他们都达到了尾部
 * 如果 fast 追上了 slow 则表示有环存在
 *
 *  注意 特殊情况的判断
 *
 *  发散思维： 如果找到环 请找到环入口接点的值 环的长度？
 *  从例子上理解：
 *     1 , 2, 3, 4, -> 2
 *     环入口是 2 环找长度是 3
 *  为什么 当快慢指针相遇到的时候， 再有一个指针从head开始走 再和 慢指针相遇就是环的入口点？
 *  环的长度如何计算？
 *  设链表中环外部分的长度为 a指针进入环后，又走了 b 的距离 slow 与 fast 相遇。
 *  c 表示环中slow未走的距离
 *  此时，fast 指针已经走完了环的  n 圈，
 *  因此fast走过的总距离为  a+n(b+c)+b = a+(n+1)b+nc
 *  fast 指针走过的距离都为 slow 指针的 2  倍。
 *  a+(n+1)b+nc=2(a+b)⟹ a=c+(n−1)(b+c)
 *  b+c = 环长
 *  由此可以知道 a=c+(n-1)环长 假定n=1 则a=c
 *  由此 另一指针从head走到入口处 刚好等于 slow走到入口处 它们相遇
 *  当n>1的时候 另一指针走到入口处 ，则慢指针走了n圈 仍会在入口处相遇
 *  根据上面的分析  也可以方便求环长
 *
 *  142的另一解决方案是 使用一个hashset 来存储走过的节点 如果判断节点存在 则直接判断有环
 *  而且找到入口点 而且非常好判断长度 只是空间度略高而已
 *  面试官喜欢快慢指针的回答
 *
 *  142 使用非java语言 例如javascript来解决的时候更简单 非常简单 同上的思路，javascript
 *  支持添加自定义属性，我们对走过的ListNode加一个自定义属性即可，每次判断一下，如果有这个属性
 *  则表示已经走过了 非常非常简单
 *  java能否使用这种方案，能否利用对象头或其他地方可以存放自定义属性？
 *  java object header class word中能否有一个Bit可以用来判断？
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

        System.out.println(cycleEntryPoint(n1).val());


    }

    private static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;

        boolean hasCircle = false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast!=null && fast.next != null) {
             fast = fast.next.next;
             slow = slow.next;
            if (fast == slow) {
                 hasCircle = true;
                 break;
            }
        }

        return hasCircle;
    }

    private static ListNode cycleEntryPoint(ListNode head) {
        if(head == null || head.next == null) return null;

        boolean hasCircle = false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast!=null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasCircle = true;
                break;
            }
        }
        if(hasCircle) {
             ListNode temp = head;
             //相遇的时候 ，另一个节点从头出发 和slow 一起一次走一步 相遇的时候 就是环的入口节点? 看上面分析
             //环的长度 如何计算？
            int l = 0;
            ListNode node = slow;
            while(temp!=slow) {
                slow = slow.next;
                temp = temp.next;
                if(node == temp) {
                    System.out.println("circle length is :" + l);
                } else
                {
                    l++;
                }
            }
            while(slow != node) {
                slow = slow.next;
                l++;
            }
            System.out.println("circle length is :" + l);

            return temp;
        }

        return null;
    }
}
