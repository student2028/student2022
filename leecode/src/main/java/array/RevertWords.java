package array;


import common.Utils;

/**
 * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
 * <p>
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * <p>
 * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
 * <p>
 * 说明：
 * 输入字符串 s 可以在前面、后面 或者 单词间 包含多余的空格。
 * 翻转后单词间应当仅用一个空格分隔。
 * 翻转后的字符串中不应包含额外的空格。
 * <p>
 * 输入：s = " the sky is blue "
 * 输出："blue is sky the"
 *
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 */
public class RevertWords {

    public static void main(String[] args) {

        String str = "   the sky  is blue   ";

        String str2 = removeExtraSpaces(str);
        System.out.println(str2);

        System.out.println(reverseWords(str2));
    }

    private static String reverseWords(String str) {

        char[] chars = str.toCharArray();
        int N = chars.length;
        int left = 0;
        int right = 0;
        reverseString(chars, 0, N - 1);
        System.out.println(String.valueOf(chars));

        //reverse each word
        while (right < N) {
            while (right < N && chars[right] != ' ') right++;
            //遇到空格或到结尾 则需要反转一个单词
            if(right == N ) right --;
            reverseString(chars, left, right == N - 1 ? right: right - 1);
            left = right + 1;
            right = left;
        }

        return String.valueOf(chars);
    }

    //反转left ... right 之间的字符
    private static void reverseString(char[] chars, int left, int right) {

        while (left < right) {
            char c = chars[left];
            chars[left] = chars[right];
            chars[right] = c;
            left++;
            right--;
        }
    }

    /**
     * 去除首尾的空格 如果中间有多个空格 则保留一个
     *
     * @param str
     */
    private static String removeExtraSpaces(String str) {
        StringBuilder sb = new StringBuilder();
        int left = 0;
        int right = str.length() - 1;
        while (str.charAt(left) == ' ') left++;
        while (str.charAt(right) == ' ') right--;
        while (left <= right) {
            char c = str.charAt(left);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') sb.append(c);
            left++;
        }

        return sb.toString();
    }
}
