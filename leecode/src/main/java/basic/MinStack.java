package basic;

import java.util.LinkedList;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 *     push(x) —— 将元素 x 推入栈中。
 *     pop() —— 删除栈顶的元素。
 *     top() —— 获取栈顶元素。
 *     getMin() —— 检索栈中的最小元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *  思路 ： 使用 一个辅助栈 每次在添加一个元素的时候，同步加入最小的元素即可？
 *  进阶 ： 如果不使用辅助栈？ 还有其他办法吗？
 *
 */
public class MinStack {

    int min = Integer.MAX_VALUE;
    LinkedList<Integer> list;

    public MinStack() {
        min = Integer.MAX_VALUE;
        list = new LinkedList<>();
    }

    public void push(int val) {
        list.add(val);
        list.addFirst(val <= min ? val : min);
        min = list.peek();
    }

    public void pop() {
        list.pollLast();
        list.poll();
        min = list.isEmpty() ? Integer.MAX_VALUE : list.peek();
    }

    public int top() {
        return list.peekLast();
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack test = new MinStack();
        test.push(10);
        test.push(3);
        test.push(-2);
        test.push(2);

        System.out.println(test.getMin());
        test.pop();
        System.out.println(test.getMin());
        test.pop();
        System.out.println(test.getMin());
        test.pop();
        System.out.println(test.getMin());

    }
}
