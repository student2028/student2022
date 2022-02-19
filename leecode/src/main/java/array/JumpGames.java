package array;

/**
 * leecode 中等题 不过真正理解了 就是超级简单的题
 * <p>
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * 一开始你想的时候，会想，下标是0的值是2，我是走一步呢，还是走两步呢? 陷入这样的想法 代码就不知道怎么写了
 * 看到一个精妙的评论 想象成小人往前走 ，走到头就是成功了
 * 只要有能量，就可以一直往前走，走一步 检查一下所剩下的值与当前值谁大，就取谁即可
 * 代码一下就出来了 而且非常简单
 */
public class JumpGames {

    public static void main(String[] args) {

        JumpGames jumpGames = new JumpGames();
        System.out.println(jumpGames.jumpGames(2, 3, 1, 1, 4));
        System.out.println(jumpGames.jumpGames(3, 2, 1, 0, 4));

    }

    public boolean jumpGames(int... nums) {
        if (nums.length == 0) return true;
        int n = nums.length;
        int cur = nums[0];
        int i = 1;
        while (i < n && cur != 0) {
            cur--;
            cur = Math.max(cur, nums[i]);
            i++;
        }
        return i == n;
    }
}
