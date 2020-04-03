package solve.easy;

import org.junit.Test;

/**
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。
 * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 给定 N，计算 F(N)。
 * <p>
 * 0 1 1 2 3 5
 *
 * @author wm
 * @see E070_ClimbingStairs
 */
public class E509_FibonacciNumber {

    @Test
    public void test() {
        System.out.println(fib(3));
        System.out.println(fib(5));
    }

    @Test
    public void test2() {
        System.out.println(fib2(3));
        System.out.println(fib2(5));
    }

    /**
     * 记忆递归法 -100.00%
     * <p>
     * time O(n)
     * space O(n)
     */
    public int fib(int N) {
        int[] memo = new int[N + 1];
        return helper(N, memo);
    }

    public int helper(int i, int[] memo) {
        // 基准情形
        if (i == 1 || i == 0) {
            return i;
        }

        // 如果这个值已经被算过 直接返回算出来的值
        if (memo[i] > 0) {
            return memo[i];
        }

        memo[i] = helper(i - 1, memo) + helper(i - 2, memo);
        return memo[i];
    }


    /**
     * 尾递归
     * <p>
     * time O(n)
     * space O(n)
     */
    public int fib2(int N) {
        // 第三个参数实际是 0+1
        // 第一个参数实际是次数
        // TODO
        return helper2(N - 1, 1, 1);
    }

    /**
     * 尾递归的辅助方法
     * <p>
     * 不需要通常递归的压stack，出stack操作，不会有stackOverflow
     *
     * @param n    要求的第n个数 每次减1
     * @param next 辅助结果1
     * @param pre  辅助结果2
     * @return 第n个数斐波那契数
     */
    public int helper2(int n, int next, int pre) {
        if (n == 0) {
            // 注意，此处不是1，是ret1，也就是最后的结果
            return next;
        }

        return helper2(n - 1, pre, pre + next);
    }

}