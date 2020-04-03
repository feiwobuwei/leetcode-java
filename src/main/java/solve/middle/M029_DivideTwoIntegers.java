package solve.middle;

import org.junit.Test;

/**
 * 两数相除
 * <p>
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法应该向零截断。
 * <p>
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 * • 被除数和除数均为 32 位有符号整数。
 * • 除数不为 0。
 * • 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。
 * 本题中，如果除法结果溢出，则返回 2^31 − 1。
 *
 * @author minwei
 */
public class M029_DivideTwoIntegers {

    @Test
    public void test() {
        int divide = divide(7, 3);
        System.out.println(divide);
    }

    @Test
    public void test2() {
        int divide = divide(-2147483648, -1);
        System.out.println(divide);


    }

    @Test
    public void testNo() {
        // -2147483648
        System.out.println(Integer.MIN_VALUE);
        System.out.println((long) Integer.MIN_VALUE);

        // -2147483648
        // 2147483648

        //  public static int abs(int a) {
        //        return (a < 0) ? -a : a;
        //    }
        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(Math.abs((long) Integer.MIN_VALUE));
    }

    /**
     * 除法到减法的转化
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return quotient 商
     */
    public int divide(int dividend, int divisor) {
        long y = Math.abs((long) dividend);
        long x = Math.abs((long) divisor);
        long ans = 0L;
        while (y >= x) {
            long temp = y;
            long carry = 1;
            // 减去除数倍增直至小于0
            while (temp - (x << carry) > 0) {
                carry++;
            }

            y -= (x << (carry - 1));
            // 结果添加
            ans += (1 << (carry - 1));
        }

        // 两者符号不同就异号
        if ((dividend > 0) ^ (divisor > 0)) {
            ans = -ans;
        }

        if (ans >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (ans <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        // down cast
        return (int) ans;
    }


}
