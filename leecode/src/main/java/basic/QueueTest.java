package basic;

import common.Utils;

import java.util.*;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 */
public class QueueTest {
    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 1;
        QueueTest test = new QueueTest();
        int[] keys = test.topKFrequent(nums, k);
        Utils.printArray(keys);
    }

    //使用 hashMap 来获取元素的频率
    //使用一个priorityQueue  来存储 topk的
    //使用默认的小顶堆 方便获取最小的元素 并替换掉
    public int[] topKFrequent(int[] nums, int k) {
        //获取元素的frequency
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<int[]> qu = new PriorityQueue<>((m, n) -> m[1] - n[1]);//此处这个lambda 表达式还不能少
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : set) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (qu.size() == k) { //如果达到k个 则把频率小的去掉啊
                if (qu.peek()[1] < count) {
                    qu.poll();
                    qu.offer(new int[]{num, count});
                }
            } else qu.offer(new int[]{num, count}); //如果不到k个则加入到队列中

        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = qu.poll()[0];
        }

        return ans;
    }

    //    给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    //    返回滑动窗口中的最大值。
    //    输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
    //    输出：[3,3,5,5,6,7]

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            // 根据题意，i为nums下标，是要在[i - k + 1, i] 中选到最大值，只需要保证两点
            // 1.队列头结点需要在[i - k + 1, i]范围内，不符合则要弹出
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            // 2.既然是单调，就要保证每次放进去的数字要比末尾的都大，否则也弹出
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offer(i);

            // 因为单调，当i增长到符合第一个k范围的时候，每滑动一步都将队列头节点放入结果就行了
            if (i >= k - 1) {
                res[idx++] = nums[deque.peek()];
            }
        }
        return res;
    }
}
