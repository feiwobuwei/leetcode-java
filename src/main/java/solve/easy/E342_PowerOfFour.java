package solve.easy;

import org.junit.Test;

/**
 * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
 * <p>
 * 示例 1:
 * 输入: 16
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: 5
 * 输出: false
 * <p>
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 *
 * @author minwei
 * @see E231_PowerOfTwo
 */
public class E342_PowerOfFour {

    @Test
    public void test() {
        // 11111111111111111111111111111100
        System.out.println(Integer.toBinaryString(-4));

        boolean powerOfFour = isPowerOfFour(-4);
        System.out.println(powerOfFour);
    }

    /**
     * 位运算 -- 100.00%
     * <p>
     * 先将2的幂和4的幂都筛选出来，再筛选出4的幂
     * 2的幂满足 (num & (num - 1)) == 0
     * 5的二进制表示是0101，0x55555555(十六进制)的二进制表示是01010101 01010101 01010101 01010101。
     * 如果是2的幂但不是4的幂，显然会有(num & 0x55555555) == 0。
     * 如果是4的幂，显然会有(num & 0x55555555) != 0。
     */
    public boolean isPowerOfFour(int num) {
        if ((num & (num - 1)) == 0) {
            return (num & 0x55555555) != 0;
        }
        return false;
    }
}
