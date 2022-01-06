package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * leecode146
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * <p>
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 官解如下：
 * LRU 缓存机制可以通过哈希表辅以双向链表实现，我们用一个哈希表和一个双向链表维护所有在缓存中的键值对。
 * node int key ,int value ,node pre, node next
 * hashMap key int value: Node
 * hashmap 用来快速定位找到Node 用来快速查找
 * get: 通过hashmap找到node, 如果node=null 返回-1 否则 把这个节点移动链表头部，然后返回值
 * put: 先获取，如果存在，则更新，并移到头部 如果不存在，新建节点，移到头部，但如果超过容量，需要移掉尾部元素，并从缓存中去掉
 * 这个题不难，主要是要细心
 * <p>
 * ["LRUCache","put","put","put","put","get","get"]
 * [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
 */
public class LRUCache {

    public static void main(String[] args) {
        //just test it now
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1);
        lRUCache.put(1, 1);
        lRUCache.put(2, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.put(4, 1); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(2);    // 返回 4

    }

    class DNode {
        int key;
        int val;
        DNode pre;
        DNode next;

        public DNode() {
        }

        public DNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    int size = 0;  //当前大小
    int capacity; // 额定容量
    Map<Integer, DNode> cache = new HashMap<>();

    //伪头节点与尾节点
    DNode head = new DNode();
    DNode tail = new DNode();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DNode node = cache.get(key);
        if (node == null) {
            System.out.println(-1);
            return -1;
        } else {
            move2Head(node);
            System.out.println(node.val);
            return node.val;
        }
    }

    public void put(int key, int val) {
        DNode node = cache.get(key);
        if (null == node) {
            node = new DNode(key, val);
            cache.put(key, node);
            add2Head(node);
            size++;
        } else {
            node.val = val;
            cache.put(key, node);
            move2Head(node);
        }
         if (size > capacity) {
            cache.remove(removeEnd().key);
            size--;
        }
    }

    private void add2Head(DNode node ){
        node.next = head.next;
        node.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    private void move2Head(DNode node) {
        // 移动到头部 需要先从当前的位置移除 然后再插入到head后面即可
        //第一步如果不是第一次插入到链表中 需要先从链表中删除
        node.pre.next = node.next;
        node.next.pre = node.pre;
        //先二步插入到head后面
        node.next = head.next;
        node.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    private DNode removeEnd() {
        //删除尾部的节点 使用tail 如果tail前面就是head 则直接返回
        DNode node = tail.pre;
        node.pre.next = tail;
        tail.pre = node.pre;
        return node;
    }


}
