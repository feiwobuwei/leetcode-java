package solve.easy;

import org.junit.Test;

/**
 * 颠倒给定的 32 位无符号整数的二进制位。
 * <p>
 * you need treat n as an unsigned value
 *
 * @author minwei
 */
public class E190_ReverseBits {

    @Test
    public void test() {
        // 00000010100101000001111010011100
        int i = 43261596;
        int res = reverseBits(i);
        // 00111001011110000010100101000000
        // 964176192
        System.out.println(res);
    }


    /**
     * 左右移动
     * <p>
     * 每次将原来的数字向左移动1位，把原来数字的末尾加到结果数字中
     * 不能判断到原来的数字为0就结束循环，必须循环32次。
     */
    public int reverseBits(int n) {
        int res = 0;

        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res += n & 1;
            n >>= 1;
        }
        return res;
    }
}
