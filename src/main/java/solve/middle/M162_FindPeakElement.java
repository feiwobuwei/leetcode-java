package solve.middle;

import org.junit.Test;

/**
 * 峰值元素是指其值大于左右相邻值的元素。
 * <p>
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * <p>
 * 示例 2:
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * 说明:
 * 你的解法应该是 O(logN) 时间复杂度的。
 *
 * @author minwei
 */
public class M162_FindPeakElement {

    @Test
    public void test() {
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        int peakElement = findPeakElement(nums);
        System.out.println(peakElement);
    }

    /**
     * 根据题目要求 只能是二分法了
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // 根据左右指针计算中间位置 m，并比较 m 与 m+1 的值，
        // 如果 m 较大，则左侧存在峰值，r = m，
        // 如果 m + 1 较大，则右侧存在峰值，l = m + 1

        // 停止循环时 必定是一次进入了if分支 一次进入了else分支 恰好说明mid比两边都大
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;

    }

}
