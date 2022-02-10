package recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 二刷 ，这个题去年做过一遍，还是抄的官解，今年自己摸索出来了
 *
 */
public class Permute {

    private List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        int size = nums.length;
        List<Integer> list = new ArrayList<>();
        backtrace(list, size, nums);
        return lists;
    }

    public void backtrace(List<Integer> list, int size, int[] nums) {
        if (size == list.size()) {
            lists.add(new ArrayList<>(list));
            return;
        }
        //如果数量没达到，则继续添加
        for (int i = 0; i < size; i++) {
            if (!list.contains(nums[i])) { //忽略重复的数据
                list.add(nums[i]);
                backtrace(list, size, nums);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Permute permute = new Permute();
        int[] nums = new int[]{1, 2, 3};
        permute.permute(nums);
        System.out.println(permute.lists);

    }


}
