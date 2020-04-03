package solve.easy;

/**
 * @author minwei
 */
public class E326_PowerOfThree {


    public static void main(String[] args) {

        boolean powerOfThree = isPowerOfThree(243);
        System.out.println(powerOfThree);


        boolean powerOfThree2 = isPowerOfThree2(243);
        System.out.println(powerOfThree2);

    }

    /**
     * 对数换底公式
     * <p>
     * time O(lgn) 对数底为3
     */
    public static boolean isPowerOfThree(int n) {
        // 计算结果是正整数 就返回true
        double result = (Math.log10(n) / Math.log10(3));
        return result % 1 == 0;

    }

    /**
     * 迭代法
     * 可以类比二进制想象为三进制
     * <p>
     * time O(lgn) 对数底为3
     */
    public static boolean isPowerOfThree2(int n) {
        if (n < 1) {
            return false;
        }

        // 相当于不断将首位右移
        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

}
