package array;


import java.util.function.Predicate;

import static common.Utils.printArray;
import static common.Utils.swap;




/**
 * 一个整型 数组中的一些元素
 * 把数组中的0 移到数组的最后面 其他元素位置相对不对
 * 把数组中的奇数移到最后面 。。。
 * 把数组中的偶数移到最后面 。。。
 * 把数组中3的倍数移到最后面 。。。
 *
 *
 *
 */

public   class MoveX2End {

    public static void main(String[] args) {

        int[] arr = new int[] { 1, 0, 3,0,2,0,4};

        printArray(arr);

        moveX2End(arr, i-> i!=0);
        printArray(arr);

        moveX2End(arr, i-> i%2==0);
        printArray(arr);

        int[] nums = new int[] {1,2,3,4};
        exchange(nums);
        printArray(nums);

    }

    private  static void moveX2End(int[] arr, Predicate<Integer> cond) {
        //双指针思路 一个指针指向 待插入的位置  另一个往后扫描
        //找到不是0的数据就插入 然后指针后移
        //想到快排的划分 交换
        int N = arr.length;
        int left = 0;
        for (int i = 0; i < N; i++) {
            if(cond.test(arr[i])) {
                swap(arr, i, left ++);
            }
        }
    }


    public static int[] exchange(int[] nums) {
        //双指针 一个指针遍历  一个left指针表示左侧全是奇数
        //左指针右走 右指针左移 交换 这是快排的partition
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            while(  nums[left]%2==1 && left < right) left++;
            while( (nums[right]%2== 0) && right > 0) right --;
            if(left < right)  swap(nums,left,right);
        }
        return nums;
    }

}