package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 分隔回文串
 * <p>
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 * 示例:
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 *
 * @author wm
 */
public class M131_PalindromePartitioning {


    String s1 = "aabb";
    String s2 = "abcbac";

    /*
     * aabb
     * 先考虑在第 1 个位置切割，a | abb
     * 这样我们只需要知道 abb 的所有结果，然后在所有结果的头部把 a 加入
     * abb 的所有结果就是 [a b b] [a bb]
     * 每个结果头部加入 a，就是 [a a b b] [a a bb]
     *
     * aabb
     * 再考虑在第 2 个位置切割，aa | bb
     * 这样我们只需要知道 bb 的所有结果，然后在所有结果的头部把 aa 加入
     * bb 的所有结果就是 [b b] [bb]
     * 每个结果头部加入 aa,就是 [aa b b] [aa bb]
     *
     * aabb
     * 再考虑在第 3 个位置切割，aab|b
     * 因为 aab 不是回文串，所有直接跳过
     *
     * aabb
     * 再考虑在第 4 个位置切割，aabb |
     * 因为 aabb 不是回文串，所有直接跳过
     *
     * 最后所有的结果就是所有的加起来
     * [a a b b] [a a bb] [aa b b] [aa bb]
     *
     */


    @Test
    public void test() {
        // [[a, a, b], [aa, b]]
        System.out.println(partition("aab"));

        // [[a, a, b, b], [a, a, bb], [aa, b, b], [aa, bb]]
        System.out.println(partition(s1));

        // [[a, b, c, b, a, c], [a, bcb, a, c], [abcba, c]]
        System.out.println(partition(s2));
    }

    /**
     * 分治法
     */
    public List<List<String>> partition(String s) {
        return partitionHelper(s, 0);
    }

    private List<List<String>> partitionHelper(String s, int start) {
        // 递归出口，空字符串
        if (start == s.length()) {
            List<String> list = new ArrayList<>();
            List<List<String>> ans = new ArrayList<>();
            ans.add(list);
            return ans;
        }

        List<List<String>> ans = new ArrayList<>();
        for (int i = start; i < s.length(); i++) {
            //当前切割后是回文串才考虑
            if (isPalindrome(s.substring(start, i + 1))) {
                String left = s.substring(start, i + 1);
                //遍历后边字符串的所有结果，将当前的字符串加到头部
                for (List<String> l : partitionHelper(s, i + 1)) {
                    l.add(0, left);
                    ans.add(l);
                }
            }

        }
        return ans;
    }

    /**
     * 判断一个字符串是否是回文
     */
    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        // 标准双指针法
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
