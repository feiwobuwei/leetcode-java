package solve.easy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 * @author minwei
 * @see E119_PascalTriangle2
 */
public class E118_PascalTriangle {

    @Test
    public void test(){
        List<List<Integer>> generate = generate(5);
        generate.forEach(System.out::println);
    }

    /**
     * 动态规划
     * <p>
     * time ：O(n^2)
     * space O(n^2)
     *
     * @param numRows 行数
     * @return 前numRows行
     */
    public List<List<Integer>> generate(int numRows) {
        // 记录最终结果
        List<List<Integer>> result = new ArrayList<>();
        // 输入0返回空集
        if (numRows == 0) {
            return result;
        }
        // 基准情形 第一排只有一个1
        result.add(new ArrayList<>());
        result.get(0).add(1);

        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            // 当前排
            List<Integer> row = new ArrayList<>();
            // 前一排
            List<Integer> prevRow = result.get(rowNum - 1);
            // 行头元素是1
            row.add(1);
            // 状态转移方程 适用于除了头元素和尾元素的中间元素
            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            // 行尾元素是1
            row.add(1);
            // 处理完了一排 加入最终结果
            result.add(row);
        }
        return result;
    }
}
