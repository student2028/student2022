package array;

/***
 * 找众数
 * 数组中出现次数超过一半的数即为众数
 * 思路1.
 *   使用快排中的 partition 划分 按排序来做  但不用做完 只需求位于n/2的位置 中间位置的数肯定就是众数
 * 思路2.
 * 技巧法 遍历一次数组 相同则加 不同则减 变成0则换下一个
 *
 */
public class FindMajor {

    public static void main(String[] args) {

        int[] arr = { 1, 2, 2, 3, 4, 2,2,4,4,4,4,4,4};
        int count = 0;
        int N = arr.length;
        int a = arr[0];
        for (int i = 1; i < N; i++) {
            if(a == arr[i]) count ++ ;
            else {
                count --;
                if(count <= 0 ) {
                    a = arr[i];
                    count = 0;
                }
            }
        }
        System.out.println(a);
    }
}
