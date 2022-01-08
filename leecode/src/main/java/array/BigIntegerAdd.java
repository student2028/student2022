package array;

/**
 * leecode 415 easy
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）, 也不能直接将输入的字符串转换为整数形式。
 *
 * 字符串就是字符数组  char - '0' 可以获得正确的数字值
 *
 *输入：num1 = "456", num2 = "77"
 * 输出："533"
 *
 *
 * 有前边链表字符串相加这个同理做法
 * 不同之处是 链表表示的是直接可以相加
 * 但字符串表示的时候需要从尾部相加
 * 加法从个位开始 所以要从长度-1开始算
 * 需要注意的另一点是 需要字符串最后输出的时候不要反了
 * 这道题印象极其深刻 是因为有一次面试一个公司被问到了
 * 当时我没有刷过题 做得很艰难 也是考虑merge数组的思路
 * 但是没练习过 写得不完美 很长 还忽略了carry
 *
 */
public class BigIntegerAdd {

    public static void main(String[] args) {

        System.out.println('0' - '0');
        System.out.println('1' - '0');
        System.out.println('9' - '0');


        System.out.println(addStrings("456","77"));

    }

    private static String addStrings(String num1, String num2) {
        String result = "";
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;

        int sum = 0; //表示和值
        int carry = 0;//表示进位值

        //注意这个条件 容易丢失carry > 0 最后一个数字计算完了 如果有进位 也是要考虑的
        while(l1 >= 0 || l2 >= 0 || carry > 0) {
            int n1 = l1 >= 0 ? num1.charAt(l1--) - '0' : 0;
            int n2 = l2 >= 0 ? num2.charAt(l2--)  - '0' : 0;
            sum = n1 + n2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            result =  sum + result;
        }

        return result;
    }


}
