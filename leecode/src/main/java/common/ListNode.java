package common;

public class ListNode {
    int val;
    public ListNode next;

    public ListNode() {
    }

    public int val() {
        return this.val;
    }

    public void val(int val) {
        this.val = val;
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    //逆序
    public static ListNode of(int... arr) {
        ListNode temp = null;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            ListNode node = new ListNode(arr[i]);
            node.next = temp;
            temp = node;
        }
        return temp;
    }

    //顺序
    public static ListNode from(int... arr) {
        ListNode head = new ListNode();
        ListNode temp = head;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            ListNode node = new ListNode(arr[i]);
            temp.next = node;
            temp = temp.next;
        }
        return head.next;
    }


    public void show() {
        int i = 0;//for listnode has cycle
        ListNode temp = this;
        while (temp != null && i < 20) {
            System.out.print(temp.val + "\t");
            temp = temp.next;
            i++;
        }
        System.out.println();
    }

    public static void show(ListNode head) {
        head.show();
    }

    //just for test
    public static void main(String[] args) {
        int[] nums = {3, 4, 5};
        ListNode head = ListNode.of(nums);
        head.show();
    }


}