package solve.easy;


import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
 * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心索引，那么我们应该返回 -1。
 * 如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * <p>
 * 示例 1:
 * 输入:
 * nums = [1, 7, 3, 6, 5, 6]
 * 输出: 3
 * 解释:
 * 索引3 (nums[3] = 6) 的左侧数之和(1 + 7 + 3 = 11)，与右侧数之和(5 + 6 = 11)相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 * <p>
 * 示例 2:
 * 输入:
 * nums = [1, 2, 3]
 * 输出: -1
 * 解释:
 * 数组中不存在满足此条件的中心索引。
 * <p>
 * 说明:
 * nums 的长度范围为 [0, 10000]。
 * 任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。
 *
 * @author wm
 */
public class E724_FindPivotIndex {

    private int[] input = new int[]{1, 7, 3, 6, 5, 6};

    @Test
    public void test() {
        System.out.println(pivotIndex(input));
    }


    /**
     * 特殊情况 [-1,-1,-1,0,1,1]
     * 第一个元素 右边直接等于0
     */
    public int pivotIndex(int[] nums) {
        // 基本校验
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 数组的所有元素的和
        int sum = Arrays.stream(nums).sum();

        // 如果是索引0的情况
        if (sum - nums[0] == 0) {
            return 0;
        }

        int tmp = 0;
        // 不可能是最后一个索引
        for (int i = 0; i < nums.length - 1; i++) {
            tmp += nums[i];
            if ((sum - nums[i + 1]) % 2 == 0) {
                if (tmp == (sum - nums[i + 1]) / 2) {
                    return i + 1;
                }
            }
        }
        return -1;
    }
}
