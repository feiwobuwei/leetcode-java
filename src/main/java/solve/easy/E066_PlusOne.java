package solve.easy;

import java.util.Arrays;

/**
 * 加一
 *
 * <p>
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 隐藏坑很多啊 主要考察编程全面性
 *
 * @author minwei
 */
public class E066_PlusOne {

    /**
     * case1:1011 1012
     * case2:1099 1100
     * case3:9999 10000
     * 时间复杂度O(n) 空间复杂度O(1)
     *
     * @param digits 待处理数组
     * @return 加1
     */
    private static int[] plusOne(int[] digits) {

        if (digits == null || digits.length == 0) {
            return null;
        }

        int[] result = new int[digits.length + 1];
        // 末位加1
        digits[digits.length - 1]++;

        // 反向遍历
        for (int i = digits.length - 1; i >= 0; i--) {
            result[i + 1] += digits[i];
            result[i] += result[i + 1] / 10;
            result[i + 1] %= 10;
        }

        // 首位如果是0,就截取掉,如果不是比如9999。如果最后是1,就保留
        if (result[0] == 0) {
            return Arrays.copyOfRange(result, 1, result.length);
        } else {
            return result;
        }
    }

    public static void print(int[] result) {
        System.out.print("[");
        for (int i = 0; i < result.length; i++) {
            if (i == result.length - 1) {
                System.out.print(result[i]);
            } else {
                System.out.print(result[i] + ",");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] test = {1, 0, 1, 1};
        int[] result = plusOne(test);
        print(result);

        System.out.println("=========================");
        int[] test2 = {1, 0, 9, 9};
        int[] result2 = plusOne(test2);
        print(result2);

        System.out.println("=========================");
        int[] test3 = {9, 9, 9, 9};
        int[] result3 = plusOne(test3);
        print(result3);

    }
}
