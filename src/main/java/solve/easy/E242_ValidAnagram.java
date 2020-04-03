package solve.easy;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * <p>
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * @author minwei
 */
public class E242_ValidAnagram {


    private String s = "anagram";
    private String t = "nagaram";

    @Test
    public void test() {
        System.out.println(isAnagram(s, t));
    }

    @Test
    public void test2() {
        System.out.println(isAnagram2(s, t));
    }

    @Test
    public void test3() {
        System.out.println(isAnagram3(s, t));
    }

    /**
     * 排序
     * <p>
     * time O(nlgn)
     * space O(1)
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] str1 = s.toCharArray();

        System.out.println("str1: " + Arrays.toString(str1));

        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);

    }


    /**
     * 用数组进行统计
     * 查找更快 但不适用于unicode情况
     * <p>
     * time O(n)
     * space O(1)
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        // s中每出现一次 +1
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        // t中每出现一次 -1
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 哈希表 适用于unicode
     * <p>
     * time O(n)
     * space O(1)
     */
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        // s中每出现一次 +1
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        // t中每出现一次 -1 出现不同的字符直接短路
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) {
                map.remove(c);
            }
        }
        // 如果最终为空 就是true
        return map.size() == 0;
    }
}
