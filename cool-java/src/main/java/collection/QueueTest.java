package collection;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * 理解队列
 * 优先队列
 * 双端队列
 *
 *
 */
public class QueueTest {

    public static void main(String[] args) {

//        PriorityQueue<Integer> qu = new PriorityQueue<Integer>();
//        qu.add(2);
//        qu.add(1);
//
//        int a = qu.peek();
//        System.out.println(a);
//        int b = qu.poll();
//        System.out.println(b);

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(2);
        deque.offer(3);
        deque.offer(4);

        System.out.println(deque.peek());
        System.out.println(deque.getLast());

    }
}
