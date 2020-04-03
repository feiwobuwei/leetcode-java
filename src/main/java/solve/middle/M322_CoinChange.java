package solve.middle;

import org.junit.Test;

/**
 * 零钱兑换
 * <p>
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * <p>
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * <p>
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * @author minwei
 */
public class M322_CoinChange {

    private int[] coins = {1, 2, 5};

    @Test
    public void test() {
        int amount = 11;
        int i = coinChange(coins, amount);
        System.out.println(i);
    }

    /**
     * 动态规划 -- 98.39%
     * <p>
     * 状态定义 dp[i]表示当 有i这么多钱时所需最少的硬币数量。
     * <p>
     * 状态转移方程 dp[i] = min(dp[i] , dp[i - coins[j] + 1)
     * <p>
     * time O(S*n) S金额 n硬币面值数量(该数组无需排序)
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 因为最终结果是求最小值 先初始化为较大值
        for (int i = 0; i < amount + 1; i++) {
            dp[i] = amount + 1;
        }
        dp[0] = 0;
        for (int coin : coins) {
            for (int j = coin; j < amount + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }
        // 当最终结果如果等于amout +1，即表示没有该组合，返回-1.
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }


}
