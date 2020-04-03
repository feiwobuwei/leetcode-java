package solve.easy;

import org.junit.Test;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * @author minwei
 */
public class E125_ValidPalindrome {

    private String s1 = "A man, a plan, a canal: Panama";
    private String s2 = "race a car";

    @Test
    public void test() {
        boolean palindrome = isPalindrome(s1);
        System.out.println(palindrome);

        palindrome = isPalindrome(s2);
        System.out.println(palindrome);
    }

    @Test
    public void test2() {
        boolean palindrome = isPalindrome2(s1);
        System.out.println(palindrome);

        palindrome = isPalindrome2(s2);
        System.out.println(palindrome);
    }

    /**
     * one-pass
     * <p>
     * time O(n)
     * space O(1)
     */
    public boolean isPalindrome(String s) {

        StringBuilder substring = new StringBuilder();

        // 先过滤掉非数字和字符串
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (helper(c)) {
                substring.append(c);
            }
        }

        String s1 = substring.toString();
        String s2 = substring.reverse().toString();

        System.out.println(s1);
        System.out.println(s2);

        return s1.equalsIgnoreCase(s2);

    }

    private boolean helper(char c) {

        boolean p = c <= '9' && c >= '0';
        boolean q = c <= 'z' && c >= 'a';
        boolean r = c <= 'Z' && c >= 'A';

        return p || q || r;
    }

    /**
     * 双指针法 速度是方法一4倍
     * <p>
     * time O(n)
     * space O(1)
     */
    public boolean isPalindrome2(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            // 过滤
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
