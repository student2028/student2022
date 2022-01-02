package common;

import java.util.Random;

/**
 * 一些常用的公共方法 static
 */
public class Utils {


    public static void printArray(int[] arr) {
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }


    public static int[] generateIntArray(int len) {
        int[] arr = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(len);
        }
        return arr;
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
