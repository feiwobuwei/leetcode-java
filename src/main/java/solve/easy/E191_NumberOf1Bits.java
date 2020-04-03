package solve.easy;

import org.junit.Test;

/**
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 * <p>
 * you need to treat n as an unsigned value
 *
 * @author minwei
 * @see E461_HammingDistance
 */
public class E191_NumberOf1Bits {


    @Test
    public void test() {
        // 0000000000000000000000001000 0000
        System.out.println(hammingWeight(64));
        // 0000000000000000000000001000 0001
        System.out.println(hammingWeight(65));
        // 11111111111111111111111111111101
        System.out.println(hammingWeight(-3));

    }

    @Test
    public void test2() {
        // 0000000000000000000000001000 0000
        System.out.println(hammingWeight2(64));
        // 0000000000000000000000001000 0001
        System.out.println(hammingWeight2(65));
        // 11111111111111111111111111111101
        System.out.println(hammingWeight2(-3));
    }

    /**
     * 逐位遍历
     * <p>
     * time O(1)
     * space O(1)
     */
    public int hammingWeight(int n) {
        int bit = 0;
        int result = 1;
        for (int i = 0; i < 32; i++) {
            // 仅2个1与运算返回1
            if ((n & result) != 0) {
                bit++;
            }
            result <<= 1;
        }
        return bit;
    }

    /**
     * 不断把数字最后一个1反转，并把结果加一。
     * 当数字变成 0 的时候此时返回答案。
     * <p>
     * time O(1)
     * space O(1)
     */
    public int hammingWeight2(int n) {
        int sum = 0;
        // 可以提前结束 不用遍历32个位
        while (n != 0) {
            sum++;
            // 把最后一个1变成 0
            n &= (n - 1);
        }
        return sum;
    }


}
