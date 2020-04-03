package solve.middle;

import org.junit.Test;

/**
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 * <p>
 * 对于 1 字节的字符，字节的第一位设为0，后面7位为这个符号的unicode码。
 * 对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为0，
 * 后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。
 * <p>
 * 这是 UTF-8 编码的工作方式：
 * <p>
 * Char. number range  |        UTF-8 octet sequence
 * (hexadecimal)    |              (binary)
 * --------------------+---------------------------------------------
 * 0000 0000-0000 007F | 0xxxxxxx
 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 给定一个表示数据的整数数组，返回它是否为有效的 utf-8 编码。
 * <p>
 * 注意:
 * 输入是整数数组。只有每个整数的最低 8 个有效位用来存储数据。这意味着每个整数只表示 1 字节的数据。
 * <p>
 * 示例 1:
 * data = [197, 130, 1], 表示 8 位的序列: 11000101 10000010 00000001.
 * 返回 true 。
 * 这是有效的 utf-8 编码，为一个2字节字符，跟着一个1字节字符。
 * <p>
 * 示例 2:
 * data = [235, 140, 4], 表示 8 位的序列: 11101011 10001100 00000100.
 * 返回 false 。
 * 前 3 位都是 1 ，第 4 位为 0 表示它是一个3字节字符。
 * 下一个字节是开头为 10 的延续字节，这是正确的。
 * 但第二个延续字节不以 10 开头，所以是不符合规则的。
 *
 * @author minwei
 */
public class M393_UTF8Validation {

    // ********************** UTF-8 中的一个字符可能的长度为 1 到 4 字节************************ //

    private int[] data = {197, 130, 1};
    private int[] data2 = {250, 145, 145, 145, 145};
    private int[] data3 = {145};

    @Test
    public void test1() {
        boolean b = validUtf8(data);
        System.out.println(b);
    }

    @Test
    public void test2() {
        boolean b = validUtf8(data2);
        System.out.println(b);
    }

    @Test
    public void test3() {
        boolean b = validUtf8(data3);
        System.out.println(b);
    }

    @Test
    public void test() {
        // 11111010
        String s = toBinary(250, 8);
        System.out.println(s);
    }

    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) {
            return false;
        }
        int n = data.length;
        int cursor = 0;
        while (cursor < n) {
            // 获取二进制字符串
            String s = toBinary(data[cursor], 8);
            // 如果是单字节
            if (checkOneByte(s)) {
                cursor++;
            } else {
                // 否则是多字节
                int count = 0;
                // 查看首个字节的前面有几个1
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '1') {
                        count++;
                    } else {
                        break;
                    }
                }
                // 快捷短路
                if (count == 1 || count > 4 || count > n - cursor) {
                    return false;
                }
                // 该部分字符串都必须以10开头
                for (int i = cursor + 1; i < cursor + count; i++) {
                    String temp = toBinary(data[i], 8);
                    if (!checkBytes(temp)) {
                        return false;
                    }
                }
                cursor += count;
            }
        }
        return true;
    }

    /**
     * 将一个int数字转换为二进制的字符串形式。
     *
     * @param num    需要转换的int类型数据
     * @param digits 要转换的二进制位数，位数不足则在前面补0
     * @return 二进制的字符串形式
     */
    public String toBinary(int num, int digits) {
        int value = 1 << digits | num;
        String bs = Integer.toBinaryString(value);
        return bs.substring(1);
    }

    /**
     * 检验1字节的情况
     */
    private boolean checkOneByte(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        return "0".equals(s.substring(0, 1));
    }

    /**
     * 检验多字节的情况
     */
    private boolean checkBytes(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        return "10".equals(s.substring(0, 2));
    }

}
