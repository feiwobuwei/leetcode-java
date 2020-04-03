package solve.middle;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * 编写一个程序，找出第 n 个丑数。
 * <p>
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * <p>
 * 说明:
 * 1 是丑数。
 * n 不超过1690。
 *
 * @author wm
 * @see solve.easy.E263_UglyNumber
 */
public class M264_UglyNumber2 {

    @Test
    public void test() {
        // 12
        System.out.println(nthUglyNumber(10));
        // 1536
        System.out.println(nthUglyNumber(100));
    }

    @Test
    public void test2() {
        // 12
        System.out.println(nthUglyNumber2(10));
        // 1536
        System.out.println(nthUglyNumber2(100));
    }


    /**
     * 这种太慢了
     * time O(n*logn)
     */
    public int nthUglyNumber(int n) {
        int count = 1;
        int number = 1;

        while (count < n) {
            number++;
            if (isUgly(number)) {
                count++;
            }
        }
        return number;
    }

    public boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }

        while (num != 1) {
            if (num % 2 == 0) {
                num = num / 2;
            } else if (num % 3 == 0) {
                num = num / 3;
            } else if (num % 5 == 0) {
                num = num / 5;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * res[0] = 1
     * 然后用res[0]乘以{2,3,5}，放进最小堆中，从堆中取出堆顶，即为res[1]。
     * <p>
     * res[1] = 2
     * 然后用res[1]乘以{2,3,5}，放进最小堆中，从堆中取出堆顶，即为res[2]。
     * <p>
     * 循环到 n 即可。
     */
    public int nthUglyNumber2(int n) {
        int[] ele = new int[]{2, 3, 5};
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        long[] res = new long[n];
        res[0] = 1;

        for (int i = 0; i < res.length; i++) {
            for (int i1 : ele) {
                if (!priorityQueue.contains((res[i] * i1))) {
                    priorityQueue.add((res[i] * i1));
                }
            }

            if (i + 1 < res.length) {
                res[i + 1] = priorityQueue.poll();
            }
        }
        return (int) res[n - 1];

    }

    /**
     * 三指针法
     */
    public int nthUglyNumber3(int n) {

        // TODO
        return 0;
    }


}
