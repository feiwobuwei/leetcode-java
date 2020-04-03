package solve.easy;

import org.junit.Test;

/**
 * 题目要求: 假设我们的环境只能存储得下 32 位的有符号整数，
 * 则其数值范围为 [−2^31,  2^31 − 1]。
 * 如果反转后整数溢出那么就返回 0
 *
 * @author wm
 */
public class E007_ReverseInteger {

    private static int reverse(int x) {
        // long数据类型是64位有符号的Java原始数据类型。当对整数的计算结果可能超出int数据类型的范围时使用。
        // long数据类型范围是-9,223,372,036,854,775,808至9,223,372,036,854,775,807(-2^63至2^63-1)。
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
                return 0;
            }
        }

        // 向下转down cast
        return (int) res;

    }

    @Test
    public void testReverse() {

        int result = reverse(-321);
        System.out.println(result);

        result = reverse(120);
        System.out.println(result);

    }


}
