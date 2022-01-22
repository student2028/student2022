package recursive;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 * 示例 1：
 * 输入：n = 4, k = 2
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 */
public class Combine {

    public static void main(String[] args) {

        Combine combine = new Combine();
        combine.combine2(4,2).stream().forEach(System.out::println );

    }


    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        backtrack(res, path, n, k, 1);
        return res;
    }

    private void backtrack(List<List<Integer>> res, LinkedList<Integer> path, int n, int k, int startIndex) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n - (k-path.size()) + 1 ; i++) {
            path.add(i);
            backtrack(res, path, n, k, startIndex + 1);
            path.removeLast();
        }

    }

    public List<List<Integer>> combine2(int n, int k) {
        combineHelper(n, k, 1);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    private void combineHelper(int n, int k, int startIndex){
        //终止条件
        if (path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n ; i++){
            path.add(i);
            combineHelper(n, k, i + 1);
            path.remove(path.size() -1 );
        }
    }

}
