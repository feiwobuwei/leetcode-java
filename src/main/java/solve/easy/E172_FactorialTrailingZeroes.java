package solve.easy;

import org.junit.Test;

import java.math.BigInteger;

/**
 * 阶乘后的零
 * <p>
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * 示例 1:
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * 示例 2:
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * <p>
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 *
 * @author minwei
 */
public class E172_FactorialTrailingZeroes {

    @Test
    public void test() {
        System.out.println(trailingZeroes(5));
        System.out.println(trailingZeroes(10));
        System.out.println(trailingZeroes(109));
    }

    @Test
    public void test2() {
        BigInteger factorial = factorial(20);
        // 2432902008176640000 (4个0)
        System.out.println(factorial);

        // 2147483647
        System.out.println(Integer.MAX_VALUE);
    }

    /**
     * 找规律 -- 统计5的倍数的个数
     * <p>
     * time O(lgn) -- 底为5
     * 需要注意 25 有2个5
     * 25的倍数 例如50 75 100 也是2个5
     */
    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            // 累加 5的倍数 + 25的倍数 + 125的倍数 + ...
            res += n / 5;
            n /= 5;
        }
        return res;
    }

    public BigInteger factorial(int n) {

        BigInteger result = new BigInteger("1");
        int i = 1;
        while (i <= n) {
            result = result.multiply(new BigInteger(String.valueOf(i)));
            i++;
        }
        return result;
    }

}
