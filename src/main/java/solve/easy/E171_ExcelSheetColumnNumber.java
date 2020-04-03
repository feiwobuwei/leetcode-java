package solve.easy;

import org.junit.Test;

/**
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 * @author minwei
 * @see E168_ExcelSheetColumnTitle
 */
public class E171_ExcelSheetColumnNumber {

    @Test
    public void test() {
        int res = titleToNumber("AZ");
        System.out.println(res);
    }

    public int titleToNumber(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - 'A' + 1;
            ans = ans * 26 + num;
        }
        return ans;
    }


}
