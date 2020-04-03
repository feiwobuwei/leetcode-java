package solve.middle;

import org.junit.Test;

import java.util.Arrays;

/**
 * 不同路径
 * <p>
 * 输入: m = 7, n = 3
 *
 * @author minwei
 */
public class M062_UniquePaths {

    @Test
    public void test() {
        int i = uniquePaths(7, 3);
        System.out.println(i);

        int j = uniquePaths(2, 1);
        System.out.println(j);
    }

    @Test
    public void test2() {
        int i = uniquePaths2(7, 3);
        System.out.println(i);

        int j = uniquePaths(13, 23);
        System.out.println(j);
    }

    @Test
    public void test3() {
        int i = uniquePaths3(7, 3);
        System.out.println(i);

        int j = uniquePaths(2, 1);
        System.out.println(j);
    }

    /**
     * 动态规划 一维数组 97.90%
     * <p>
     * time O(mn)
     */
    public int uniquePaths(int m, int n) {
        int[] memo = new int[n];
        Arrays.fill(memo, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                memo[j] += memo[j - 1];
            }
        }
        return memo[n - 1];
    }

    /**
     * 数学公式法 -- 提交超时
     */
    public int uniquePaths2(int m, int n) {
        // n 等于0就是0 n等于1 就是1
        if (n <= 1) {
            return n;
        }
        return cascadeSum(m, n - 1);
    }

    /**
     * 重叠和 递归法
     * level 1 : 输入多少就返回多少 1 2 3 ... n
     * level 2 : 返回到n的求和 公式为 1+2+3 ... + ... n = n(n+1)/2
     * level 3 : 再次对上面的式子求n项和 n(n+1)(2n+1)/12 + n(n+1)/4
     * ...
     * <p>
     * 示例
     * cascadeSum(14,2)=105
     * cascadeSum(7,3)=84
     *
     * @param n     目标
     * @param level 重叠级别
     */
    private int cascadeSum(int n, int level) {
        if (level == 1) {
            return n;
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += cascadeSum(i, level - 1);
        }
        return result;
    }

    /**
     * 动态规划 二维数组
     * 可以填表完成
     * <p>
     * time O(mn)
     */
    public int uniquePaths3(int m, int n) {
        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    res[0][j] = 1;
                } else if (j == 0) {
                    res[i][0] = 1;
                } else {
                    res[i][j] = res[i - 1][j] + res[i][j - 1];
                }
            }
        }
        return res[m - 1][n - 1];

    }

}
