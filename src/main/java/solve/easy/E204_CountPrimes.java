package solve.easy;

/**
 * 统计小于n的质数
 *
 * @author minwei
 */
public class E204_CountPrimes {

    public static void main(String[] args) {

        int i = countPrimes(64);
        System.out.println(i);

        int j = countPrimes2(10);
        System.out.println(j);

        int k = countPrimes3(64);
        System.out.println(k);
    }


    /**
     * 教材解法 暴力法 -- 提交超时
     * time O(n^2)
     * space O(1)
     *
     * @param n 10
     * @return 小于10的质数 2 3 5 7 所以返回4
     */
    public static int countPrimes(int n) {

        int count = 0;
        for (int i = 2; i < n; i++) {
            boolean sign = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    sign = false;
                    break;
                }
            }
            if (sign) {
                count++;
            }
        }
        return count;

    }

    /**
     * 暴力法的优化 - 5.04%
     * time: O(n * √n) = O(n^1.5)
     * space: O(1)
     * <p>
     * (1)对正整数 n ，如果用 2 到 √n 之间(包含边界)的所有整数去除，均无法整除，则 n 为质数。
     * 可以用反证法证明
     * (2)此外一切非 2 偶数一定不可能为质数
     */
    public static int countPrimes2(int n) {
        if (n < 3) {
            return 0;
        }
        // 从3开始,初始值为1
        int count = 1;
        for (int i = 3; i < n; i++) {
            // 先排除掉>2的偶数
            if ((i & 1) == 0) {
                // 直接进入下次循环
                continue;
            }
            boolean sign = true;
            for (int j = 3; j * j <= i; j += 2) {
                if (i % j == 0) {
                    sign = false;
                    break;
                }
            }
            if (sign) {
                count++;
            }
        }
        return count;

    }

    /**
     * 厄拉多塞筛法 -- 94.69%
     * <p>
     * <p>
     * time O(NlglgN) 底是e; 证明需要用欧拉定理(与素数的倒数和有关)
     * space O(N)
     */
    public static int countPrimes3(int n) {
        int count = 0;
        boolean[] signs = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!signs[i]) {
                count++;
                // 2i 3i 4i 5i 6i 7i ...
                for (int j = i + i; j < n; j += i) {
                    // 排除不是质数的数
                    signs[j] = true;
                }
            }
        }
        return count;
    }

}
