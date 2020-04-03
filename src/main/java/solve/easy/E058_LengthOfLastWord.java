package solve.easy;

import org.junit.Test;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * <p>
 * 如果不存在最后一个单词，请返回 0 。
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 * 示例:
 * 输入: "Hello World"
 * 输出: 5
 *
 * @author minwei
 */
public class E058_LengthOfLastWord {

    @Test
    public void test() {
        int helloWorld = lengthOfLastWord("Hello World");
        System.out.println(helloWorld);
    }

    public int lengthOfLastWord(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int i = n - 1;
        while (s.charAt(i) == ' ') {
            i--;
            if (i < 0) {
                return 0;
            }
        }
        int start = i;
        for (; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                break;
            }
        }
        return start - i;
    }
}
