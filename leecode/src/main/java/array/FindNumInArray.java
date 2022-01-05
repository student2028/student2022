package array;


/**
 * 题目：一个整型数组里除了两个数字之外，其他的数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 *  借助hashmap
 *  双循环检测
 *  都不是理想的回答
 *
 * 官解 中这道题有固定的套路 根据数组的特点 其他的数据都是2次
 * 直接把数组中所有元素进行异或
 * 然后所得的值就是 在数组 中 只出现了一次的数据
 *
 * 理解异或
 * 0 ^ a = a
 * a ^ b ^ a = b
 *
 * 延深思考
 * 有算法题是 如果这样的 只出现一次的数 在数组中出现了两个 或三个怎么找？
 *
 */
public class FindNumInArray {

    public static void main(String[] args) {


        int[] arr = {1,3,2,2,1,4,4,5,5,6,6};

        int N = arr.length;
        int x = 0;
        for (int i = 0; i < N; i++) {
            x ^= arr[i];
        }
        System.out.println(x);

    }


}
