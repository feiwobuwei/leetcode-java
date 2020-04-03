package solve.middle;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
 * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * @author minwei
 */
public class M238_ProductOfArrayExceptSelf {

    int[] nums = {1, 2, 3, 4};

    int[] nums2 = {2, 3, 4, 5, 6};

    @Test
    public void test() {
        int[] ints = productExceptSelf(nums);
        System.out.println(Arrays.toString(ints));

        int[] ints2 = productExceptSelf(nums2);
        System.out.println(Arrays.toString(ints2));
    }

    /**
     * two-pass --  96.58%
     * <p>
     * time O(n)
     * space O(1)
     */
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;

        int left = 1;
        // 每个数先获取它左边的数字乘积
        for (int i = 1; i <= nums.length - 1; i++) {
            result[i] = left * nums[i - 1];
            left *= nums[i - 1];
        }

        // 再获取它右边的数字乘积
        int right = 1;
        for (int j = nums.length - 2; j >= 0; j--) {
            result[j] *= right * nums[j + 1];
            right *= nums[j + 1];
        }

        return result;

    }
}
