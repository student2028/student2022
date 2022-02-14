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
 *
 *
 */
public class Combine {

    public static void main(String[] args) {

        Combine combine = new Combine();
        combine.combine(4,2).stream().forEach(System.out::println );

    }

    public List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 1);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    private void dfs(int k, int n, int start){
        //终止条件
        if (path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n ; i++){
            path.add(i);
            dfs(k, n, i + 1);
            path.remove(path.size() -1 );
        }
    }

}
