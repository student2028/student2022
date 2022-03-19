package sort;

import common.Utils;

import static common.Utils.printArray;

/**
 * 归并排序
 * 分治思想  递归算法
 * 原理：  1 3 4 2 0
 * 先划分 再归并
 * 划分 一个数组两分 一直划分下去 一直到每一个数组只有一个元素 这样即是有序的
 * 归并的逻辑 即合并两个有序的数组
 * <p>
 * 归并排序都需要临时数组来存储数据
 */
public class MergeSort {

    public static void main(String[] args) {

        int[] arr = Utils.generateIntArray(10);
        printArray(arr);
        mergeSort1(arr, new int[arr.length], 0, arr.length - 1);
        //  mergeSort(arr, 0, arr.length - 1);
        printArray(arr);

    }

    private static void mergeSort1(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return;
        int m = left + (right - left) / 2;
        mergeSort1(arr, temp, left, m);
        mergeSort1(arr, temp, m + 1, right);

        int n1 = m, n2 = right, k = right;
        while (n1 >= left || n2 >= m + 1) {
            if (n1 >= left && n2 >= m + 1) temp[k--] = arr[n1] > arr[n2] ? arr[n1--] : arr[n2--];
            else if (n1 >= left) temp[k--] = arr[n1--];
            else temp[k--] = arr[n2--];
        }
        for(k = left; k<= right; k++) arr[k] = temp[k];
    }


    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int m = left + (right - left) / 2;
        mergeSort(arr, left, m);
        mergeSort(arr, m + 1, right);
        merge(arr, left, m, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int l1 = mid - left + 1;
        int l2 = right - mid;
        int[] l = new int[l1];
        int[] r = new int[l2];
        //copy data to l r from arr
        for (int i = 0; i < l1; i++) {
            l[i] = arr[i + left];
        }
        for (int i = 0; i < l2; i++) {
            r[i] = arr[mid + 1 + i];
        }
        int i = 0;
        int j = 0;
        int k = left;

        while (i < l1 && j < l2) {
            arr[k++] = l[i] < r[j] ? l[i++] : r[j++];
        }
        while (i < l1) arr[k++] = l[i++];
        while (j < l2) arr[k++] = r[j++];

    }

}
