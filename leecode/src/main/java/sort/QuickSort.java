package sort;

import common.Utils;

import static common.Utils.swap;


/*******************************************************************************
 *  QuickSort 原理
 *  分治法 递归算法
 *  划分 怎么划分？ 找到一个参考点，譬如left[0] 元素 作为参考点 （此处可以优化）
 *  二分法 根据参考点 把当前数组分成三部分 a<pivot  pivot a>pivot
 *  划分 如何实现？
 *  划分就是找到参考点 应该所处的位置
 *  双指针技巧  一个指向左端  一个指向右端
 *  从左向右扫 遇到大于参考点的停下 从右往右扫 遇到比参考点小的停下
 *  交换两边的数据  一直到双指针碰到一起 这种思路简单 边界问题不容易出现
 *  另一种双指针的思路是  一个right 一直向前走扫描数据
 *  另一个left 只有当满足条件的时候才 加一  left表示我左边的数据都小于pivot
 *  这种方法要注意右边的边界
 * 使用相同的方法 对左右两侧的子数组进一步进行划分 一直划分到数组成为单个元素
 * 这样 就完成了排序
 *
 * 示例 4 1 3 7 6 5
 *  4作为参考点   划分为 3, 1 ,     4    7 6 5 or 1 ,3 ,4 ,7 ,6 ,5
 *
 *******************************************************************************/
public class QuickSort {


    public static void main(String[] args) {

        int[] arr = Utils.generateIntArray(10);
        Utils.printArray(arr);
//        Arrays.sort(arr);
//        Utils.printArry(arr);

        quickSort2(arr, 0, arr.length - 1);
        Utils.printArray(arr);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = left;
        int i = left;
        int j = left;
        while (j <= right) {
            if (arr[j] < arr[pivot]) swap(arr, j, ++i);
            j++;
        }
        swap(arr, left, i);

        return i;
    }


    private static int partition2(int[] arr, int left, int right) {
        int pivot = left;
        int i = left;
        int j = right;

        while (i < j) {
            while (arr[i] < arr[pivot]) i++;
            while (arr[j] > arr[pivot]) j--;
            swap(arr, i, j);
        }
        return i;
    }


    private static void quickSort2(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivot = partition(arr, left, right);
        quickSort2(arr, left, pivot - 1);
        quickSort2(arr, pivot + 1, right);
    }

}
