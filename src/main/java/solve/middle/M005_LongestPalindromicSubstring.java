package solve.middle;

import org.junit.Test;

/**
 * 最长回文子串
 *
 * @author wm
 */
public class M005_LongestPalindromicSubstring {

    private String s1 = "baabd";
    private String s2 = "babad";

    @Test
    public void test() {
        String result = longestPalindrome(s1);
        System.out.println(result);

        result = longestPalindrome(s2);
        System.out.println(result);
    }

    @Test
    public void test2() {
        String result = longestPalindrome2(s1);
        System.out.println(result);

        result = longestPalindrome(s2);
        System.out.println(result);
    }

    @Test
    public void test3() {
        String result = longestPalindrome3(s1);
        System.out.println(result);

        result = longestPalindrome(s2);
        System.out.println(result);
    }

    /**
     * 中心扩展算法
     * <p>
     * time:O(n^2)
     * space:O(1)
     */
    public String longestPalindrome(String s) {
        // 长度为0或1 的情况
        if (s.length() < 2) {
            return s;
        }
        // 长度为2的情况
        if (s.length() == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                return s;
            }
            return s.substring(0, 1);
        }

        String result = "";

        // 从2到n-1 进行中心扩展
        for (int i = 1; i < s.length() - 1; i++) {
            // 奇数
            String odd = maxPalindrome(s, i - 1, i + 1);
            // 偶数1
            String even1 = maxPalindrome(s, i - 1, i);
            // 偶数2
            String even2 = maxPalindrome(s, i, i + 1);

            // 取三者的最长子串
            if (odd.length() > result.length()) {
                result = odd;
            }
            if (even1.length() > result.length()) {
                result = even1;
            }
            if (even2.length() > result.length()) {
                result = even2;
            }
        }
        return result;
    }

    /**
     * 求字符串某个区间可以扩展到的最长回文子串
     *
     * @param s 输入字符串
     * @param k 左边界 继续往左扩展
     * @param j 右边界 继续往右扩展
     * @return 可以扩展到的最大回文子串
     */
    private String maxPalindrome(String s, int k, int j) {
        while (k >= 0 && j < s.length() && s.charAt(k) == s.charAt(j)) {
            k--;
            j++;
        }
        return s.substring(k + 1, j);
    }

    /**
     * 动态规划 -- 比方法二速度慢 18.06%
     * <p>
     * 状态定义：dp[i][j]表示字符串s中[i, j]范围内的子串是否是回文串
     * <p>
     * 状态转移：
     * 1 当i == j时，[i, j]范围内只有一个字符，显然是回文串，dp[i][j] = true。
     * 2 当(j - i) ∈ (0, 2]时，[i, j]范围内有2个字符或3个字符，不管是2个字符还是3个字符，
     * 此时[i, j]范围内的子串是回文串的条件是s.charAt(i) == s.charAt(j)。
     * 3 当(j - i) > 2时，[i, j]范围内超出3个字符，此时[i, j]范围内的子串是回文串的条件
     * 是s.charAt(i) == s.charAt(j)且[i + 1, j - 1]范围内的子串是回文串。
     * <p>
     * time: O(n^2)
     * space O(n^2)
     */
    public String longestPalindrome2(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        if (n < 2) {
            return s;
        }
        if (n == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                return s;
            } else {
                return s.substring(0, 1);
            }
        }

        // dp[i][j]表示字符串s中[i, j]范围内的子串是否是回文串
        boolean[][] dp = new boolean[n][n];
        // 记录最长回文子串
        String result = "";
        // 最长回文子串的长度
        int max = 0;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                // 状态转移方程
                boolean b1 = s.charAt(i) == s.charAt(j);
                boolean b2 = (j - i) <= 2 || dp[i + 1][j - 1];
                dp[i][j] = b1 && b2;
                if (dp[i][j]) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        // 截取[i,j]段的字符串
                        result = s.substring(i, j + 1);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 暴力法 -- 提交超时
     * <p>
     * time:O(n^3) n字符串长度
     * space:O(1)
     */
    public String longestPalindrome3(String s) {

        if (s.isEmpty()) {
            return s;
        }
        String res = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String k = s.substring(i, j);
                String rk = new StringBuffer(k).reverse().toString();
                // equals 时间复杂度 O(n)
                if (k.equals(rk) && (k.length() > res.length())) {
                    res = k;
                }
            }
        }
        return res;
    }


}
