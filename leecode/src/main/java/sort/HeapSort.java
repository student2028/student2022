package sort;

import common.Utils;

import static common.Utils.printArry;
import static common.Utils.swap;

/**
 * 堆排序
 * 满二叉树 是每一层的元素都是满的（除最后一层外）
 * 堆就是一种具有排序性质的满二叉树 如果根部最大则是大顶堆  如果根部最小则是小顶堆
 * 类似于选择排序 找到最小的元素 然后重构堆  再选出最小元素
 * 因为满二叉树非常易于使用数组来表示，而且节省空间
 * 以0为开始的数组 节点i的子节点分别是left 2*i+1 right:2*i + 2
 * 大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
 * 小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
 * 堆排序流程
 * 建大顶堆  把顶与数组末尾元素交换 然后 剩余部分再建堆  再循环处理
 * 建大顶堆使用了sink方法 建小顶堆 取其中值小的节点进行比较 改下比较方向 即可
 * 都是使用sink方法
 * 堆构造过程
 * <p>
 * 堆数组中, 索引值一半以后都是叶子节点, 前面的都是非叶子结点
 * 从索引值一半的位置倒着往前下沉的原因是
 * 倒着下沉可以保证每一次下沉都保证堆的有序性?
 * 如果你从前面开始下沉的话, 有可能你下着下着前面的结点就不具备堆的有序性了
 * 堆排序的时间复杂度Ologn 使用1的空间，非常高效，但为什么在实践中mergesort和quicksort的使用场景比堆排序多呢？
 * 实践中多数情况下快排比heapsort快，而且划分比维护堆快。
 * 在数据量非常大的情况下，可以使用mergesort,可以用于内存外排序
 * mergesort在大数据组件中使用比较多
 */
public class HeapSort {
    public static void main(String[] args) {

        int[] arr = Utils.generateIntArray(10);

        printArry(arr);
        heapSort2(arr);
        printArry(arr);

    }

    private static void heapSort(int[] arr) {
        int N = arr.length;
        for (int i = N / 2; i >= 0; i--) {
            sink(arr, i, N);
        }
        printArry(arr);
        for (int i = N - 1; i > 0; i--) {
            swap(arr, 0, i);
            sink(arr, 0, i);
        }
    }

    /**
     * @param arr
     * @param k   第k个元素
     * @param N   数组arr的长度
     *            sink 的目标就是把 node=k的元素 下沉下去
     *            它的子节点分别是2k+1 and 2k+2 和他们的值进行比较
     *            目标是建大顶堆 要让大的元素交换到上面去
     *            如果子元素大于当前元素则交换 否则退出
     *            交换即可 一直往下走 一直到结束
     */
    private static void sink(int[] arr, int k, int N) {
        while (2 * k + 1 < N) {
            //left node
            int i = 2 * k + 1;
            if (i + 1 < N && arr[i] < arr[i + 1]) i++;
            if (arr[i] < arr[k]) break; // 如果父结点 > 子节点中的大值  则退出 构建大顶堆的思路
            swap(arr, i, k);
            k = i;
        }
    }

    //构建小顶堆
    private static void sink2(int[] arr, int k, int N) {
        while (2 * k + 1 < N) {
            int i = 2 * k + 1;
            if (i + 1 < N && arr[i] > arr[i + 1]) i++;
            if (arr[i] > arr[k]) break;
            swap(arr, i, k);
            k = i;
        }
    }


    //构建小顶堆 进行从大到小的排序  小的元素交换到后面去
    private static void heapSort2(int[] arr) {
        int N = arr.length;
        for (int i = N / 2; i >= 0; i--) {
            sink2(arr, i, N);
        }
        for (int i = N - 1; i > 0; i--) {
            swap(arr, 0, i);
            sink2(arr, 0, i);
        }
    }

}
