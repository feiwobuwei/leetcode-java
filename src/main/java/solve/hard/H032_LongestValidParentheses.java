package solve.hard;

import org.junit.Test;

import java.util.Stack;

/**
 * 最长有效括号
 * <p>
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * <p>
 * 示例 2:
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * @author minwei
 */
public class H032_LongestValidParentheses {

    private String s = "(((()))";
    private String s1 = "(()())";

    @Test
    public void test() {
        int i = longestValidParentheses(s);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = longestValidParentheses2(s);
        System.out.println(i);
    }

    @Test
    public void test3() {
        int i = longestValidParentheses3(s);
        System.out.println(i);

        int i1 = longestValidParentheses3(s1);
        System.out.println(i1);
    }

    @Test
    public void test4() {
        int i = longestValidParentheses4(s);
        System.out.println(i);
    }

    /**
     * 暴力法 -- 超时
     * <p>
     * time O(n3)
     * space O(n)
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j++) {
                if (isValid(s.substring(i, j))) {
                    result = Math.max(result, j - i);
                }
            }
        }
        return result;
    }

    /**
     * 辅助方法 time O(n)
     */
    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.empty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }

    /**
     * 动态规划
     * <p>
     * time O(n)
     */
    public int longestValidParentheses2(String s) {
        int result = 0;
        // dp[i]表示以下标为 i 的字符结尾的最长有效子字符串的长度
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                result = Math.max(result, dp[i]);
            }
        }
        return result;
    }

    /**
     * 栈 下标差
     * <p>
     * time O(n)
     */
    public int longestValidParentheses3(String s) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    result = Math.max(result, i - stack.peek());
                }
            }
        }
        return result;
    }

    /**
     * 双指针法
     * <p>
     * 正向遍历一次
     * 反向遍历一次
     * <p>
     * time O(n)
     */
    public int longestValidParentheses4(String s) {
        int left = 0, right = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                result = Math.max(result, 2 * right);
            } else if (right >= left) {
                // 以右括号开始的就废弃掉
                left = right = 0;
            }
        }

        // 反向再来一次
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                result = Math.max(result, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return result;
    }


}
