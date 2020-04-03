package solve.easy;

import org.junit.Test;

/**
 * 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
 * 给定一个数字 n，找出可形成完整阶梯行的总行数。
 * n 是一个非负整数，并且在32位有符号整型的范围内。
 * 示例 1:
 * n = 5
 * 硬币可排列成以下几行:
 * ¤
 * ¤ ¤
 * ¤ ¤
 * <p>
 * 因为第三行不完整，所以返回2.
 *
 * @author minwei
 */
public class E441_ArrangingCoins {

    int n = 166;

    @Test
    public void test() {
        int i = arrangeCoins(n);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = arrangeCoins2(n);
        System.out.println(i);
    }

    @Test
    public void test3() {
        int i = arrangeCoins3(n);
        System.out.println(i);
    }

    /**
     * 数学公式法 -- 100.00%
     */
    public int arrangeCoins(int n) {
        return (int) ((-1 + Math.sqrt(1 + 8 * (long) n)) / 2);
    }

    /**
     * 暴力法 -- 38.31%%
     */
    public int arrangeCoins2(int n) {
        int i = 1;
        while (n > 0) {
            n -= i++;
            if (n < i) {
                break;
            }
        }
        return --i;

    }

    /**
     * 二分法 -- 85.62%%%
     */
    public int arrangeCoins3(int n) {

        if (n <= 1) {
            return n;
        }

        long lo = 0;
        long hi = n;
        while (lo < hi) {
            long mid = (hi - lo) / 2 + lo;

            if (sum(mid) > n) {
                if (sum(mid - 1) <= n) {
                    return (int) mid - 1;
                } else {
                    hi = mid - 1;
                }
            } else {
                if (sum(mid + 1) > n) {
                    return (int) mid;
                } else {
                    lo = mid + 1;
                }

            }
        }
        // 正常流程不会走到这里
        return -1;

    }

    public long sum(long n) {
        return n * (n + 1) >> 1;
    }

}
