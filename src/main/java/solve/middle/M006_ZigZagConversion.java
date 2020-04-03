package solve.middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Z 字形变换
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * @author wm
 */
public class M006_ZigZagConversion {

    @Test
    public void test() {
        String s = "LEETCODEISHIRING";
        int numRows = 3;

        String result = convert(s, numRows);
        // LCIRETOESIIGEDHN
        System.out.println(result);

    }

    @Test
    public void test2() {
        String s = "LEETCODEISHIRING";

        int numRows = 4;
        String result = convert2(s, numRows);
        // LDREOEIIECIHNTSG
        System.out.println(result);

    }

    /**
     * 按对应行添加字符 使用上升下降标志 -- 54.35%
     * 从左向右迭代字符串
     * <p>
     * time:O(n) -- n为字符串长度
     * space:O(n)
     */
    public String convert(String s, int numRows) {
        // 只有一行直接返回
        if (numRows == 1) {
            return s;
        }
        // 记录每一排的字符
        List<StringBuilder> rows = new ArrayList<>();
        // 初始化
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }
        // 当前排
        int curRow = 0;
        // 下降或上升标志
        boolean goingDown = false;
        // 遍历字符数组
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            // 如果是第一排或者最后一排 就换方向
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            // true 向下走 +1 false 向上走 -1 第一次是下降true
            curRow += goingDown ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        // 按每一排拼接字符串
        for (StringBuilder row : rows) {
            result.append(row);
        }
        // 把字符串构造器变为字符串
        return result.toString();
    }

    /**
     * 另一种形式 寻找所在行的规律 -- 67.42%
     * <p>
     * time:O(n) -- n为字符串长度
     * space:O(n)
     */
    public String convert2(String s, int numRows) {

        if (numRows <= 1) {
            return s;
        }
        // StringBuilder 数组
        StringBuilder[] sb = new StringBuilder[numRows];

        // 初始化
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }

        // 找规律
        for (int i = 0; i < s.length(); i++) {
            // 2 * numRows - 2 为周期长度
            int index = i % (2 * numRows - 2);
            // (numRows - 1) - (index - (numRows - 1)) = 2 * (numRows - 1) - index
            index = index < numRows ? index : 2 * numRows - 2 - index;
            sb[index].append(s.charAt(i));
        }

        // 把第一排以后的添在第一排后面
        for (int i = 1; i < sb.length; i++) {
            sb[0].append(sb[i]);
        }

        return sb[0].toString();
    }


}
