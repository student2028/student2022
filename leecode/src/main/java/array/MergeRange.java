package array;

import java.util.*;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 提示：
 * <p>
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 此题观察一下 思路还是比较容易的 主要是先排序  如果不使用库带的 自己再写一下 这个题就考察的多了
 */
public class MergeRange {

    public static void main(String[] args) {
        MergeRange mergeRange = new MergeRange();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] res = mergeRange.merge(intervals);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i][0] + "," + res[i][1]);
        }
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> res = new ArrayList<>();
        int size = intervals.length;
        res.add(intervals[0]);
        for (int i = 1; i < size; i++) {
            int[] tmp = res.get(res.size() - 1);
            if (tmp[1] >= intervals[i][0]) {//如果有交集即当前的start <= last[1]，则弹出来，修正后重新加入 小心不要丢了=号这种情况
                res.remove(res.size() - 1);//移除前一个
                res.add(new int[]{tmp[0], Math.max(tmp[1], intervals[i][1])});
            } else { //如果没有交集则直接加进来
                res.add(intervals[i]);
            }
        }

        return res.toArray(new int[res.size()][]);
    }

}
