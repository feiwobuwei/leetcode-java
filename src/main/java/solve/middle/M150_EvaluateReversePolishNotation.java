package solve.middle;

import org.junit.Test;

import java.util.Stack;

/**
 * 逆波兰表达式求值
 * <p>
 * 根据逆波兰表示法，求表达式的值。
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 * <p>
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 *
 * @author minwei
 * @see solve.hard.H224_BasicCalculator
 */
public class M150_EvaluateReversePolishNotation {

    @Test
    public void test() {
        String[] tokens = {"4", "13", "5", "/", "+"};
        int i = evalRPN(tokens);
        System.out.println(i);
    }

    private String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

    @Test
    public void test2() {
        int i = evalRPN(tokens);
        System.out.println(i);
    }

    /**
     * 进行速度优化 -- 95.97%
     * <p>
     * 正则匹配太耗时间了
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if ("+".equals(token)) {
                stack.push(stack.pop() + stack.pop());
            } else if ("-".equals(token)) {
                int n1 = stack.pop();
                int n2 = stack.pop();
                stack.push(n2 - n1);
            } else if ("*".equals(token)) {
                stack.push(stack.pop() * stack.pop());
            } else if ("/".equals(token)) {
                int n1 = stack.pop();
                int n2 = stack.pop();
                stack.push(n2 / n1);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }
}
