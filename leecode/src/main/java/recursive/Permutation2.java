package recursive;

import java.util.HashSet;
import java.util.Set;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * 示例:
 * <p>
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * 限制：
 * <p>
 * 1 <= s 的长度 <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permutation2 {

    public static void main(String[] args) {

        Permutation2 test = new Permutation2();
        for(String str : test.permutation("aab")) System.out.println(str);

    }

    Set<String> res = new HashSet<>();

    public String[] permutation(String s) {
        int n = s.length();
        boolean[] used = new boolean[n];
        backtrack(s.toCharArray(), used, "", n);
        return res.toArray(new String[0]);

    }

    public void backtrack(char[] chars, boolean[] used, String path, int size) {
        if (size == path.length()) {
            res.add(path);
            return;
        }

        for (int i = 0; i < size; i++) {
            if (!used[i]) {
                used[i] = true;
                backtrack(chars, used, path + chars[i], size);
                used[i] = false;
            }
        }

    }

}
