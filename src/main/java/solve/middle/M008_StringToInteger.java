package solve.middle;

import org.junit.Test;

/**
 * 字符串转换整数 (atoi)
 *
 * @author wm
 */
public class M008_StringToInteger {

    @Test
    public void test() {
        String s = "  -12a3";
        int result = myAtoi(s);
        System.out.println(result);
    }


    @Test
    public void test2() {
        String s = "42";
        int result = myAtoi(s);
        System.out.println(result);
    }

    /**
     * 编程完备性
     * <p>
     * time:O(n)
     * space:O(1)
     */
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int sign = 1;
        int start = 0;
        // 处理越界问题(down cast)
        long res = 0;

        // 去除头部空格
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                start = i;
                break;
            }
        }

        // 处理符号位
        char firstChar = str.charAt(start);

        if (firstChar == '+') {
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }

        for (int i = start; i < str.length(); i++) {
            // 如果出现不是数字的字符 直接短路
            if (!Character.isDigit(str.charAt(i))) {
                return (int) res * sign;
            }
            // 隐式自动变换
            res = res * 10 + str.charAt(i) - '0';
            if (sign == 1 && res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && res > Integer.MAX_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        return (int) res * sign;
    }


}
