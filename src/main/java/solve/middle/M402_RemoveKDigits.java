package solve.middle;

import org.junit.Test;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 *
 * @author minwei
 */
public class M402_RemoveKDigits {

    @Test
    public void test() {
        String s = "1432219";
        String s1 = removeKdigits(s, 3);
        System.out.println(s1);
    }


    @Test
    public void test2() {
        String s = "1020567419";
        String s1 = removeKdigits(s, 6);

        // 最终结果 char[] = {0 0 1 9 7 [] [] [] [] []}  19
        System.out.println(s1);
    }

    /**
     * 贪心算法 -- 98.31%
     * 每次得到局部最优解,最终得到全局最优解
     * 如果比当前元素小，就向外弹；如果比当前元素大，就直接压
     * <p>
     * time O(n)
     * space O(n)
     */
    public String removeKdigits(String num, int k) {
        int newLength = num.length() - k;
        char[] stack = new char[num.length()];
        int top = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            // 当栈顶数字大于遍历到的当前数字 栈顶数字出栈
            while (top > 0 && stack[top - 1] > c && k > 0) {
                top--;
                k--;
            }
            // 否则入栈
            stack[top++] = c;
        }
        // 找到栈中第一个非零数字的位置 构建新的字符串来返回
        int offset = 0;
        while (offset < newLength && stack[offset] == '0') {
            offset++;
        }
        return offset == newLength ? "0" : new String(stack, offset,
                newLength - offset);
    }

}
