package array;



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

        quickSelectKthNum(nums, 0, nums.length - 1, 2);

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

    //这种写法的partition 在leecode中通不过 请注意
    private static int partition2(int[] arr, int left, int right) {
        int i = left;
        int j = right;

        while (i < j) {
            while (arr[i] < arr[left]) i++;
            while (arr[j] > arr[left]) j--;
            swap(arr, i, j);
        }
        return i;
    }


    private static int quickSelectKthNum(int[] nums, int left, int right, int k) {

        int kth = nums.length - k;
        while (left <= right) {
            int pivot = partition2(nums, left, right);
            if (pivot == kth) {
                System.out.println(nums[kth]);
                return nums[kth];
            }
            if (pivot > kth) right = pivot - 1;
            else left = pivot + 1;
        }

        return -1;
    }

    static void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

}
