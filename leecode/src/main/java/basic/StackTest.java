package basic;

import java.util.Stack;

/**
 * 几个使用栈的练习题
 * <p>
 * leecode 1047
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * <p>
 * 输入："abbaca"
 * 输出："ca"
 *
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 *
 */
public class StackTest {
    public static void main(String[] args) {

        StackTest test = new StackTest();
        String str = test.removeDuplicates("abbaca");
        System.out.println(str);

        String[] tokens = new String[]{"4","13","5","/","+"};
        System.out.println(test.evalRPN(tokens));

    }

    public String removeDuplicates(String s) {
        //string to stack
        Stack<Character> stack = new Stack<>();
        int N = s.length();
        for (int i = 0; i < N; i++) {
            if (stack.isEmpty() || stack.peek() != s.charAt(i)) stack.push(s.charAt(i));
            else { //不为空 并且相同 则抵消出栈
                stack.pop();
            }
        }
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) ans.append(stack.pop());
        return ans.reverse().toString();
    }


    //此题在leecode上提交的时候 注意 -/ 运算是有先后顺序的 还有leecode jdk不支持 =="+"的判断 千万注意啊
    public int evalRPN(String[] tokens) {
            //逆波兰式表达式求值
        Stack<Integer> st = new Stack<>();
        int N = tokens.length;
        for (int i = 0; i < N; i++) {
            if( tokens[i].matches("-?\\d+")) st.push(Integer.parseInt(tokens[i]));
            else {
                //出栈两个元素 进行运算并入栈
                int a = st.pop();
                int b =  st.pop();
                if(tokens[i].equals( "+")) st.push(a+b);
                else if(tokens[i].equals( "-")) st.push(b-a);
                else if(tokens[i].equals("*")) st.push(a*b);
                else if(tokens[i].equals( "/")) st.push(b/a);
            }
        }

        return st.pop();
    }


}
