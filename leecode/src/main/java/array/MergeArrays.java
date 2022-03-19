package array;


import static common.Utils.printArray;

/**
 * 合并两个有序的数组
 * int[] a = new int[] {1,3,5};
 * int[] b = new int[] {2,4,6};
 */
public class MergeArrays {

    public static void main(String[] args) {
        MergeArrays test = new MergeArrays();
        int[] a = new int[]{1, 3, 5};
        int[] b = new int[]{2, 4, 6};
        printArray(test.mergeArrays(a, b));

    }

    public int[] mergeArrays(int[] num1, int[] num2) {
        int n1 = num1.length - 1;
        int n2 = num2.length - 1;
        int[] res = new int[n1 + n2 + 2];

        int k = res.length - 1;
        while (n1 >= 0 || n2 >= 0) {
            if (n1 >= 0 && n2 >= 0) {
                res[k--] = num1[n1] > num2[n2] ? num1[n1--] : num2[n2--];
            } else if (n1 >= 0) {
                res[k--] = num1[n1--];
            } else {
                res[k--] = num2[n2--];
            }
        }
        return res;
    }

}
