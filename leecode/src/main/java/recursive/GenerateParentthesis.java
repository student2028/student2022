package recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * 没搞出来 看官解
 */
public class GenerateParentthesis {

    public static void main(String[] args) {

        GenerateParentthesis generateParentthesis = new GenerateParentthesis();
        generateParentthesis.generateParenthesis(2).stream().forEach(System.out::println);

        generateParentthesis.isValid("(())".toCharArray());
        generateParentthesis.isValid("()(())".toCharArray());
        generateParentthesis.isValid("(()(())".toCharArray());


    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    /**
     * 如果左括号数量小于 n，我们可以放一个左括号。
     * 如果右括号数量小于左括号的数量，我们可以放一个右括号。
     *
     * @param ans
     * @param cur
     * @param open
     * @param close
     * @param max
     */
    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append("(");
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(")");
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }

    }

    //判断序列是否是有效的
    //遇到 (加1 否则就减一 最后一定是0 才是正确的
    public boolean isValid(char[] chars) {
        int balance = 0;
        for (char c : chars) {
            if(c == '(') balance ++;
            else balance --;
        }
        System.out.println(balance == 0 );
        return balance == 0 ;
    }

}
