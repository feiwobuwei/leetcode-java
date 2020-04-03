package solve.middle;


import org.junit.Test;

/**
 * 整数转罗马数字
 *
 * @author minwei
 * @see solve.easy.E013_RomanToInteger
 */
public class M012_IntegerToRoman {

    @Test
    public void test() {
        int i = 1994;
        String s1 = intToRoman(i);
        System.out.println(s1);

        int j = 58;
        String s2 = intToRoman(j);
        System.out.println(s2);

    }

    private final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5,
            4, 1};

    private final String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L", "XL",
            "X", "IX", "V", "IV", "I"};

    /**
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     */
    public String intToRoman(int num) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                sb.append(symbol[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }


}
