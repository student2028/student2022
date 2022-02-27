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

    //思路 面积需要有两条线  一条线 来自左边 一条线来自右边 使用双指针
    //面积是由两边低的那个高度决定的
    //怎么移动？ 一直保持着高的值 让小的值移动即可
    public int maxArea(int...  height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while(left < right) {
            int minHeight = Math.min(height[left],height[right]);
            int area = minHeight * (right - left);
            maxArea = Math.max(maxArea, area);
            if(height[left]> height[right]) right --; else left ++;
        }
        return maxArea;
    }
}
