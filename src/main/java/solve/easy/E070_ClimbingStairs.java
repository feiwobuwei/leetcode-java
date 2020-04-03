package solve.easy;

import org.junit.Test;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @author wm
 * @see E509_FibonacciNumber 关联
 */
public class E070_ClimbingStairs {

    @Test
    public void test() {
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(5));
    }

    @Test
    public void test2() {
        System.out.println(climbStairs2(3));
        System.out.println(climbStairs2(5));
    }

    @Test
    public void test3() {
        System.out.println(climbStairs3(3));
        System.out.println(climbStairs3(5));
    }

    /**
     * @see E509_FibonacciNumber#fib(int)
     */
    @Test
    public void test4() {
        // 记忆递归法 详见 E509
    }

    @Test
    public void test5() {
        System.out.println(climbStairs5(3));
        System.out.println(climbStairs5(5));
    }

    @Test
    public void test6() {
        System.out.println(climbStairs6(3));
        System.out.println(climbStairs6(5));
    }

    @Test
    public void testOverflow() {
        // 2147483647
        System.out.println(Integer.MAX_VALUE);
        // 512559680
        System.out.println(climbStairs3(47));
        // 开始溢出
        System.out.println(climbStairs3(48));
    }


    // =============================================

    /**
     * 暴力法 bottom-up
     * <p>
     * time O(2^n)
     * space O(n)
     * <p>
     * 关于空间复杂度 产生了(0,5),(1,5),(2,5),(3,5),(4,5),(5,5),(6,5)
     * 也即7[O(n)]个中间临时递归方法调用
     *
     * @param n 要爬的楼梯阶数
     * @return 所有可能的方法数
     */
    public int climbStairs(int n) {
        return helper(0, n);
    }


    /**
     * climbStairs的辅助方法
     *
     * @param i 当前递归时所在的阶数 level
     * @param n 要爬的楼梯阶数
     * @return 从i到n的所有爬法数
     */
    public int helper(int i, int n) {

        // 三种基准情形
        // 到达了目标楼梯及以上
        if (i >= n) {
            return 0;
        }

        // 还差一阶 此时有一种方法
        if (i == n - 1) {
            return 1;
        }

        // 还差两阶 此时有2种方法
        if (i == n - 2) {
            return 2;
        }

        // 每次迈一步或者两步
        return helper(i + 1, n) + helper(i + 2, n);
    }

    /**
     * 反向递归 top-down
     * 倒过来看 从楼顶往下走(直到走到1 or 2)
     * <p>
     * time: O(2^n) space: O(n)
     * 关于空间复杂度 产生了(5),(4),(3),(2),(1),(0)一共6[O(n)]个中间临时递归方法调用
     *
     * @param n 当前调用要爬到的楼梯阶数
     * @return 所有可能的方法数
     */
    public int climbStairs2(int n) {
        if (n <= 2) {
            return n;
        } else {
            return climbStairs2(n - 1) + climbStairs2(n - 2);
        }
    }

    /**
     * 斐波那契数的非递归算法
     * 其实就是迭代法
     * time:O(n)
     * space:O(1) (固定三个变量)
     *
     * @param n 要爬的楼梯阶数
     * @return 所有可能的方法数
     */
    public int climbStairs3(int n) {

        // 处理基本情形
        if (n < 0) {
            return -1;
        }

        if (n <= 2) {
            return n;
        }

        // 定义三个变量，空间复杂度是O(1)
        int step1 = 1;
        int step2 = 2;
        int step3 = 0;

        // 三个变量一直循环
        // climbStairs(n) = climbStairs(n - 1) + climbStairs(n - 2)
        for (int i = 3; i <= n; i++) {
            step3 = step1 + step2;
            step1 = step2;
            step2 = step3;
        }

        return step3;
    }


    /**
     * 动态规划法,相比迭代法差一点(主要是空间复杂度变差)
     * <p>
     * 时间复杂度O（n）空间复杂度O（n）
     */
    public int climbStairs5(int n) {
        if (n == 1) {
            return 1;
        }
        // 索引从1开始 方便对应关系 dp[0]被舍弃
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 斐波那契数的公式法
     * <p>
     * time O(log(n))。pow方法将会用去 log(n)的时间
     * space O(1)
     */
    public int climbStairs6(int n) {
        final double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) (fibn / sqrt5);
    }

}