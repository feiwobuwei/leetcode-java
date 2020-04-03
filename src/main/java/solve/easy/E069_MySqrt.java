package solve.easy;

/**
 * 实现平方功能
 * 返回类型是整数，结果只保留整数的部分，小数部分将被舍去
 *
 * @author minwei
 */
public class E069_MySqrt {

    /**
     * 二分法
     * <p>
     * time O(lgx)
     * space O(1)
     */
    private static int sqrt(int x) {

        // 均值不等式，Hn≤Gn≤An≤Qn，
        // 即调和平均数不超过几何平均数，几何平均数不超过算术平均数，算术平均数不超过平方平均数。

        // 一开始就把右指针限定范围缩小
        // 根据几何平均数不超过算术平均数(一个参数取1 一个参数取x)
        // 即可得 √x <= (x+1)/2 < x/2 + 1
        int left = 0, right = x / 2 + 1;
        while (left <= right) {
            // 设为long是防止下面的乘法结果溢出
            long mid = left + (right - left) / 2;
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                left = (int) (mid + 1);
            } else {
                right = (int) (mid - 1);
            }
        }
        return right;

    }

    /**
     * 牛顿法 x_n+1 = ( x_n + a / x_n) / 2
     */
    private static int sqrt2(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("参数不能小于0");
        } else if (x == 0) {
            return 0;
        }

        long res = x / 2 + 1;
        while (res * res > x) {
            res = (res + x / res) / 2;
        }
        return (int) res;
    }

    /**
     * 暴力法
     * time O(lgx)
     */
    private static int sqrt3(int x) {
        // 设为long是防止下面的乘法结果溢出
        for (long i = 1; i <= x; i++) {
            if (i * i > x) {
                return (int) (i - 1);
            } else if (i * i == x) {
                return (int) i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        System.out.println(sqrt(68));

        System.out.println("==================");

//        System.out.println(sqrt2(100));
//        System.out.println(sqrt2(8888));
        System.out.println(sqrt2((int) Math.pow(2, 16)));

//        System.out.println("执行次数" + count);

        System.out.println("==================");

        System.out.println(sqrt3(81));
    }

}
