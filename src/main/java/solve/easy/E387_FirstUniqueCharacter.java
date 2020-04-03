package solve.easy;

import org.junit.Test;

import java.util.HashMap;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * s = "leetcode"
 * 返回 0.
 * s = "loveleetcode",
 * 返回 2.
 *
 * @author minwei
 */
public class E387_FirstUniqueCharacter {

    @Test
    public void test() {
        String s = "leetcode";
        int i = firstUniqChar(s);
        System.out.println(i);
    }

    @Test
    public void test2() {
        String s = "loveleetcode";
        int i = firstUniqChar2(s);
        System.out.println(i);
    }

    /**
     * 哈希表
     * <p>
     * 18%
     * time O(n)
     * space O(1)
     */
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        int n = s.length();
        // 每个字符每出现一次 次数加1
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;

    }

    /**
     * 带标志优化
     * 20%
     */
    public int firstUniqChar2(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (i != j && s.charAt(i) == s.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 类似哈希表 使用数组来记录
     */
    public int firstUniqChar3(String s) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
