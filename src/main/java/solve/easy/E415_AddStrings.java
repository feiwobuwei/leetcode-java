package solve.easy;

import org.junit.Test;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 * 注意：
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 *
 * @author minwei
 */
public class E415_AddStrings {

    @Test
    public void test1() {
        String s1 = "8765876219";
        String s2 = "8882423";
        System.out.println(addStrings(s1, s2));
    }

    @Test
    public void test2() {
        String s1 = "100000000000000000000000000000000";
        String s2 = "233";
        System.out.println(addStrings(s1, s2));
    }


    public String addStrings(String num1, String num2) {

        // 将字符串反转 并转为字符数组
        char[] chars1 = new StringBuilder(num1).reverse().toString().toCharArray();
        char[] chars2 = new StringBuilder(num2).reverse().toString().toCharArray();

        // 在较长的数组长度上加1保存结果
        int[] result = new int[Math.max(chars1.length, chars2.length) + 1];

        // 对位相加 超十进一

        for (int i = 0; i < result.length; i++) {
            // 如果当前的i超过了某个数组的长度,就用0代替其高位
            int n1 = i < chars1.length ? chars1[i] - '0' : 0;
            int n2 = i < chars2.length ? chars2[i] - '0' : 0;

            // 代表进位 可能是0或1
            int temp = result[i];
            temp += n1 + n2;
            result[i] = temp;

            // 有进位就进行处理
            if (temp >= 10) {
                result[i + 1] = temp / 10;
                result[i] = temp % 10;
            }

        }

        // 用来存储最终结果
        StringBuilder sb = new StringBuilder();

        // 高位是1 需要加1位
        if (result[result.length - 1] == 1) {
            sb.append(1);
        }

        // 反向遍历
        for (int i = result.length - 2; i >= 0; i--) {
            sb.append(result[i]);
        }

        return sb.toString();
    }
}
