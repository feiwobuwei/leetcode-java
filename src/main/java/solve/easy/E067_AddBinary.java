package solve.easy;

import org.junit.Test;

/**
 * 二进制求和
 * <p>
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 * @author minwei
 */
public class E067_AddBinary {

    @Test
    public void test() {
        String a = "1010";
        String b = "1011";
        // 10101
        String s = addBinary(a, b);
        System.out.println(s);
    }

    @Test
    public void test2() {
        String a = "1010";
        String b = "111011";
        // 1000101
        String s = addBinary(a, b);
        System.out.println(s);
    }

    @Test
    public void test3() {
        String a = "100";
        String b = "110010";
        String s = addBinary(a, b);
        // 110110
        System.out.println(s);
    }

    @Test
    public void test4() {
        String a = "100";
        String b = "110010";
        String s = addBinary2(a, b);
        // 110110
        System.out.println(s);
    }

    /**
     * one-pass 96.34%
     * <p>
     * 另一种处理方式是在短的字符串前面补0 直到一样长
     * <p>
     * time O(max(m,n))
     * space O(1)
     */
    public String addBinary(String a, String b) {

        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return a;
        }

        StringBuilder result = new StringBuilder();
        String longer;
        String shorter;

        if (a.length() >= b.length()) {
            longer = a;
            shorter = b;
        } else {
            longer = b;
            shorter = a;
        }

        // 总是选择长度更短的来遍历
        int min = shorter.length();
        // 先把2个字符串都倒过来
        StringBuilder reverseA = new StringBuilder(a).reverse();
        StringBuilder reverseB = new StringBuilder(b).reverse();

        // 进位
        int carry = 0;
        for (int i = 0; i < min; i++) {
            // 本位的值
            int sum = reverseA.charAt(i) - '0' + reverseB.charAt(i) - '0' + carry;
            carry = sum / 2;
            sum = sum % 2;
            result.append(sum);
        }

        // 处理较长字符串之前截掉的部份
        for (int i = longer.length() - min - 1; i >= 0; i--) {
            // 本位的值
            int sum = longer.charAt(i) - '0' + carry;
            carry = sum / 2;
            sum = sum % 2;
            result.append(sum);
        }

        // 说明最高有进位
        if (carry == 1) {
            result.append(1);
        }

        return result.reverse().toString();
    }

    /**
     * 另一种处理方式是在短的字符串前面补0 直到一样长
     */
    public String addBinary2(String a, String b) {

        // TODO
        return null;
    }


}