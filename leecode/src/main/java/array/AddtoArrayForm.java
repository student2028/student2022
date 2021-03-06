package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * <p>
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * <p>
 * 示例 2：
 * <p>
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddtoArrayForm {

    public static void main(String[] args) {
        AddtoArrayForm test = new AddtoArrayForm();
        System.out.println(test.addToArrayForm(new int[]{6}, 999));

    }

    public List<Integer> addToArrayForm(int[] num, int k) {
        int n = num.length;
        LinkedList<Integer> res = new LinkedList<>();
        int sum, carry = 0;
        for (int i = n - 1 ; i >= 0 || k > 0 || carry != 0; i--) {
            int a = i >= 0 ? num[i] : 0;
            int b = k > 0 ? k % 10 : 0;
            k = k/10;
            sum = a + b + carry;
            carry = sum / 10;
            res.addFirst(sum % 10);
        }

        return res;
    }

    //官方题解 更加简洁
    public List<Integer> addToArrayForm2(int[] num, int k) {
        List<Integer> res = new ArrayList<Integer>();
        int n = num.length;
        for (int i = n - 1; i >= 0 || k > 0; --i, k /= 10) {
            if (i >= 0) {
                k += num[i];
            }
            res.add(k % 10);
        }
        Collections.reverse(res);
        return res;
    }


}
