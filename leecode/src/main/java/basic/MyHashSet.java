package basic;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * 模拟题codetop上找到有人问啊 估计是校招？
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * <p>
 * 实现 MyHashSet 类：
 * <p>
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-hashset
 * 这个题可以看看宫水三叶的解法 非常有意思 而且非常全面
 * 一题三解 boolean数组 分桶数组  和 链表法
 * 由于题目给出了 0 <= key <= 10^6 数据范围，同时限定了 key 只能是 int。
 * 我们可以直接使用一个 boolean 数组记录某个 key 是否存在，key 直接对应 boolean 的下标。
 */
public class MyHashSet {

    static class Node {
        private int key;
        private Node next;

        private Node(int key) {
            this.key = key;
        }
    }

    private int getIndex(int key) {
        // 因为 nodes 的长度只有 10009，对应的十进制的 10011100011001（总长度为 32 位，其余高位都是 0）
        // 为了让 key 对应的 hash 高位也参与运算，这里对 hashCode 进行右移异或
        // 使得 hashCode 的高位随机性和低位随机性都能体现在低 16 位中
        int hash = Integer.hashCode(key);
        hash ^= (hash >>> 16);
        return hash % nodes.length;
    }

    public static void main(String[] args) {

        MyHashSet set = new MyHashSet();
        set.add(1);
        set.add(1);
        set.add(2);
        System.out.println(set.contains(2));
        System.out.println(set.contains(3));
        set.remove(2);
        System.out.println(set.contains(2));
    }

    /**
     * 链地址法 数组加链表
     * 我们简化下，按官解，直接对每一个数组初始化成一个LinkedList 处理方便
     */

    Node[] nodes = new Node[10009];

    public MyHashSet() {

    }

    public void add(int key) {
        //获取key所在桶中的位置  如果存在则返回 如果不存在则插入 如果存在 则仔细检查key是否相同
        int idx = getIndex(key);
        Node node = nodes[idx];
        Node tmp = node;
        //如果找到
        if (node != null) {
            Node pre = null;
            while (tmp != null) {
                //如果已经存过 则返回 否则一直往下走
                if (tmp.key == key) return;
                pre = tmp;
                tmp = tmp.next;
            }
            tmp = pre; //tmp存储一个最后节点的位置
        }
        //如果不存在 则插入 尾插法
        if (tmp != null) {
            tmp.next = new Node(key);
        } else {
            nodes[idx] = new Node(key);
        }

    }

    public void remove(int key) {
        //如果找到则移除 找不到则返回
        int idx = getIndex(key);
        Node loc = nodes[idx];
        if (loc != null) {
            Node prev = null;
            while (loc != null) {
                if (loc.key == key) {
                    if (prev != null) {
                        prev.next = loc.next;
                    } else {
                        nodes[idx] = loc.next;
                    }
                    return;
                }
                prev = loc;
                loc = loc.next;
            }
        }

    }

    public boolean contains(int key) {
        int idx = getIndex(key);
        Node node = nodes[idx];
        while (node != null) {
            //如果已经存过 则返回 否则一直往下走
            if (node.key == key) return true;
            node = node.next;
        }
        return false;
    }
}
