package solve.easy;

import org.junit.Test;

/**
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * @author minwei
 */
public class E198_HouseRobber {

    @Test
    public void test() {
        int[] money = {2, 7, 9, 3, 1};
        int rob = rob(money);
        System.out.println(rob);

        rob = rob2(money);
        System.out.println(rob);
    }

    @Test
    public void test2() {
        int[] money = {2, 1, 1, 2};
        int rob = rob(money);
        System.out.println(rob);

        rob = rob2(money);
        System.out.println(rob);
    }

    /**
     * DP
     * <p>
     * time O(n)
     * space O(n)
     */
    public int rob(int[] nums) {

        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[len];
    }

    /**
     * 官网的优化
     * <p>
     * time O(n)
     * space O(1)
     */
    public int rob2(int[] num) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : num) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }


}
