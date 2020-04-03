package solve.hard;

import org.junit.Test;

/**
 * 缺失的第一个正数
 * <p>
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * <p>
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 说明:
 * <p>
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 *
 * @author minwei
 */
public class H041_FirstMissingPositive {

    @Test
    public void test() {
        int[] nums = {3, 4, -1, 1};
        int i = firstMissingPositive(nums);
        System.out.println(i);
    }

    /**
     * 让数组的第i个位置应该存放值i+1 -- 100%
     * <p>
     * time O(n)
     * space O(1)
     */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                continue;
            }
            // 首先确保别越界 可能会有多次交换 所以在循环中
            while ((nums[i] > 0 && nums[i] <= nums.length) && nums[nums[i] - 1] != nums[i]) {
                // 把应该在该位置的数字交换过来
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 如果是类似[1, 2, 3]的情况，小没有出现的最小正数是4
        return nums.length + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
