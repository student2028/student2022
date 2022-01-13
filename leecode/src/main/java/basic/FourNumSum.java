package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 原理同三数之和
 */
public class FourNumSum {

    public static void main(String[] args) {

        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;

        fourNUmSum(nums, target).stream().forEach(System.out::println);


    }

    private static List<List<Integer>> fourNUmSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            for (int j = i + 1; j < N; j++) {

                if (j > i + 1 && nums[j - 1] == nums[j]) continue;
                int left = j + 1;
                int right = N - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) right--;
                    else if (sum < target) left++;
                    else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[right - 1] == nums[right]) right--;
                        while (left < right && nums[left + 1] == nums[left]) left++;
                        left++;
                        right--;
                    }
                }

            }
        }
        return res;
    }


}
