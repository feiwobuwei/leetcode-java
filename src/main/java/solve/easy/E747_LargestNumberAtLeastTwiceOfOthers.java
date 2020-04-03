package solve.easy;

import org.junit.Test;

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * 至少是其他数字两倍的最大数
 * <p>
 * 在一个给定的数组nums中，总是存在一个最大元素 。
 * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
 * 如果是，则返回最大元素的索引，否则返回-1。
 * <p>
 * 示例 1:
 * 输入: nums = [3, 6, 1, 0]
 * 输出: 1
 * 解释: 6是最大的整数, 对于数组中的其他整数,
 * 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
 *  
 * 示例 2:
 * 输入: nums = [1, 2, 3, 4]
 * 输出: -1
 * 解释: 4没有超过3的两倍大, 所以我们返回 -1.
 *  
 * 提示:
 * nums 的长度范围在[1, 50].
 * 每个 nums[i] 的整数范围在 [0, 100].
 *
 * @author wm
 */
public class E747_LargestNumberAtLeastTwiceOfOthers {

    private int[] input = new int[]{3, 6, 1, 0};

    @Test
    public void test() {
        System.out.println(dominantIndex(input));
    }

    @Test
    public void test2() {
        System.out.println(dominantIndex2(input));
    }

    public int dominantIndex(int[] nums) {
        // 基本校验
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 特殊情况
        if (nums.length == 1) {
            return 0;
        }

        int max;
        if ((max = helper(nums)) != -1) {
            for (int i = 0; i < nums.length; i++) {
                if (max == nums[i]) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int helper(int[] nums) {
        OptionalInt maxOptional = Arrays.stream(nums).max();
        int max = 0;
        if (maxOptional.isPresent()) {
            max = maxOptional.getAsInt();
        }

        final int finalMax = max;
        OptionalInt secondMaxOptional = Arrays.stream(nums).filter(x -> x < finalMax).max();
        if (secondMaxOptional.isPresent()) {
            if (2 * secondMaxOptional.getAsInt() <= finalMax) {
                return finalMax;
            }
        }
        return -1;
    }

    // ======================================

    public int dominantIndex2(int[] nums) {
        int maxIndex = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // 快速短路法
        for (int i = 0; i < nums.length; ++i) {
            if (maxIndex != i && nums[maxIndex] < 2 * nums[i]) {
                return -1;
            }
        }
        return maxIndex;
    }

}
