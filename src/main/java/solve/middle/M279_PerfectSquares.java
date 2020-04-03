package solve.middle;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）
 * 使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * @author minwei
 * @see M322_CoinChange
 */
public class M279_PerfectSquares {

    @Test
    public void test() {
        int i = numSquares(18);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = numSquares2(4);
        System.out.println(i);
    }

    /**
     * 广度优先搜索 -- 14.78%
     * <p>
     * 深用栈,广用队
     * <p>
     * time O(n2)
     * space O(n) 实际耗损更大
     */
    public int numSquares(int n) {
        int result = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int now = queue.poll();
                for (int j = 1; j * j <= now; j++) {
                    if (now - j * j == 0) {
                        return result;
                    }
                    queue.add(now - j * j);
                }
            }
            result++;
        }
        return -1;
    }

    /**
     * 动态规划 -- 94.93%
     * <p>
     * time O(n^1.5)
     * space O(n)
     */
    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = i;
        }
        for (int i = 2; i < dp.length; i++) {
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
            }
        }
        return dp[n];
    }


}
