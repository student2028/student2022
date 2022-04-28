package collection;

import java.util.HashMap;
import java.util.Map;

public class LRU {

    class Node {
        int key, val;
        Node pre, next;
          public Node () {};
          public Node(int key, int val) { this.key = key; this.val = val; }
    }

    Map<Integer, Node> cache ;
    Node head, tail ;
    int capacity;
    public LRU(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node == null ) return -1;
        move2Head(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if(node == null) {
            node = new Node(key,value);
            cache.put(key,node);
            add2Head(node);
        } else {
            node.val = value;
            move2Head(node);
        }
        remove();
    }

    //add2head move2head removetail
    void add2Head(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    void move2Head(Node node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
        add2Head(node);
    }

    void remove() {
        if(cache.size() > capacity) {
            Node node = tail.pre;
            cache.remove(node.key);
            node.next.pre = node.pre;
            node.pre.next = node.next;
        }
    }

    public static void main(String[] args) {
        LRU test = new LRU(2);
        test.put(1,1);
        test.put(2,2);
        System.out.println(test.get(1));
        test.put(3,3);
        System.out.println(test.get(2));
        test.put(4,4);
        System.out.println(test.get(1));
        System.out.println(test.get(3));
        System.out.println(test.get(4));

    }
}
