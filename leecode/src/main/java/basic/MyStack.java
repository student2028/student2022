package basic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 *
 * 实现 MyStack 类：
 *
 *     void push(int x) 将元素 x 压入栈顶。
 *     int pop() 移除并返回栈顶元素。
 *     int top() 返回栈顶元素。
 *     boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *
 *
 *
 * 注意：
 *
 *     你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 *     你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-stack-using-queues
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 队列先进先出
 * 栈是先进后出
 * 用队列来模拟栈
 * push : 1  2  3
 * pop : 1  2
 * 可以这样 一个用来存数据  一个用来暂存数据
 * 只剩下最后一个 然后输出即可
 *
 */
public class MyStack {

    Queue<Integer> in = new LinkedList<>();
    Queue<Integer> tmp = new LinkedList<>();

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());
        while(!stack.empty()) System.out.println(stack.pop());
    }

    public MyStack() {


    }

    public void push(int x) {
        in.offer(x);
    }

    public int pop() {
        while(in.size() > 1) tmp.offer(in.poll());
         int ans = in.poll();
         while(!tmp.isEmpty()) in.offer(tmp.poll());
         return ans;
    }

    public int top() {
        while(in.size() > 1) tmp.offer(in.poll());
        int ans = in.poll();
        while(!tmp.isEmpty()) in.offer(tmp.poll());
        in.offer(ans);
        return ans;
    }

    public boolean empty() {
        return in.isEmpty();
    }

}
