package solve.easy;

import org.junit.Test;

/**
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。0 ≤ x, y < 2^31.
 *
 * @author minwei
 * @see E191_NumberOf1Bits
 */
public class E461_HammingDistance {

    @Test
    public void test() {
        System.out.println(hammingDistance(1, 4));
        // 65  // 0000000000000000000000001000 0001
        // -3  // 1111111111111111111111111111 1101
        System.out.println(hammingDistance(65, -3));
    }

    @Test
    public void test2() {
        System.out.println(hammingDistance2(1, 4));
        // 65  // 0000000000000000000000001000 0001
        // -3  // 1111111111111111111111111111 1101
        System.out.println(hammingDistance2(65, -3));
    }


    /**
     * 暴力法 -- 逐位遍历
     */
    public int hammingDistance(int x, int y) {

        int sum = 0;

        for (int i = 0; i < 32; i++) {
            if ((x & 1) != (y & 1)) {
                sum++;
            }
            x = x >> 1;
            y = y >> 1;
        }

        return sum;
    }

    /**
     * 异或
     * 大幅优化空间复杂度
     */
    public int hammingDistance2(int x, int y) {
        // bitCount 数出整数二进制下 1 的个数
        // 1^0 = 1 ,0^1 =1 ,0^0 = 0 ,1^1 = 0
        return Integer.bitCount(x ^ y);
    }


}
