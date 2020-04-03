package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * <p>
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 例如，给出 n = 3，生成结果为：
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 *
 * @author wm
 */
public class M022_GenerateParentheses {

    @Test
    public void test() {
        List<String> strings = generateParenthesis(3);
        System.out.println(strings);
        System.out.println(strings.size());
    }

    @Test
    public void test2() {
        List<String> strings = generateParenthesis2(3);
        System.out.println(strings);
        System.out.println(strings.size());
    }

    /**
     * 暴力法 -- 92.21%
     * <p>
     * time: O(4^n * n)
     * space: O(4^n * n)
     *
     * @param n 括号对数
     * @return 所有种类的组合集
     */
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    /**
     * 使用递归生成所有序列。长度为 n 的序列就是 
     * '(' 加上所有长度为 n-1 的序列，
     * 以及 
     * ')' 加上所有长度为 n-1 的序列。
     * <p>
     * (((((( 发现不是 然后会退一格改为 ((((() 然后检验 然后(((()(  然后(((())
     *
     * @param current 当前位置字符
     * @param pos     当前索引位置
     * @param result  符合条件的一个字符串
     */
    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    /**
     * 左括号的数量减去右括号的数量的净值。
     * 如果这个值小于零或者不以零结束，该序列就是无效的，否则它是有效的。
     */
    public boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return (balance == 0);
    }

    /**
     * 回溯法 -  backtracking
     * C(n,2n)/n+1 第n个卡塔兰数
     * <p>
     * time: O( 4^n/ n^0.5 )
     * space: O( 4^n/ n^0.5 )
     * <p>
     * 卡特兰数Cn满足以下递推关系：
     * Cn+1=CnC0 + Cn-1C1 + Cn-2C2 +... + C0Cn;
     * <p>
     * C0=1
     * C1=1
     * C2=2 = 1*1+1*1     [(()), ()()]
     * C3=5 = 1*2+1*1+2*1 [((())), (()()), (())(), ()(()), ()()()]
     *
     * @param n
     */
    public List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> res, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            res.add(cur);
            return;
        }

        if (open < max) {
            backtrack(res, cur + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(res, cur + ")", open, close + 1, max);
        }
    }
}
