package solve.hard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 基本计算器
 * <p>
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 * <p>
 * 中缀 1+((2+3)*4)-5
 * 后缀  1 2 3 + 4 * + 5 -
 *
 * @author minwei
 * @see solve.middle.M150_EvaluateReversePolishNotation
 */
public class H224_BasicCalculator {

    @Test
    public void test() {
        String s = "1 + 1";
        int calculate = calculate(s);
        System.out.println(calculate);
    }

    @Test
    public void test2() {
        String s = "(1+(4+5+2)-3)+(6+8)";
        int calculate = calculate(s);
        System.out.println(calculate);
    }

    /**
     * 三步走 -- 12.05%
     */
    public int calculate(String s) {

        // 1 字符串 转 中缀表达式
        List<String> infix = toInfixExpressionList(s);
        System.out.println(infix);

        // 2 中缀表达式 转后缀表达式
        List<String> suffix = infixListToSuffixList(infix);
        System.out.println(suffix);

        Stack<Integer> stack = new Stack<>();

        for (String token : suffix) {
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

    /**
     * 1 输入字符串转为 中缀表达式 列表
     *
     * @param s 输入字符串
     * @return 中缀表达式列表
     */
    private List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();
        // 指针
        int i = 0;
        StringBuilder str;
        do {
            // 如果c不是一个数字
            if (s.charAt(i) < 48 || s.charAt(i) > 57) {
                // 还必须过滤掉 空格
                if (s.charAt(i) != ' ') {
                    ls.add("" + s.charAt(i));
                }
                i++;
            } else {
                // 考虑多位数的问题
                str = new StringBuilder();
                // 只要后续的是数字 就追加到末尾
                while (i < s.length() && s.charAt(i) >= 48 && s.charAt(i) <= 57) {
                    str.append(s.charAt(i));
                    i++;
                }
                ls.add("" + str);
            }
        } while (i < s.length());
        return ls;
    }

    /**
     * 2 中缀表达式列表 转为 后缀表达式列表
     *
     * @param list 中缀表达式列表
     * @return 后缀表达式 此时已经没有了括号
     */
    private List<String> infixListToSuffixList(List<String> list) {
        // 辅助栈
        Stack<String> stack = new Stack<>();
        // 储存结果
        List<String> result = new ArrayList<>();

        for (String item : list) {
            // 如果是一个数 直接加入list
            if (item.matches("\\d+")) {
                result.add(item);
            } else if ("(".equals(item)) {
                // 遇到左括号 压入栈中
                stack.push(item);
            } else if (")".equals(item)) {
                // 遇到右括号 s1一直弹出到 "(" 为止
                while (!"(".equals(stack.peek())) {
                    result.add(stack.pop());
                }
                // 补充弹出"(" 这样就消除掉了一对括号
                stack.pop();
            } else {
                // 运算符的情况
                // 当item优先级小于等于s1栈顶的运算符的优先级时 将栈顶的运算符弹出加入到list中
                // 需要一个比较优先级高低的方法
                while (stack.size() != 0 && Operation.getValue(item) <= Operation.getValue(stack.peek())) {
                    result.add(stack.pop());
                }
                // 否则直接将item压入栈中
                stack.push(item);
            }
        }
        // 将栈中中剩余的运算符压入list中
        while (stack.size() != 0) {
            result.add(stack.pop());
        }
        return result;
    }

    /**
     * 操作符优先级
     */
    private static class Operation {

        private final static int ADD = 1;
        private final static int SUB = 1;
        private final static int MUL = 2;
        private final static int DIV = 2;

        /**
         * 返回优先级
         *
         * @param operation 操作符
         * @return 操作符的优先级
         */
        public static int getValue(String operation) {
            int result;
            switch (operation) {
                case "+":
                    result = ADD;
                    break;
                case "-":
                    result = SUB;
                    break;
                case "*":
                    result = MUL;
                    break;
                case "/":
                    result = DIV;
                    break;
                default:
                    // 括号置0 优先级最高
                    result = 0;
            }
            return result;
        }
    }

}
