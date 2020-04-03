package solve.easy;

import org.junit.Test;

/**
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 * <p>
 * 说明：不要使用任何内置的库函数，如  sqrt。
 * <p>
 * 示例 1：
 * 输入：16
 * 输出：True
 * <p>
 * 示例 2：
 * 输入：14
 * 输出：False
 *
 * @see solve.middle.M319_BulbSwithcer
 */
public class E367_ValidPerfectSquare {

    @Test
    public void test() {
        boolean perfectSquare2 = isPerfectSquare2(2147483647);
        System.out.println(perfectSquare2);
    }

    /**
     * 常规暴力法
     * <p>
     * 执行用时 :424 ms, 在所有 java 提交中击败了11.39%的用户
     */
    public boolean isPerfectSquare(int num) {
        if (num <= 0) {
            return false;
        }

        if (num == 1) {
            return true;
        }

        for (int i = 2; i <= num / 2; i++) {
            if (i * i == num) {
                return true;
            }
        }

        return false;
    }

    /**
     * 时间复杂度 O(√n)
     */
    public boolean isPerfectSquare2(int num) {
        // 防止 i*i 越界变成了负数 然后无限循环
        double i = 0;
        // 无限循环
        while (i * i < num) {
            i++;
        }
        return i * i == num;
    }


}
