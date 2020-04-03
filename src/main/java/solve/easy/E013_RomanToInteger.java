package solve.easy;

/**
 * 罗马字符串转整数
 *
 * @author wm
 * @see solve.middle.M012_IntegerToRoman
 */
public class E013_RomanToInteger {

    public static void main(String[] args) {

        String s1 = "LVIII";
        String s2 = "MCMXCIV";
        String s3 = "";

        System.out.println(romanToInt(s1));
        System.out.println(romanToInt(s2));
        System.out.println(romanToInt(s3));

    }

    /**
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 规律:左边的数字如果小于右边的数字 = 右 - 左
     * 正常情况就累加
     * 特殊值
     * IV=4 IX=9
     * XL=40,XC=90
     * CD=400,CM=900
     * <p>
     * time O(n) space O(1)
     *
     * @param s 待输入的字符串
     * @return 转换后的整数
     */
    private static int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = toNumber(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            // 如果右边的数字大于左边的数字
            if (toNumber(s.charAt(i)) > toNumber(s.charAt(i - 1))) {
                // 注意必须乘以2,因为从左边过来时,先把该字符对应的数字加入了进去,
                // 后来发现应该是减,所以为了复原应该减去2倍的该值
                res += toNumber(s.charAt(i)) - 2 * toNumber(s.charAt(i - 1));
            } else {
                res += toNumber(s.charAt(i));
            }
        }
        return res;
    }

    /**
     * 辅助转换函数
     *
     * @param c 单个字符
     * @return 返回对应代表的整数
     */
    private static int toNumber(char c) {
        int res = 0;
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return res;
        }
    }


}
