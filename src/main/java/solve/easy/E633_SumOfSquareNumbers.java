package solve.easy;

import org.junit.Test;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。
 * <p>
 * 示例1:
 * 输入: 5
 * 输出: True
 * 解释: 1 * 1 + 2 * 2 = 5
 *  
 * 示例2:
 * 输入: 3
 * 输出: False
 *
 * @author 99497
 */
public class E633_SumOfSquareNumbers {

    @Test
    public void test1() {
        System.out.println(judgeSquareSum(5));
        System.out.println(judgeSquareSum(3));
    }

    /**
     * 常规方法
     * <p>
     * 45.48%
     */
    public boolean judgeSquareSum(int c) {
        int s = (int) Math.sqrt(c);
        for (int i = 1; i <= s; i++) {
            double r = Math.sqrt(c - i * i);
            int temp = (int) r;
            if (r == temp) {
                return true;
            }
        }
        return s == 0;
    }

}