package solve.middle;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *  
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 * @author minwei
 */
public class M567_PermutationInString {

    @Test
    public void test() {
        String s1 = "ab";
        String s2 = "abeidbaooo";

        boolean b = checkInclusion(s1, s2);
        System.out.println(b);
    }

    /**
     * 滑动窗口法
     */
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 == 0) {
            return true;
        }
        if (n2 == 0) {
            return false;
        }
        int[] map = new int[26];
        for (int i = 0; i < n1; i++) {
            map[s1.charAt(i) - 'a']++;
        }
        int left = 0, right = -1;
        int[] window = new int[26];
        while (right + 1 < n2) {
            right++;
            window[s2.charAt(right) - 'a']++;
            if (map[s2.charAt(right) - 'a'] == 0) {
                left = right + 1;
                if (left >= n2) {
                    break;
                }
                Arrays.fill(window, 0);
            } else if (map[s2.charAt(right) - 'a'] < window[s2.charAt(right) - 'a']) {
                while (map[s2.charAt(right) - 'a'] < window[s2.charAt(right) - 'a']) {
                    window[s2.charAt(left) - 'a']--;
                    left++;
                }
            }
            if (Arrays.equals(window, map)) {
                return true;
            }
        }
        return false;
    }


}
