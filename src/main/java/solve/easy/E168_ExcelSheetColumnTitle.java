package solve.easy;

import org.junit.Test;

import java.util.HashMap;

/**
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * ...
 *
 * @author minwei
 * @see E171_ExcelSheetColumnNumber
 */
public class E168_ExcelSheetColumnTitle {

    @Test
    public void test() {
        String s = convertToTitle(26);
        System.out.println(s);

        String s1 = convertToTitle(28);
        System.out.println(s1);

        String s2 = convertToTitle(52);
        System.out.println(s2);
    }

    /**
     * 哈希表映射 -- 49.38%
     * <p>
     * 十进制转二十六进制
     * 但要求的是A-Z 对应1-26
     */
    public String convertToTitle(int n) {
        // 映射表
        HashMap<Integer, Character> hashMap = new HashMap<>(26);
        for (int i = 0; i < 26; i++) {
            hashMap.put(i, (char) ('A' + i));
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (n >= 1) {
            stringBuilder.append(hashMap.get((n - 1) % 26));
            n = (n - 1) / 26;
        }
        return stringBuilder.reverse().toString();
    }


}
