package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * <p>
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 * @author minwei
 */
public class M151_ReverseWordsInString {

    @Test
    public void test() {
        String s = "  hello world!  ";
        String s1 = reverseWords(s);
        System.out.println(s1);
    }

    @Test
    public void test2() {
        String s = "a good   example";
        String s1 = reverseWords(s);
        System.out.println(s1);
    }

    /**
     * 双指针 -- 99.96%
     * <p>
     * time O(S) S为字符串长度
     * space O(S)
     */
    public String reverseWords(String s) {
        if (s == null) {
            return null;
        }
        if (s.trim().length() == 0) {
            return "";
        }

        int start;
        int end;
        List<String> list = new ArrayList<>();
        char[] chars = s.toCharArray();

        int k = 0;
        while (k < chars.length) {
            if (chars[k] == ' ') {
                k++;
            } else {
                start = end = k;
                while (end < chars.length && chars[end] != ' ') {
                    end++;
                }
                list.add(s.substring(start, end));
                k = end;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i)).append(' ');
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
