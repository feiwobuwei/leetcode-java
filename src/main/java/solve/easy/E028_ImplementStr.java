package solve.easy;

import org.junit.Test;

import java.util.Arrays;

/**
 * C语言的strStr()对应于Java里的indexOf()
 * 禁止使用indexOf()
 * <p>
 * 著名的 KMP算法
 *
 * @author minwei
 */
public class E028_ImplementStr {

    /**
     * 有2个空格
     */
    private String s1 = "BBC ABCDAB ABCDABCDABDE";
    private String s2 = "ABCDABD";

    private String s3 = "ABCDABCA";
    private String s4 = "AABAABAAA";

    @Test
    public void test() {
        int[] next = kmpNext(s3);
        System.out.println(Arrays.toString(next));

        int[] next2 = kmpNext(s4);
        System.out.println(Arrays.toString(next2));
    }

    @Test
    public void test1() {
        String haystack = "hello", needle = "ll";
        int i = strStr(haystack, needle);
        System.out.println(i);

        System.out.println(violentMatching(s1, s2));
    }

    @Test
    public void test2() {
        // 部分匹配值数组
        int[] next = kmpNext(s2);
        System.out.println(Arrays.toString(next));

        int i = strStr2(s1, s2);
        System.out.println(i);
    }

    @Test
    public void test3() {
        String haystack = "hello", needle = "ll";
        int i = violentMatching(haystack, needle);
        System.out.println(i);

        System.out.println(violentMatching(s1, s2));
    }



    /**
     * 动态窗口扫描法 -- leetcode 99.23%
     * <p>
     * time O((m-n)*n) -- subString 是线性的 O(n)
     * space O(1)
     *
     * @param haystack 草堆
     * @param needle   针
     * @return needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
     */
    private static int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    // ===================== KMP ===================== //

    public int strStr2(String haystack, String needle) {

        if (haystack.length() == 0 && needle.length() != 0) {
            return -1;
        }

        if (needle.length() == 0) {
            return 0;
        }
        return kmpSearch(haystack, needle, kmpNext(needle));
    }

    /**
     * KMP算法 -- 10.88% (更适用于m远大于n的场合 )
     * time O(m+n)
     *
     * @param s1   主串或文本串 待匹配字符串
     * @param s2   s2用来匹配的字符串 也叫模式串
     * @param next 部分匹配值数组
     * @return 匹配的首个位置
     */
    private int kmpSearch(String s1, String s2, int[] next) {

        for (int i = 0, j = 0; i < s1.length(); i++) {

            while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
                // 发现不匹配时 模式串应该回溯到的位置
                j = next[j - 1];
            }
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            if (j == s2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    // 部分匹配值就是 "前缀" 和 "后缀" 的最长的共有元素的长度

    // 例如 ABCDABD
    // "A"的前缀后缀都为空集 共有元素的长度为0
    // "AB"的前缀和后缀为 [A] [B]  共有元素的长度为0
    // "ABC"的前缀和后缀为 [A,AB] [C,BC]  共有元素的长度为0
    // "ABCD"的前缀和后缀为 [A,AB,ABC] [D,CD,BCD]  共有元素的长度为0
    // "ABCDA"的前缀和后缀为 [A,AB,ABC,ABCD] [A,DA,CDA,BCDA]  共有元素A 长度为1
    // "ABCDAB"的前缀 为 [A,AB,ABC,ABCD,ABCDA] [B,AB,DAB,CDAB,BCDAB]   共有元素AB 长度为2
    // "ABCDABD的前缀 为 [A,AB,ABC,ABCD,ABCDA,ABCDAB] [D,BD,ABD,DABD,CDABD,BCDABD]  共有元素长度为0

    /**
     * 辅助方法
     * <p>
     * 状态定义 next[i] 表示到索引为i的字符位置时的部分匹配值 也即当i+1不匹配时应该返回到的位置
     * 边界条件 next[0]=-1
     * 状态转移方程
     *
     * @param ps 用来匹配的字符串 也叫模式串
     * @return 部分匹配值数组
     */
    private int[] kmpNext(String ps) {

        // 部分匹配值数组
        int[] next = new int[ps.length()];

        // 如果模式字符串长度为1 部分匹配值就是0 每次都从该唯一字符进行匹配
        next[0] = 0;

        // 从第2个字符开始
        for (int j = 1, k = 0; j < ps.length(); j++) {
            // 前缀类似文本串  后缀类似模式串
            while (k > 0 && ps.charAt(j) != ps.charAt(k)) {
                // 这里和主方法类似 当k位置匹配失败 应该回溯到 next[k - 1]
                k = next[k - 1];
            }

            // 如果相等 部分匹配值(最大相同前后缀)就加1
            if (ps.charAt(j) == ps.charAt(k)) {
                k++;
            }

            next[j] = k;
        }
        return next;
    }

    // ===================== 双指针法 ===================== //

    /**
     * 暴力匹配法 双指针法 -- 48.78%
     *
     * @param haystack 主串或文本串 待匹配字符串
     * @param needle   s2用来匹配的字符串 也叫模式串
     * @return 匹配的首个位置
     */
    private static int violentMatching(String haystack, String needle) {

        if (haystack.length() == 0 && needle.length() != 0) {
            return -1;
        }

        if (needle.length() == 0) {
            return 0;
        }

        char[] chars1 = haystack.toCharArray();
        char[] chars2 = needle.toCharArray();

        int length1 = chars1.length;
        int length2 = chars2.length;

        int i = 0;
        int j = 0;

        while (i < length1 && j < length2) {

            if (chars1[i] == chars2[j]) {
                i++;
                j++;
            } else {
                // 如果匹配失败  j就是上次匹配成功的字符串长度
                // i去掉上次匹配的字符串 然后右移一位继续
                i = i - j + 1;
                j = 0;
            }

            // 根据j的值判断是否成功
            if (j == chars2.length) {
                return i - j;
                // 最终匹配成功时 i位于s1匹配字符串最后一个字母的后一位 去掉匹配长度j i就指向匹配字符串的第一个字目
            }

        }

        return -1;
    }

}
