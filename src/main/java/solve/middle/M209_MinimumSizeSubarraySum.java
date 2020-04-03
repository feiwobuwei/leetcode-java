package solve.middle;

import org.junit.Test;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，
 * 找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例: 
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * <p>
 * 进阶:
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 * @author wm
 */
public class M209_MinimumSizeSubarraySum {

    private int[] input = new int[]{2, 3, 1, 2, 4, 3};

    @Test
    public void test() {
        System.out.println(minSubArrayLen(7, input));
    }

    /**
     * 双指针法
     * 本质是滑动窗口法
     * time O(n)
     *
     * @see solve.easy.E485_MaxConsecutiveOnes#findMaxConsecutiveOnes(int[])
     */
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (right < n) {
            sum += nums[right];
            right++;
            while (sum >= s) {
                min = Math.min(min, right - left);
                sum -= nums[left];
                left++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;

    }

    /**
     * 分治法
     */
    public int minSubArrayLen2(int s, int[] nums) {
        return 0;
    }
}
