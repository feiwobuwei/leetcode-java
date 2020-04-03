package solve.easy;

import org.junit.Test;

/**
 * 最长公共前缀 Longest Common Prefix
 *
 * @author minwei
 */
public class E014_LCP {

    @Test
    public void test1() {
        String[] s = {"flower", "flow", "flight"};
        String result1 = longestCommonPrefix(s);
        System.out.println(result1);
    }

    @Test
    public void test2() {
        String[] s2 = {"flow", "flower", "flight"};
        String result2 = longestCommonPrefix2(s2);
        System.out.println(result2);
    }

    @Test
    public void test3() {
        String[] s3 = {"flow", "flower", "flight"};
        String result3 = longestCommonPrefix3(s3);
        System.out.println(result3);
    }

    @Test
    public void test4() {
        String[] s4 = {"leetsa", "leetcode", "leetca", "leedsa"};
        String result4 = longestCommonPrefix4(s4);
        System.out.println(result4);
    }


    /**
     * 水平扫描
     * <p>
     * time O(s) space O(1)
     */
    private static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            // 查找子字符串第一次出现的位置
            // 如果后面的字符串包含前面的字符串(即前为后子串,那么一开始indexOf的返回值就是0)
            while (strs[i].indexOf(prefix) != 0) {
                // 由于是找前缀，所以裁剪后面
                prefix = prefix.substring(0, prefix.length() - 1);
                // 如果已经裁没了 直接返回空串
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    /**
     * 垂直扫描
     * <p>
     * time O(s) space O(1) S是所有字符串中字符数量的总和
     * <p>
     * 最坏情况下，需要进行 n * m 次比较
     * 但是最好的情况下，算法只需要进行 n * minLen 次比较
     */
    private static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String s0 = strs[0];
        for (int i = 0; i < s0.length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                // 一旦出现某个字符串已经到尾 或者 某索引值的字符出现了不同,立即以前面已经相同的部分结束并返回。
                // 如果字符串数组中某个字符串很短,那么相比第一种水平扫描垂直扫描会很快结束
                if (i == strs[j].length() || strs[j].charAt(i) != s0.charAt(i)) {
                    return s0.substring(0, i);
                }
            }
        }
        // 全部字符串都相同的特殊情况
        return strs[0];
    }

    /**
     * 分治法
     * <p>
     * time: O(s)  space: O(m⋅log(n))
     * 最坏情况下，我们有 n 个长度为 m 的相同字符串。
     * 时间复杂度：O(S)，S 是所有字符串中字符数量的总和，S=mn。
     */
    private static String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 先方法重载
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    /**
     * divide的过程
     *
     * @param strs 字符串数组
     * @param l    头指针
     * @param r    尾指针
     * @return 公共字符串
     */
    private static String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            // 此时分到的这边只有一个字符串
            return strs[l];
        } else {
            int mid = (l + r) / 2;
            String lcpLeft = longestCommonPrefix(strs, l, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, r);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    /**
     * 缩小到只有2个字符串
     * 方便进行两两比较
     */
    private static String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                // 第一次出现不同的字符,就以前面的部分作为公共部分返回 垂直扫描
                return left.substring(0, i);
            }
        }
        // 此时两个字符串一个包含另一个的全部字符,那么返回较短的那个字符串
        return left.substring(0, min);
    }

    /**
     * 二分查找法
     * 99.91% 四种中最快的算法
     * <p>
     * time: O(S⋅log(n)) space: O(1)
     * @see E278_FirstBadVersion#firstBadVersion3
     */
    private static String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        // 先获取字符串数组中的最短字符串的长度
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }

        int result = 0;

        // 头指针
        int low = 1;
        // 尾指针
        int high = minLen;
        while (low <= high) {
            int middle = (high - low) / 2 + low;
            if (isCommonPrefix(strs, middle)) {
                low = middle + 1;
                result = middle;
            } else {
                high = middle - 1;
            }

        }
        // result是0时 返回空串
        return strs[0].substring(0, result);
    }

    /**
     * 二分法的辅助方法 -- 判断公共前缀是否在[0,len)区间内
     * <p>
     * S[1...mid] 不是所有串的公共前缀。对于所有的 j > mid , S[1..j] 也不是公共前缀，
     * S[1...mid] 是所有串的公共前缀。对于所有的 i < mid , S[1..i] 都是可行的公共前缀，
     * 每次一都会进行 S = m * n 次比较
     */
    private static boolean isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str1)) {
                return false;
            }
        }
        return true;
    }


}
