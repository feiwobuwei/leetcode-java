package solve.easy;

public class E746_MinCostClimbingStairs {

    public static void main(String[] args) {

        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(cost));

    }

    public static int minCostClimbingStairs(int[] cost) {

        int[] dp = new int[cost.length + 1];

        for (int i = 2; i <= cost.length; i++) {
            int sp1 = dp[i - 2] + cost[i - 2];
            int sp2 = dp[i - 1] + cost[i - 1];
            dp[i] = Math.min(sp1, sp2);
        }
        return dp[cost.length];

    }
}
