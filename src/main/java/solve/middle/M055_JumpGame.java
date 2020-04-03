package solve.middle;

import org.junit.Test;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * <p>
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是0，
 * 所以你永远不可能到达最后一个位置。
 *
 * @author minwei
 */
public class M055_JumpGame {

    private int[] nums = {2, 3, 1, 1, 4};

    @Test
    public void test() {
        boolean b = canJump(nums);
        System.out.println(b);
    }

    @Test
    public void test2() {
        boolean b = canJump2(nums);
        System.out.println(b);
    }

    @Test
    public void test3() {
        boolean b = canJump3(nums);
        System.out.println(b);
    }

    public boolean canJump(int[] nums) {
        return canJumpFromPosition(0, nums);
    }

    /**
     * 回溯法 -- 提交超时
     * <p>
     * time 最坏 O(2^n)  T(n)=2*T(n-1)
     * space O(n)
     */
    public boolean canJumpFromPosition(int index, int[] nums) {
        int n = nums.length;
        if (index == n - 1) {
            return true;
        }

        // 可以到达的最远位置,但不能超过数组长度
        int furthestJump = Math.min(index + nums[index], n - 1);

        for (int i = index + 1; i <= furthestJump; i++) {
            // 如果最终可达 回溯回来都是true 最终返回的也是true
            if (canJumpFromPosition(i, nums)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 动态规划 -- 5.05%
     * <p>
     * time  O(n ^ 2)
     * space O(n)。
     */
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        // 状态定义: dp[i]:从第i处能否到达数组末尾
        boolean[] dp = new boolean[n];
        // 边界条件
        dp[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 1; j <= nums[i] && i + j < n; j++) {
                // 状态转移方程 dp[i] = dp[i + 1] || dp[i + 2] || ... || dp[i + nums[i]]
                dp[i] |= dp[j + i];
            }
        }
        return dp[0];
    }

    /**
     * 贪心算法 -- 最优解 98.10%
     * <p>
     * 在动态规划解法中，其状态转移为：dp[i] = dp[i + 1] || dp[i + 2] || ... || dp[i + nums[i]]
     * 实际上只需要找到第一个dp[i + j]为true的值即可，即最左侧的为true的值。
     * <p>
     * 时间复杂度和空间复杂度均是O(n)
     */
    public boolean canJump3(int[] nums) {
        int n = nums.length;
        int lastPos = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            // 我们能从i处跳到lastPos
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}
