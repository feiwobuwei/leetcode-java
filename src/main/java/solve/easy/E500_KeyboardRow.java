package solve.easy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。
 * <p>
 * 示例：
 * 输入: ["Hello", "Alaska", "Dad", "Peace"]
 * 输出: ["Alaska", "Dad"]
 *
 * @author 99497
 */
public class E500_KeyboardRow {

    @Test
    public void test() {
        String[] strings = {"Hello", "Alaska", "Dad", "Peace"};
        String[] words = findWords(strings);
        System.out.println(Arrays.toString(words));
    }

    /*
        键盘上行 qwertyuiop
        键盘中行 asdfghjkl
        键盘下行 zxcvbnm
     */

    public String[] findWords(String[] words) {
        if (words == null || words.length == 0) {
            return new String[0];
        }
        // 用长度为26的数组标识每个字母所在的行号
        int[] map = {2, 3, 3, 2, 1, 2, 2, 2, 1, 2, 2, 2, 3, 3, 1, 1, 1, 1, 2, 1, 1, 3, 1, 3, 1, 3};
        List<String> list = new ArrayList<>();
        for (String word : words) {
            // 先把字母全部转为大写
            String tempWord = word.toUpperCase();
            int temp = map[tempWord.charAt(0) - 65];
            boolean flag = true;
            // 通过与首字母比较行号确定是否在同一行
            for (int i = 1; i < tempWord.length(); i++) {
                if (temp != map[tempWord.charAt(i) - 65]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                list.add(word);
            }
        }
        return list.toArray(new String[0]);


    }


}
