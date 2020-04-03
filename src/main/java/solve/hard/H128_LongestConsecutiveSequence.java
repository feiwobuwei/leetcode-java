package solve.hard;

import org.junit.Test;

import java.util.HashMap;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * <p>
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 示例:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * @author minwei
 */
public class H128_LongestConsecutiveSequence {

    @Test
    public void test() {
        int[] nums = {100, 4, 200, 1, 3, 2};
        int i = longestConsecutive(nums);
        System.out.println(i);
    }

    public int longestConsecutive(int[] nums) {
        int result = 0;
        HashMap<Integer, Boolean> calculatedMap = new HashMap<>(16);
        for (int num : nums) {
            // 初始时刻所有数均未被考虑过
            calculatedMap.put(num, false);
        }
        for (int num : nums) {
            // 如果nums[i]已经被考虑过了，跳过本次循环
            if (calculatedMap.get(num)) {
                continue;
            }
            int left = num - 1;
            while (calculatedMap.containsKey(left) && !calculatedMap.get(left)) {
                calculatedMap.put(left, true);
                left--;
            }
            int right = num + 1;
            while (calculatedMap.containsKey(right) && !calculatedMap.get(right)) {
                calculatedMap.put(right, true);
                right++;
            }
            result = Math.max(result, right - left - 1);
        }
        return result;
    }
}
