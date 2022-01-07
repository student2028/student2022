package array;

import java.util.Arrays;

/**
 * 字符串数组的最长公共前缀
 * longest common prefix
 * 1. 暴力方法
 * 2. 使用二分法
 * 3. 使用分治法
 */
public class LCP {

    public static void main(String[] args) {
        String[] arr = {"abcc", "abcc", "abcb"};

        System.out.println(lcp(arr));


    }

    private static String lcp(String[] arr) {

        //找到数组中最短的字符串
        //使用二分法 先匹配整个字符串，如果不满足，则截成一半处理
        // 如果一半满足了？ 再加
        //一直到最后为空 反回空，如果中间匹配，则返回
        int minLen = arr[0].length();
        for (int i = 1; i < arr.length; i++) {
            if ( minLen > arr[i].length())
                 minLen = arr[i].length();
        }

        int left = 0;
        int right = minLen;
        while(left < right) {
            int mid = left + (right - left + 1)/ 2 ;
            if( allIn(arr, mid)){
                left = mid;
            } else {
                right = mid -1;
            }
        }

        return arr[0].substring(0,left);
    }

    private static boolean allIn(String[] arr, int len) {
        String str = arr[0].substring(0, len);
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].startsWith(str)) return false;
        }
        return true;
    }

}
