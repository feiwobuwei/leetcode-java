package solve.easy;

import org.junit.Test;

/**
 * 最大子序和
 * <p>
 * 注意: 子数组最少包含一个元素），返回其最大和
 *
 * @author minwei
 */
public class E053_MaximumSubarray {


    /**
     * 双重迭代
     * <p>
     * time O(n^2)
     * space O(1）
     */
    private static int maxSubArray2(int[] nums) {
        // 最终最大和
        int maxSum = nums[0];
        // N次迭代
        for (int i = 0; i < nums.length; i++) {
            int thisSum = 0;
            // N-i次迭代 相比maxSubArray1的区别是 默认每次终点都直接到尾元素
            // j为窗口的游标 逐格向右移动。 thisSum 记录每一次的临时最大和
            for (int j = i; j < nums.length; j++) {
                thisSum += nums[j];

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }

        return maxSum;
    }

    /**
     * 动态规划(DP)
     * 状态转移方程 dp[i] = nums[i] + (dp[i - 1] < 0 ? 0 : dp[i - 1]);
     * <p>
     * time O(n) space O(n）
     */
    private static int maxSubArray3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp[i] 表示以 i 为结尾的最大连续子数组和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        // res用于记录dp[]数组中的最大值
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 前面的子序和如果是是负数的话,抛去后肯定能获得更大的和
            dp[i] = nums[i] + (dp[i - 1] < 0 ? 0 : dp[i - 1]);
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    /**
     * 双变量迭代法和动态规划方法类似
     * <p>
     * time O(n) space O(1)
     */
    private static int maxSubArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 双变量迭代法
        int curSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 如果取后者 即说明curSum<0 之前的子序和全部抛弃 选择新的起点
            // 每次有更大的子序和 就取更大的
            curSum = Math.max(curSum + nums[i], nums[i]);
            maxSum = Math.max(curSum, maxSum);
        }

        return maxSum;
    }

    /**
     * 分治算法
     * 最大子序和 要么位于左区间 要么位于右区间 要么跨越边界
     * <p>
     * 平均 time O(n*lgn)
     */
    private static int maxSubArray4(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        return maxSumRec(nums, 0, nums.length - 1);
    }

    /**
     * 辅助细分函数
     * <p>
     * time O(n*lgn)
     * space O(lgn)
     *
     * @param nums  本次调用的区间数组
     * @param left  头指针
     * @param right 尾指针
     * @return 本区间的最大子序和
     */
    private static int maxSumRec(int[] nums, int left, int right) {

        // 基准情形
        if (left == right) {
            return nums[left];
        }

        int center = (right - left) / 2 + left;
        int maxLeftSum = maxSumRec(nums, left, center);
        int maxRightSum = maxSumRec(nums, center + 1, right);

        // 求左区间的最大子序和
        int maxLeftBorderSum = Integer.MIN_VALUE;
        int leftBorderSum = 0;

        for (int i = center; i >= left; i--) {
            leftBorderSum += nums[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        // 求右区间的最大子序和
        int maxRightBorderSum = Integer.MIN_VALUE;
        int rightBorderSum = 0;

        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += nums[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }

        // 最后取三种情形中的最大值
        return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    private static int max3(int a, int b, int c) {
        int temp = a > b ? a : b;
        return temp > c ? temp : c;
    }

    private int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
    private int[] nums2 = new int[]{-2, -1};

    @Test
    public void testMaxSum1() {
        int result = maxSubArray(nums);
        System.out.println(result);

        result = maxSubArray(nums2);
        System.out.println(result);

    }

    @Test
    public void testMaxSum2() {
        int result = maxSubArray2(nums);
        System.out.println(result);

        result = maxSubArray2(nums2);
        System.out.println(result);

    }

    @Test
    public void testMaxSum3() {
        int result = maxSubArray3(nums);
        System.out.println(result);

        result = maxSubArray3(nums2);
        System.out.println(result);

    }

    @Test
    public void testMaxSum4() {
        int result = maxSubArray4(nums);
        System.out.println(result);

        result = maxSubArray4(nums2);
        System.out.println(result);

    }

}
