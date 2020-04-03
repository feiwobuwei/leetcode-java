package solve.hard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 串联所有单词的子串
 * <p>
 * 给定一个字符串 s 和一些长度相同的单词 words。
 * 找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *  
 * 示例 1：
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * <p>
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * <p>
 * 示例 2：
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 *
 * @author minwei
 */
public class H030_SubstringWithConcatenationOfAllWords {

    private String s = "barfoothefoobarman";
    private String[] words = {"foo", "bar"};

    @Test
    public void test() {
        List<Integer> substring = findSubstring(s, words);
        System.out.println(substring);
    }

    /**
     * 哈希表。
     * <p>
     * 时间复杂度是O(m * len)，其中m为字符串s的长度，len为words数组中的单词长度。
     * 空间复杂度是O(n)。
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int n;
        if (null == words || (n = words.length) == 0) {
            return result;
        }
        int len = words[0].length();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(words[i])) {
                map.put(words[i], map.get(words[i]) + 1);
            } else {
                map.put(words[i], 1);
            }
        }
        for (int i = 0; i < s.length() - n * len + 1; i++) {
            Map<String, Integer> tmpMap = new HashMap<>(map);
            int j = 0;
            for (; j < len * n; j += len) {
                String subString = s.substring(i + j, i + j + len);
                if (!tmpMap.containsKey(subString)) {
                    break;
                } else {
                    tmpMap.put(subString, tmpMap.get(subString) - 1);
                    if (tmpMap.get(subString) == 0) {
                        tmpMap.remove(subString);
                    }
                }
            }
            if (j == len * n) {
                result.add(i);
            }
        }
        return result;
    }
}
