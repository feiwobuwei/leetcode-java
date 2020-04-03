package solve.middle;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
 * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 * @author wm
 * @see M015_ThreeSum
 */
public class M016_ThreeSumClosest {

    private int[] nums = {-1, 2, 1, -4};

    @Test
    public void test() {
        int closest = threeSumClosest(nums, 1);
        System.out.println(closest);

        int closest1 = threeSumClosest(nums, -3);
        System.out.println(closest1);
    }

    @Test
    public void test2() {
        int closest = threeSumClosest2(nums, 1);
        System.out.println(closest);

        int closest1 = threeSumClosest2(nums, -3);
        System.out.println(closest1);
    }

    /**
     * 双指针法 -- 62.94%
     * <p>
     * time: O(n^2)
     * space: O(1)
     */
    public int threeSumClosest(int[] nums, int target) {

        // 先假定前三者之和最接近
        int res = nums[0] + nums[1] + nums[2];
        // 先排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                // 更接近
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
            }
        }
        return res;
    }

    /**
     * 暴力法 -- 5.07%
     * <p>
     * 时间复杂度是O(n ^ 3)，其中n为nums数组的长度。
     * 空间复杂度是O(1)
     */
    public int threeSumClosest2(int[] nums, int target) {
        int n = nums.length, diff = Integer.MAX_VALUE, result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (Math.abs(nums[i] + nums[j] + nums[k] - target) < diff) {
                        diff = Math.abs(nums[i] + nums[j] + nums[k] - target);
                        result = nums[i] + nums[j] + nums[k];
                    }
                }
            }
        }
        return result;
    }

}
