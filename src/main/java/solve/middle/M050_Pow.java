package solve.middle;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * @author wm
 */
public class M050_Pow {

    public static void main(String[] args) {
        double result = myPow(2.00, 10);
        System.out.println(result);

        double result2 = myPow2(2.00, -2);
        System.out.println(result2);

        double result3 = myPow3(2.00, 0);
        System.out.println(result3);
    }


    /**
     * 暴力法
     * <p>
     * time O(n)
     */
    private static double myPow(double x, int n) {

        if (x == 0) {
            return 0.0;
        }

        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double tmp = 1;
        for (int i = 1; i <= N; i++) {
            tmp = tmp * x;
        }
        return tmp;
    }

    /**
     * 快速幂算法（递归）
     * <p>
     * time O(logn)
     */
    private static double myPow2(double x, int n) {
        if (x == 0) {
            return 0.0;
        }

        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
    }

    private static double fastPow(double x, long n) {
        // 边界条件
        if (n == 0) {
            return 1.0;
        }

        // n/2 是向下取整
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }


    /**
     * 快速幂算法（循环）
     * <p>
     * time O(logn)
     * space O(1)
     *
     * 例如 n = 13，则 n 的二进制表示为 1101, 那么 m 的 13 次方可以拆解为:
     * m^1101 = m^0001 * m^0100 * m^1000。 相当于1个m乘以4个m乘以8个m
     * 通过 & 1和 >>1 来逐位读取 1101，为1时将该位代表的乘数累乘到最终结果sum。
     */
    public static double myPow3(double x, int n) {

        if (x == 0) {
            return 0.0;
        }

        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double sum = 1.0;
        double tmp = x;
        while (N != 0) {
            if ((N & 1) == 1) {
                sum *= tmp;
            }
            // 每次循环的tmp相当于进制从右往左每一位的权重
            // 例如二进制 100 最左边的1代表4(2^2)
            tmp *= tmp;
            // 右移一位
            N = N >> 1;
        }

        return sum;
    }


}
