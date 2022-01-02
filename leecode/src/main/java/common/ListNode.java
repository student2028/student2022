package common;

public class ListNode {
    int val;
    public ListNode next;

    public ListNode() {
    }

    public int val(){
        return this.val;
    }


   public  ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode of(int[] arr) {
        ListNode temp = null;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            ListNode node  = new ListNode(arr[i]);
            node.next = temp;
            temp = node;
        }
        return temp;
    }

    public void show() {
        ListNode temp = this;
        while (temp != null) {
            System.out.print(temp.val + "\t");
            temp = temp.next;
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