package solve.easy;

import org.junit.Test;

/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc" 
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 *
 * @author minwei
 */
public class E557_ReverseWordsInString3 {

    @Test
    public void test() {
        String s = "Let's take LeetCode contest";
        String s1 = reverseWords(s);
        System.out.println(s1);
    }

    public String reverseWords(String s) {
        // 最终容器
        StringBuilder result = new StringBuilder();
        // 临时容器
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                result.append(temp.reverse());
                result.append(c);
                temp = new StringBuilder();
            } else {
                temp.append(c);
            }
        }
        result.append(temp.reverse());
        return result.toString();
    }
}
