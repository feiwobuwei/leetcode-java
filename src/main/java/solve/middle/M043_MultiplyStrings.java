package solve.middle;

import org.junit.Test;

/**
 * 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，
 * 它们的乘积也表示为字符串形式。
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author minwei
 */
public class M043_MultiplyStrings {

    @Test
    public void test() {
        String x = "3338429042340042304302404";
        String y = "12303231";
        System.out.println(multiply(x, y));
    }

    /**
     * 小学乘法的代码实现 -- 96.28%
     * <p>
     * time O(mn)
     * space O(m+n  )
     */
    public String multiply(String num1, String num2) {

        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        // 1.把两个大整数用数组逆序存储，数组长度等于两整数长度之和
        int lengthA = num1.length();
        int lengthB = num2.length();

        int[] arrayA = new int[lengthA];
        for (int i = 0; i < lengthA; i++) {
            arrayA[i] = num1.charAt(lengthA - 1 - i) - '0';
        }

        int[] arrayB = new int[lengthB];
        for (int i = 0; i < lengthB; i++) {
            arrayB[i] = num2.charAt(lengthB - 1 - i) - '0';
        }

        // 2.构建result数组，数组长度等于两整数长度之和(乘积不可能超过)
        int[] result = new int[lengthA + lengthB];

        // 3.嵌套循环，整数B的每一位依次和整数A的所有数位相乘，并把结果累加
        for (int i = 0; i < lengthB; i++) {
            for (int j = 0; j < lengthA; j++) {
                // 整数B的某一位和整数A的某一位相乘
                result[i + j] += arrayB[i] * arrayA[j];
                // 如果result某一位大于10，则进位，进位数量是该位除以10的商
                if (result[i + j] >= 10) {
                    result[i + j + 1] += result[i + j] / 10;
                    result[i + j] = result[i + j] % 10;
                }
            }
        }

        // 4.把result数组再次逆序并转成String
        StringBuilder sb = new StringBuilder();

        // 是否找到大整数的最高有效位
        boolean findFirst = false;

        for (int i = result.length - 1; i >= 0; i--) {
            if (!findFirst) {
                if (result[i] == 0) {
                    continue;
                }
                findFirst = true;
            }
            sb.append(result[i]);
        }
        return sb.toString();
    }


    /*
      TODO 待研究问题1  复杂度O(n^1.59)
      TODO 待研究问题2  快速傅里叶变换
     */
}
