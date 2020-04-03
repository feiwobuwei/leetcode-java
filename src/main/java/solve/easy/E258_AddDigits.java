package solve.easy;

import org.junit.Test;

/**
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 * <p>
 * 示例:
 * 输入: 38
 * 输出: 2
 * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
 * <p>
 * 进阶:
 * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
 *
 * @author minwei
 */
public class E258_AddDigits {

    @Test
    public void test() {
        int i = addDigits(123);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = addDigits2(123);
        System.out.println(i);
    }


    /**
     * 循环求权值的和 -- 95.84%
     * <p>
     * time O(n)
     * space O(1)
     */
    public int addDigits(int num) {
        while (num >= 10) {
            String numStr = String.valueOf(num);
            num = 0;
            for (int i = 0; i < numStr.length(); i++) {
                num += numStr.charAt(i) - '0';
            }
        }
        return num;
    }

    /**
     * 数学规律
     * <p>
     * time O(1)
     */
    public int addDigits2(int num) {
        if (0 == num) {
            return 0;
        }
        int temp = num % 9;
        if (temp == 0) {
            return 9;
        } else {
            return temp;
        }
    }
}
