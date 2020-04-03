package solve.middle;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个算法来判断一个数是不是“快乐数”。
 * <p>
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 * 然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 * <p>
 * 示例: 
 * <p>
 * 输入: 19
 * 输出: true
 * 解释:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 *
 * @author minwei
 */
public class M202_HappyNumber {

    @Test
    public void test() {
        boolean happy = isHappy(19);
        System.out.println(happy);

        happy = isHappy(37);
        System.out.println(happy);
    }

    @Test
    public void test2() {
        boolean happy = isHappy2(19);
        System.out.println(happy);

        happy = isHappy2(37);
        System.out.println(happy);
    }

    @Test
    public void test3() {
        boolean happy = isHappy3(19);
        System.out.println(happy);

        happy = isHappy3(37);
        System.out.println(happy);
    }

    private Set<Integer> set = new HashSet<>();

    /**
     * 常规循环  -- 64.76%
     * <p>
     * 集合作备忘录
     */
    public boolean isHappy(int n) {
        if (n <= 0) {
            return false;
        }
        while (n != 1) {
            int num = next(n);
            // 已经出现循环
            if (set.contains(num)) {
                return false;
            }
            set.add(n);
            n = num;
        }
        // 可以退出循环 则true
        return true;
    }

    private int next(int n) {
        int result = 0;
        while (n > 0) {
            int num = n % 10;
            result += num * num;
            n /= 10;
        }
        return result;
    }

    /*
    不是快乐数的数称为不快乐数（unhappy number），
    所有不快乐数的数位平方和计算，最後都会进入 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 的循环中。
     */

    /**
     * 根据规律 -- 35.63%
     * <p>
     * time O(lgn)
     * space O(1)
     */
    public boolean isHappy2(int n) {
        Integer[] nums = new Integer[]{4, 16, 37, 58, 89, 145, 42, 20};
        HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(nums));
        while (n != 1) {
            int num = next(n);
            if (hashSet.contains(num)) {
                return false;
            }
            n = num;
        }
        // 可以退出循环 则true
        return true;
    }

    /**
     * 递归 --
     */
    public boolean isHappy3(int n) {
        if (n == 1) {
            return true;
        }
        if ((n == 4) || (n == 16) || (n == 37) || (n == 58) ||
                (n == 89) || (n == 145) || (n == 42) || (n == 20)) {
            return false;
        }
        int sum = 0;

        do {
            int remainder = n % 10;
            sum += remainder * remainder;
            n = n / 10;
        } while (n > 0);

        return isHappy3(sum);
    }

}
