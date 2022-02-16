package string;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * <p>
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 */
public class StringMultiply {

    public static void main(String[] args) {
        StringMultiply stringMultiply = new StringMultiply();
        System.out.println(stringMultiply.multiply("12", "19"));
        System.out.println(stringMultiply.multiply("0", "0"));

    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";//剪枝
        int n1 = num1.length();
        int n2 = num2.length();
        //乘法的结果长度最大为m+n
        int[] nums = new int[n1 + n2];
        //从个位开始相乘 i,j位置上的数字乘积放到i+j+1的位置 多举几个例子就清醒了
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = mul + nums[i + j + 1];
                nums[i + j] += sum / 10;
                nums[i + j + 1] = sum % 10;
            }
        }

        //忽略前面可能的0值 转成字符串
        int i = 0;
        while (nums[i] == 0) i++;
        StringBuilder res = new StringBuilder();
        while (i < n1 + n2) {
            res.append(nums[i]);
            i++;
        }
        return res.toString();
    }
}
