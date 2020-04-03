package solve.easy;

import org.junit.Test;

/**
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * <p>
 * 示例 1:
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * <p>
 * 注意：
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 *
 * @author wm
 */
public class E485_MaxConsecutiveOnes {

    private int[] input = new int[]{1, 1, 0, 1, 1, 1};
    private int[] input2 = new int[]{1, 1, 1, 0, 0, 1, 1, 1, 1};
    private int[] input3 = new int[]{0, 1};

    @Test
    public void test() {
        System.out.println(findMaxConsecutiveOnes(input));
        System.out.println(findMaxConsecutiveOnes(input2));
        System.out.println(findMaxConsecutiveOnes(input3));
    }

    /**
     * 滑动窗口 -- 71.82%
     */
    public int findMaxConsecutiveOnes(int[] nums) {

        // TODO 待优化
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                int left = i;
                int right = i;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == 1) {
                        right = j;
                        i = j;
                    } else {
                        // 直接短路 窗口起点移动
                        i = j;
                        break;
                    }
                }
                result = Math.max(result, right - left + 1);
            }
        }

        return result;
    }
}
