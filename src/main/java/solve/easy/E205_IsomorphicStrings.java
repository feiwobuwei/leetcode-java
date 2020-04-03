package solve.easy;

import org.junit.Test;

import java.util.HashMap;

/**
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，
 * 但字符可以映射自己本身。
 * <p>
 * 示例 1:
 * 输入: s = "egg", t = "add"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: s = "paper", t = "title"
 * 输出: true
 * <p>
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 *
 * @author minwei
 */
public class E205_IsomorphicStrings {

    private String s1 = "paper";
    private String s2 = "title";

    @Test
    public void test() {
        boolean isomorphic = isIsomorphic(s1, s2);
        System.out.println(isomorphic);
    }

    @Test
    public void test2() {
        boolean isomorphic = isIsomorphic2(s1, s2);
        System.out.println(isomorphic);
    }

    /**
     * 两次哈希表
     * <p>
     * 题目要求:两个字符不能映射到同一个字符上，但字符可以映射自己本身。
     */
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> hashMap = new HashMap<>(16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hashMap.containsKey(c)) {
                if (!hashMap.get(c).equals(t.charAt(i))) {
                    return false;
                }
            } else {
                hashMap.put(c, t.charAt(i));
            }
        }
        for (Character c1 : hashMap.keySet()) {
            for (Character c2 : hashMap.keySet()) {
                if (!c1.equals(c2) && hashMap.get(c1).equals(hashMap.get(c2))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 如果同构的话,字符串中同一字母出现的位置应该是相同的
     * 当出现不同时 立即短路返回false
     * <p>
     * time O(n)
     * space O(1) (转数组可以不需要)
     */
    public boolean isIsomorphic2(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.indexOf(ch1[i]) != t.indexOf(ch2[i])) {
                return false;
            }
        }
        return true;
    }

}
