package solve.middle;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 *
 * @author wm
 */
public class M003_LongestSubstringWithoutRepeatingCharacters {

    private String s = "pwwkew";

    @Test
    public void test() {
        int result = lengthOfLongestSubstring(s);
        System.out.println(result);
    }

    @Test
    public void test2() {
        int result2 = lengthOfLongestSubstring2(s);
        System.out.println(result2);
    }

    @Test
    public void test3() {
        String s1 = "pwkeppw";
        int result3 = lengthOfLongestSubstring3(s1);
        System.out.println(result3);
    }

    /**
     * 暴力法 -- 提交超时
     * <p>
     * time O(N^3)
     * space O(min(m,n))
     * <p>
     * space: Set 的大小取决于字符串n的大小以及字符集/字母数量 m 的大小。
     * 也如果该字符串含有所有字母,就是字符集的大小。否则为自己本身所含有字符数量的大小
     *
     * @param s 要判别的字符串
     * @return 无重复的最长子串的长度
     */
    private int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s, i, j)) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }

    /**
     * 辅助方法
     *
     * @param s     要判别的字符串
     * @param start 起始索引
     * @param end   结束索引
     * @return 如果子字符串中的字符都是唯一的，它会返回true，否则会返回false。
     */
    private boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }

    /**
     * 双指针法 -- 滑动窗口法
     * <p>
     * time O(2n)=O(n)
     * space O(min(m,n))
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();

        int i = 0;
        int j = 0;
        int ans = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                // 尾指针右移
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                // 头指针右移
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 滑动窗口法 优化
     * <p>
     * time O(n)
     * space O(min(m,n))
     */
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        int answer = 0;
        // 该map的key为字符,value记录这个字符最近出现的位置的索引值+1
        // 所以当出现重复字符后,i(滑动窗口起点)直接跳到这个最近重复字符的后一个位置
        Map<Character, Integer> map = new HashMap<>();

        // i j 类似快慢指针
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                // String s1="pwkeppw"; 这样的字符串就有取其中较大值的必要了。
                // 因为较后面的pp，滑动窗口起点就已经到后面去了 再出现w不跳回到第2个位置
                i = Math.max(map.get(s.charAt(j)), i);
            }
            answer = Math.max(answer, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return answer;
    }


}
