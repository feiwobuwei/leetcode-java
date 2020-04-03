package solve.easy;

import org.junit.Test;

/**
 * 猜数字大小
 * <p>
 * 我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
 * 每次你猜错了，我会告诉你这个数字是大了还是小了。
 * 你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：
 * <p>
 * 输入: n = 10, pick = 6
 * 输出: 6
 *
 * @author minwei
 */
public class E374_GuessNumberHigherOrLower {

    @Test
    public void test() {
        int i = guessNumber(6);
        System.out.println(i);
    }

    private int pick = 6;

    /**
     * 暴力法
     * <p>
     * time O(n)
     * space O(1)
     */
    public int guessNumber(int n) {
        for (int i = 1; i < n; i++) {
            if (guess(i) == pick) {
                return i;
            }
        }
        return n;
    }

    /**
     * 二分法
     * <p>
     * time O(lgn) -- 底为2
     * space O(1)
     */
    public int guessNumber2(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int res = guess(mid);
            if (res == 0) {
                return mid;
            } else if (res < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // 仅仅是为了保证有返回值
        return -1;
    }

    private int guess(int i) {
        // 底层  return (i >> 31) | (-i >>> 31);
        // Returns the signum function of the specified {@code int} value.  (The
        // return value is -1 if the specified value is negative; 0 if the
        // specified value is zero; and 1 if the specified value is positive.)
        return Integer.signum(i);
    }

    /**
     * 三分查找 -- 三分查找比二分查找效果要更差
     * <p>
     * time O(lgn) -- 底为3
     * space O(1)
     */
    public int guessNumber3(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid1 = low + (high - low) / 3;
            int mid2 = high - (high - low) / 3;
            int res1 = guess(mid1);
            int res2 = guess(mid2);
            if (res1 == 0) {
                return mid1;
            }
            if (res2 == 0) {
                return mid2;
            } else if (res1 < 0) {
                high = mid1 - 1;
            } else if (res2 > 0) {
                low = mid2 + 1;
            } else {
                low = mid1 + 1;
                high = mid2 - 1;
            }
        }
        return -1;
    }

}
