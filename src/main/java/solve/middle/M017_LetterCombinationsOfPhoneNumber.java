package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话号码的字母组合
 * <p>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * @author wm
 */
public class M017_LetterCombinationsOfPhoneNumber {

    private String s1 = "23";
    private String s2 = "234";

    @Test
    public void test() {
        List<String> strings = letterCombinations(s1);
        System.out.println(strings);
    }

    @Test
    public void test2() {
        List<String> strings = letterCombinations(s2);
        System.out.println(strings);
    }

    /**
     * 初始化映射关系
     */
    private Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    /**
     * 回溯法 -- 78.85%
     * <p>
     * time: O(3^N×4^M)
     * space: O(3^N×4^M)
     * <p>
     * N 是输入数字中对应 3 个字母的数目（2，3，4，5，6，8)
     * M 是输入数字中对应 4 个字母的数目 (7，9）
     *
     * @param digits "数字组合字符串"
     * @return "符合条件的字符串列表"
     */
    public List<String> letterCombinations(String digits) {
        List<String> output = new ArrayList<>();

        if (digits.length() != 0) {
            backtrack("", digits, output);
        }
        return output;
    }

    /**
     * 回溯函数
     *
     * @param combination 字母组合
     * @param nextDigits  下一位数字
     */
    public void backtrack(String combination, String nextDigits, List<String> output) {
        // 边界条件
        if (nextDigits.length() == 0) {
            output.add(combination);
        } else {
            // 迭代map中下一个可用数字的所有字母
            String digit = nextDigits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                // 将当前字母附加到组合并继续下一个数字
                backtrack(combination + letter, nextDigits.substring(1), output);
            }
        }
    }


}
