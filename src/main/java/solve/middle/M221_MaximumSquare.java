package solve.middle;

import org.junit.Test;

/**
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 *
 * @author minwei
 */
public class M221_MaximumSquare {

    char[][] nums = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
    };

    @Test
    public void test() {
        int i = maximalSquare(nums);
        System.out.println(i);
    }

    /**
     * 动态规划
     * dp[i][j]代表以 i,j为正方形右下角的最大边长
     * <p>
     * time O(mn)
     * space O(mn)
     */
    public int maximalSquare(char[][] matrix) {
        int result = 0;
        int m = matrix.length;
        if (m == 0) {
            return result;
        }
        int n = matrix[0].length;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = min3(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result * result;
    }

    private int min3(int i, int j, int k) {
        int temp = (i < j) ? i : j;
        return temp < k ? temp : k;
    }

}
