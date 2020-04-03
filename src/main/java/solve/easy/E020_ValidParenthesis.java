package solve.easy;

import java.util.HashMap;
import java.util.Stack;


/**
 * 检查括号表达式的合法性
 *
 * @author minwei
 */
public class E020_ValidParenthesis {


    /**
     * time O(n) space O(n)
     *
     * @param s 待检查表达式字符串
     * @return 表达式合法返回 true；否则 false
     */
    private static boolean isValid(String s) {
        // 快速判断
        if (s == null || s.length() % 2 == 1) {
            return false;
        }

        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果是一个开括号 直接入栈
            if (map.keySet().contains(c)) {
                stack.push(c);
                // 如果是一个闭括号
            } else if (map.values().contains(c)) {
                // 如果没有可匹配的括号(栈为空) 或者栈顶括号不配对，字符串无效
                // Stack继承Vector, 判断是否为空也可用 isEmpty()
                if (!stack.empty() && map.get(stack.peek()) == c) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    public static void main(String[] args) {

        String s1 = "([)]";
        String s2 = "{[]}";

        System.out.println(isValid(s1));
        System.out.println(isValid(s2));
    }
}
