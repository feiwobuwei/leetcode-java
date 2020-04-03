package solve.middle;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 分数到循环小数
 * <p>
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * <p>
 * 输入: numerator = 1, denominator = 2
 * 输出: "0.5"
 * <p>
 * 输入: numerator = 2, denominator = 3
 * 输出: "0.(6)"
 *
 * @author minwei
 */
public class M166_FractionToRecurringDecimal {

    @Test
    public void test() {
        String s = fractionToDecimal(1, 6);
        System.out.println(s);
    }

    @Test
    public void test2() {
        String s1 = fractionToDecimal(3, 7);
        System.out.println(s1);
    }

    /**
     * 长除法 -- 编程完备性
     *
     * @param numerator   分子
     * @param denominator 分母
     * @return 循环小数
     */
    public String fractionToDecimal(int numerator, int denominator) {
        // int范围是[-2147483648, 2147483647]，如果将-2147483648转换成正数，会产生数据越界问题，
        // 重载方法，用long型数据来处理。
        return fractionToDecimal((long) numerator, (long) denominator);
    }

    private String fractionToDecimal(long numerator, long denominator) {

        // 用于拼接结果 整数部分
        StringBuilder integer = new StringBuilder();
        // 最终结果是否是正数
        boolean isPositive = true;

        // 都调整为正数
        if (numerator < 0) {
            isPositive = false;
            numerator *= -1;
        }

        if (denominator < 0) {
            isPositive = !isPositive;
            denominator *= -1;
        }

        // 分子不是0 且结果是负数 先加负号
        if (!isPositive && (numerator != 0)) {
            integer.append("-");
        }

        // 第一次除法 获取商
        long quotient = numerator / denominator;
        integer.append(quotient);

        // 余数
        long remainder = numerator % denominator;

        // 如果可以整除 已经OK
        if (0 == remainder) {
            return integer.toString();
        }

        // 不能整除 开始产生小数
        integer.append(".");

        // 用于拼接结果 非循环小数部分
        StringBuilder decimal = new StringBuilder();

        // 键代表的是被除数，值代表的是余数
        HashMap<Long, Long> hashMap = new HashMap<>(8);

        // 存储 小数循环体
        HashSet<Long> hashSet = new HashSet<>();

        // 用于拼接 小数循环体
        StringBuilder loop = new StringBuilder();

        while (remainder != 0) {
            // 小学除法 每次借一位 添加商 3/7 -> 30/7 -> 添加商4 余数2(2就是下次的被除数)
            decimal.append(remainder * 10 / denominator);
            hashMap.put(remainder, remainder * 10 % denominator);
            // 每次把余数作为新的被除数
            remainder =  hashMap.get(remainder);

            // 目前上面是 非循环小数部分

            // 如果出现了重复的被除数 说明小数开始循环
            if (hashMap.containsKey(remainder)) {
                // hashSet来存储该循环体
                if (hashSet.contains(remainder)) {
                    break;
                } else {
                    loop.append(remainder * 10 / denominator);
                    hashSet.add(remainder);
                }

            }
        }
        if (remainder != 0) {
            // 无限循环小数
            return integer.toString() + decimal.substring(0, decimal.indexOf(loop.toString()))
                    + "(" + loop + ")";
        } else {
            // 有限小数
            return integer.append(decimal).toString();
        }
    }

}
