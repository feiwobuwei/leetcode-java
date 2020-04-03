package solve.easy;

/**
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 *
 * @author minwei
 */
public class E371_SumOfTwoIntegers {

    public static void main(String[] args) {
        int sum = getSum(63, 1);
        System.out.println(sum);
    }

    /**
     * 异或 为加
     * 与   为取进位
     */
    public static int getSum(int a, int b) {
        // 进位
        int carry;
        int result;
        int temp;

        result = a ^ b;
        carry = a & b;

        while (carry != 0) {
            carry = carry << 1;
            temp = result;
            result ^= carry;
            carry &= temp;
        }
        return result;
    }
}
