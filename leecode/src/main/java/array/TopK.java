package array;


import java.util.PriorityQueue;

import static common.Utils.printArray;
import static common.Utils.swap;

/**
 * 找出数组中topK大的数
 * 1. 使用冒泡排序
 * 2. 使用堆排序
 * 3. 使用快选法  （面试推荐回答）
 * topK大的 使用快选法 可以转化成求数组中
 * 第Nth大的数据，就是划分后位置位于 arr.length - K 的数据
 * 划分完成后 它后面的数据就是topK的数据啦
 */
public class TopK {

    public static void main(String[] args) {

        int[] nums = {3, 2, 1, 5, 6, 4};

        printArray(nums);
        System.out.println("find the 第2大的元素：");

//      System.out.println(quickSelectKthNum(nums, 2));
//      System.out.println(findK(nums, 0, nums.length -1, nums.length - 2));
        System.out.println(findK2(nums, 2));

    }

    //first step 写出partition
    private static int partition(int[] nums, int left, int right) {
        int i = left;
        int j = left;
        while (j <= right) {
            if (nums[j] < nums[left]) swap(nums, j, ++i);
            j++;
        }
        swap(nums, i, left);
        return i;
    }

    private static int quickSelectKthNum(int[] nums, int k) {
        int N = nums.length;
        int target = N - k;
        int left = 0;
        int right = N - 1;
        int pivot = partition(nums, left, right);
        while (left <= right) {
            if (pivot == target) return nums[target];
            if (pivot > target) right = pivot - 1;
            else left = pivot + 1;
            pivot = partition(nums, left, right);
        }
        return -1;
    }

    private static int findK(int[] nums, int left, int right, int k){
        if(left >= right) return -1;
        int pivot = partition(nums, left, right);
        if(pivot == k) return nums[k];
        else if(pivot < k) return findK(nums, pivot + 1, right, k);
        else return findK(nums, left, pivot - 1, k);
    }

    //使用优先队列 大顶堆 或 小顶堆
    private static int findK2(int[] nums, int k) {
        //如果是小顶堆  则第k个元素 就是第k大的
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
            if(pq.size() > k) pq.poll();
        }
        return pq.poll();
    }

}
