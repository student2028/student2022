package array;

/**
 * 乘水最多的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * //输入：[1,8,6,2,5,4,8,3,7]: 49
 * //[1,1] : 1
 * //[1,2,1] :2
 * 贪心一开始试错了 看官解是双指针
 * 这道题是使用双指针的一道非常经典的题目了
 *
 */
public class MaxArea {
    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();

        System.out.println(maxArea.maxArea(1,1));
        System.out.println(maxArea.maxArea(1,2,1));
        System.out.println(maxArea.maxArea(1,8,6,2,5,4,8,3,7));

    }

    public int maxArea(int...  height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while(left < right) {
            int min = Math.min(height[left],height[right]);//高度取小值
            int area = min *(right - left);//求面积
            res = Math.max(area, res);
            if(height[right] > height[left]) left++; else right --;
        }
        return res;
    }
}
