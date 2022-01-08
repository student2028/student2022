package array;

import common.Utils;

/**
 * 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-string
 */
public class ReverseString {

    public static void main(String[] args) {

        char[] chars = "good".toCharArray();

        Utils.printArray(chars);

        reverseString(chars);

        Utils.printArray(chars);

    }

    private static void reverseString(char[] chars) {
        // 快排的划分一样 双指针
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char c = chars[left];
            chars[left] = chars[right];
            chars[right] = c;
            left++;
            right--;
        }

    }

}
