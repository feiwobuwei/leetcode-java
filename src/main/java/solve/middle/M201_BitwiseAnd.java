package solve.middle;

import org.junit.Test;

/**
 * 范围内 按位与
 *
 * @author minwei
 */
public class M201_BitwiseAnd {


    @Test
    public void test() {
        int i = rangeBitwiseAnd(5, 7);
        System.out.println(i);
    }

    /**
     * 规律
     * (1) 如果m与n不在同一个2的整数次幂区间(例如[4,8]或[16,32])内 则结果必然是0
     * (2) 如果m与n在在同一个2的整数次幂区间内,则结果为该区间左边的整数次幂
     *
     * @param m 起点
     * @param n 终点
     * @return 按位与结果
     */
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) {
            return 0;
        }
        int temp = 1;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            temp <<= 1;
        }
        // 如果不在同一个区间 那么m就是0 返回的也是0 否则就是2的整数次幂
        return m * temp;
    }
}


