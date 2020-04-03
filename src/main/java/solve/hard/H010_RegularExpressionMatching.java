package solve.hard;


import org.junit.Test;

import java.util.Arrays;

/**
 * 正则表达式匹配
 * <p>
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 *
 * @author minwei
 */
public class H010_RegularExpressionMatching {

    private String s = "aab";
    private String p = "c*a*b";

    private String s1 = "ac";
    private String p1 = "ab.*";

    private String s2 = "ac";
    private String p2 = "*.c";

    @Test
    public void test1() {
        boolean match = isMatch(s, p);
        System.out.println(match);

        boolean match1 = isMatch(s1, p1);
        System.out.println(match1);

        boolean match2 = isMatch(s2, p2);
        System.out.println(match2);
    }

    @Test
    public void test2() {
        boolean match = isMatch2(s, p);
        System.out.println(match);

        boolean match1 = isMatch2(s1, p1);
        System.out.println(match1);

        boolean match2 = isMatch2(s2, p2);
        System.out.println(match2);
    }

    @Test
    public void test() {
        String s0 = "a";
        String p0 = ".*..a*";
        boolean match1 = isMatch2(s0, p0);
        System.out.println(match1);

    }

    /**
     * Recursion 递归法 -- 38.87% (存在重复子结构问题)
     * <p>
     * 递归的终止条件：
     * （1）如果s字符串的长度为0，如果此时字符串p当且仅当有形如"a*b*c*d*e*"这样的格式时，返回true；否则，返回false。
     * （2）如果s字符串的长度不为0，而p字符串的长度为0，返回false。
     * <p>
     * 递归的过程：
     * （1）如果s的最后一个字符与p的最后一个字符相等，或者说p的最后一个字符为"."，
     * 那么直接看字符串s中除去最后一个字符的字符串能否与字符串p中除去最后一个字符的字符串相匹配。
     * （2）如果p的最后一个字符为"*"，这种情况分为两种子情形
     * <p>
     * a.如果s的最后一个字符既不与p的倒数第二个字符相等，p的最后第二个字符也不为"."，
     * 那么直接看字符串s能否与字符串p中除去最后两个字符的字符串相匹配。
     * b.如果s的最后一个字符与p的倒数第二个字符相等，或者说p的最后第二个字符为"."，这种情况又分为三种子情形(subcase):
     * <pre>
     * b-1：看字符串s中除去最后一个字符的字符串能否与字符串p相匹配
     * b-2：看字符串s中能否与字符串p中除去最后一个字符的字符串相匹配
     * b-3：看字符串s中能否与字符串p中除去最后两个字符的字符串相匹配
     * </pre>
     * 只要上述b-1、b-2、b-3三种情况中有一种情况相匹配，我们就返回true。如果三种情况都不匹配，我们就返回false。
     * <p>
     * time O(mn)
     * space O(mn)
     */
    public boolean isMatch(String s, String p) {
        int ns = s.length();
        int np = p.length();

        // 基准情形
        if (ns != 0 && np == 0) {
            return false;
        }
        if (ns == 0) {
            if (np % 2 == 1) {
                return false;
            }
            int i = 1;
            // 探测所有偶数位置(奇数索引)是否都是'*' 且该位置前面的字符不是'*'
            // 根据要求 ** 返回的是false
            while (i < p.length() && p.charAt(i) == '*' && p.charAt(i - 1) != '*') {
                i += 2;
            }
            return i == p.length() + 1;
        }

        // 递归情形a
        if (s.charAt(ns - 1) == p.charAt(np - 1) || p.charAt(np - 1) == '.') {
            return isMatch(s.substring(0, ns - 1), p.substring(0, np - 1));
        }

        // 递归情形b
        if (p.charAt(np - 1) == '*') {
            if (s.charAt(ns - 1) != p.charAt(np - 2) && p.charAt(np - 2) != '.') {
                return isMatch(s.substring(0, ns), p.substring(0, np - 2));
            } else {
                return isMatch(s.substring(0, ns - 1), p)
                        || isMatch(s.substring(0, ns), p.substring(0, np - 1))
                        || isMatch(s.substring(0, ns), p.substring(0, np - 2));
            }
        }
        return false;
    }

    /**
     * 动态规划 -- 99.33%
     * <p>
     * 状态定义：
     * f(x, y)-- 字符串s中[0,x]范围内的字符串能否匹配字符串p中[0, y]范围内的字符串
     * 状态转移：
     * 如果p(y) == s(x) || p(y) == '.', 此时右f(x, y) = f(x - 1, y - 1)
     * 如果p(y) == '*'，
     * a.如果s(x) == p(y - 1) || p(y - 1) == '.'，
     * a-1：对照递归分析b-1 f(x - 1, y)
     * a-2：对照递归分析b-2 f(x, y - 1)
     * a-3：对照递归分析b-3 f(x, y - 2)
     * f(x, y) = f(x - 1, y) || f(x, y - 1) || f(x, y - 2)。
     * <p>
     * b.如果s(x) != p(y - 1) && p(y - 1) != '.'
     * f(x, y) = f(x, y - 2)
     * <p>
     * 其余情况一律为false
     * <p>
     * time O(mn)
     * space O(mn)
     *
     * @see H072_EditDistance#minDistance2
     */
    public boolean isMatch2(String s, String p) {
        int ns = s.length();
        int np = p.length();
        boolean[][] dp = new boolean[ns + 1][np + 1];
        dp[0][0] = true;

        // 基准情形的特殊情况 当s为空串且p不为空串时: 必须偶数项为* 且其前一项不能为*
        // * 的次数必须为遍历到的长度的一半。若中间有任意一次*缺席,则后面的都不再满足count要求,全为false
        int count = 1;
        for (int i = 1; i < np + 1; i++) {
            if ((i & 1) == 0 && p.charAt(i - 1) == '*'
                    && p.charAt(i - 2) != '*' && i >> 1 == count) {
                dp[0][i] = true;
                count++;
            }
        }

        for (int i = 1; i < ns + 1; i++) {
            for (int j = 1; j < np + 1; j++) {
                if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 如果p第一个字符是*号 则没有任何意义
                if (p.charAt(j - 1) == '*' && j > 1) {
                    // 如果s的最后一个字符既不与p的倒数第二个字符相等，p的最后第二个字符也不为"."
                    if (s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.') {
                        // 使用当前s子串和去掉2个字符的p子串作为当前子串的匹配结果
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        // 该字符和p中上一个字符匹配成功，使用下面三个匹配结果或运算后的结果，作为当前子串的匹配结果
                        // 1. s子串去掉一个字符，和当前p子串的匹配结果 （in this case, a* counts as multiple a ）
                        // 2. s子串和p子串去掉1个字符的匹配结果（in this case, a* counts as single a）
                        // 3. s子串和p子串去掉2个字符的匹配结果（in this case, (a*)(.*) counts as empty）
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[ns][np];
    }

}
