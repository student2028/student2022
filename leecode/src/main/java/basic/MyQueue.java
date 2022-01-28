package basic;

import java.util.Stack;

/**
 * MyQueue
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 *
 * 实现 MyQueue 类：
 *
 *     void push(int x) 将元素 x 推到队列的末尾
 *     int pop() 从队列的开头移除并返回元素
 *     int peek() 返回队列开头的元素
 *     boolean empty() 如果队列为空，返回 true ；否则，返回 false
 *
 * 说明：
 *
 *     你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 *     你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MyQueue {

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */


    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.peek());
        System.out.println(queue.pop());

    }

    /**
     * 队列 先进先出
     * 用两个栈来模拟出来
     * 一个栈用来存数据
     * 则出数据的时候 栈弹出的数据是反着的 所以要用另一个栈接着 然后弹到另一个栈里 另一个栈再弹出来
     * 例如
     * push 1 , 2, 3, 栈里面是 3/2/1
     * 出来的顺序就应该是1，2，3，
     * 但我们需要先将3/2/1的数据压到另一个栈中变成 1/2/3 这样1就可以先出来了
     *
     */

    Stack<Integer> in  = new Stack<>(); //用来存数
    Stack<Integer> out = new Stack<>(); //用来出数 是空的 是要从in出输入

    public MyQueue() {

    }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        //关键部分
        //先把in中的转移到out
        if(in.isEmpty()) return -1;
        while(!in.isEmpty()) out.push(in.pop());
        int ans = out.pop();
        //out中剩余的再放回in
        while(!out.isEmpty()) in.push(out.pop());
        return ans;
    }

    public int peek() {
        if(in.isEmpty()) return -1;
        while(!in.isEmpty()) out.push(in.pop());
        int ans = out.peek();
        //out中剩余的再放回in
        while(!out.isEmpty()) in.push(out.pop());
        return ans;
    }

    public boolean empty() {
        return in.isEmpty();
    }
}
