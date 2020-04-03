package solve.easy;

/**
 * @author minwei
 */
public class E231_PowerOfTwo {


    /**
     * 位运算
     * <p>
     * time O(1)
     */
    public boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        } else {
            return (n & (n - 1)) == 0;
        }

    }

    /**
     * 迭代法
     * time O(lgn) 对数底为2
     */
    public boolean isPowerOfTwo2(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 2 == 0) {
            n /= 2;
        }

        return n == 1;

    }
}
