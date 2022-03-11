package hard;

/**
 * leecode 42 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5] 输出 9
 * 思路：
 * 首先，如果按照列来计算的话，宽度一定是1了，我们再把每一列的雨水的高度求出来就可以了。
 * 可以看出每一列雨水的高度，取决于，该列 左侧最高的柱子和右侧最高的柱子中最矮的那个柱子的高度。
 * min(lHeight, rHeight) - height
 * 首先从头遍历所有的列，并且要注意第一个柱子和最后一个柱子不接雨水
 */
public class TrapRain {

    public static void main(String[] args) {
        TrapRain test = new TrapRain();
        System.out.println(test.trap(new int[]{4, 2, 0, 3, 2, 5}));
        System.out.println(test.trap2(new int[]{4, 2, 0, 3, 2, 5}));

    }

    //暴力解法
    public int trap(int[] height) {
        int res = 0;
        int n = height.length;
        for (int i = 1; i < n - 1; i++) {
            int max_left = height[i];
            int max_right = height[i];

            for (int r = i + 1; r < n; r++) max_right = Math.max(max_right, height[r]);
            for (int l = i - 1; l >= 0; l--) max_left = Math.max(max_left, height[l]);
            int h = Math.min(max_left, max_right) - height[i];
            if (h > 0) res += h;
        }

        return res;
    }

    //双指针法
    public int trap2(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int max_left = 0;
        int max_right = 0;
        int res = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (max_left < height[left]) max_left = height[left];
                else res += max_left - height[left];
                left++;
            } else {
                if (max_right < height[right]) max_right = height[right];
                else res += max_right - height[right];
                right--;
            }
        }

        return res;
    }

}
