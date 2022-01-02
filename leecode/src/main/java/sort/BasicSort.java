package sort;

import java.util.Arrays;

/**
 * 基本的排序有
 * 冒泡排序
 * 原理： 比较排序 双循环 外层循环表示待排序元素的位置 n 个元素只需要比较n-1次 即可
 * 内循环 待排序元素 与后一位元素的的比较与交换 如果当前元素大于后面的元素 则交换
 * 注意后面的元素已经排过序了 排序的范围是 l-i
 * 内循环完成后 元素最大的值沉底冒泡
 * 插入排序
 * 原理： 假定前面的数组都是有序的 当前元素扫描找到合适的位置插入
 * 如何找到合适的位置  是从前往后找 ??? 这样效率太低
 * 还是从后往前找呢？ 从后往前找，边找边交换
 * 所以可以看到插入排序有优化的空间
 * 而且前面的子数组是有序的，还可以全用折半查找的思路来找到插入的位置
 * 这都是对插入排序的优化
 * <p>
 * 数组的插入 会让后面的元素后移
 * 选择排序
 * 外层循环表示位置  当前元素所在位置
 * 最小的元素在0 次小的元素在1
 * 所以每一次可以找出剩下元素中最小的元素即可
 * <p>
 * 高级
 * 快排  归并 堆排序
 */
public class BasicSort {

    public static void main(String[] args) {

        int[] arr = {2, 1, 4, 3, 5, 7, 6, 9, 8};

        printArray(arr);

        // bubbleSort(arr);
        // insertSort(arr);
        // insertSort2(arr);
        // Arrays.sort(arr);

        selectSort(arr);
        printArray(arr);


    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    private static void bubbleSort(int[] arr) {

        int l = arr.length;
        for (int i = 1; i < l; i++) {
            for (int j = 0; j < l - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

    }

    private static void insertSort(int[] arr) {
        int l = arr.length;
        for (int i = 1; i < l; i++) {
            //当前假定从前往后找位置 找到第一个比自己大的元素时停下来
            int j = 0;
            while (arr[i] > arr[j] && i > j) j++;
            //j is the pos
            int temp = arr[i];
            //1 3 4 2 6,5,7  2找到了第二个位置  1 所以3 4 需要移动
            // i = 3 j = 1
            for (int k = i; k > j; k--) {
                arr[k] = arr[k - 1];
            }
            arr[j] = temp;
        }
    }

    private static void insertSort2(int[] arr) {
        int l = arr.length;
        for (int i = 1; i < l; i++) {
            //查找的时候 其实可以直接交换 这样省了一个循环
            // 1 3 4 2 5   // 2查找自己的位置
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    private static void selectSort(int[] arr) {
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            int index = i;
            for (int j = i; j < l; j++) {
                if (arr[j] < arr[index]) index = j;
            }
            if (i != index) {
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }

        }
    }

}
